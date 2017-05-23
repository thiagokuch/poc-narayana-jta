package br.com.poc.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Component
@Configuration
public class JerseyConfiguration extends ResourceConfig {

	public JerseyConfiguration() {
		//register(AccountController.class);
		
		//Ou esse
		register(RequestContextFilter.class);
        packages("br.com.poc.controller");
	}
	
	@Bean
	public ServletRegistrationBean jerseyServlet() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
	    registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfiguration.class.getName());
	    return registration;
	}

}
