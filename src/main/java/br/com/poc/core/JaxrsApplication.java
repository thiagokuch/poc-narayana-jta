package br.com.poc.core;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/account/")
public class JaxrsApplication extends Application {
}