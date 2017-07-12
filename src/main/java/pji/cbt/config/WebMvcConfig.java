package pji.cbt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry routes) {
		routes.addViewController("/403").setViewName("403");
		routes.addViewController("/login").setViewName("login");
	}

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/rest/**")
//        .allowedOrigins("http://localhost:9095")
//        .allowedMethods("GET", "POST", "PUT", "DELETE");
//    }
}