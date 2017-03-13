package pji.cbt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry routes) {
		routes.addViewController("/admin/dashborad").setViewName("index");
		routes.addViewController("/tester/dashboard").setViewName("indextester");
		routes.addViewController("/403").setViewName("403");
		routes.addViewController("/login").setViewName("login");
	}

}
