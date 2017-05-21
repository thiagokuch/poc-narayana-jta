package br.com.poc.core;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Component
@ApplicationPath("/account/")
public class JaxrsApplication extends Application {
}