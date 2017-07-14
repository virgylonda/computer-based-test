package pji.cbt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String SQL_LOGIN = "SELECT username, password, enabled FROM tb_user WHERE username = ?";
	private static final String SQL_PERMISSION = "SELECT u.username, r.name FROM tb_user u JOIN tb_role r ON u.role_id = r.id WHERE username = ?";
	
	@Autowired
    CustomSuccessHandler customSuccessHandler;
 
	@Autowired
	private DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/static/**", "/asset/**","/test","/test-user","/user-create", "/answer/**","/question/**","/category/**", "/authentication/**", "/test/**", "/rest/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/tester/**").hasAuthority("TEST")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(customSuccessHandler)
                .and()
            .logout()
                .permitAll()
                .and()
            .csrf().disable();
    }
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    		.jdbcAuthentication()
    		.dataSource(dataSource)
    		.usersByUsernameQuery(SQL_LOGIN).passwordEncoder(passwordEncoder())
    		.authoritiesByUsernameQuery(SQL_PERMISSION);
    }
    
    protected BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}