package pji.cbt;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import pji.cbt.config.AppConfig;
import pji.cbt.config.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("pji.cbt")
public class WebAppInitializer implements WebApplicationInitializer {
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/secure/*");

		return registrationBean;
	}

    public static void main(String[] args) throws Exception{
        SpringApplication.run(WebAppInitializer.class, args);
    }

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	       AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
	        ctx.register(AppConfig.class);  
	        ctx.setServletContext(servletContext);    
	        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
	        dynamic.addMapping("/");  
	        dynamic.setLoadOnStartup(1);  
		
	}
}

