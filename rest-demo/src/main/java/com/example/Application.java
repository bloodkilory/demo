package com.example;

import com.example.config.ExampleUserService;
import com.example.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * @author Yangkun
 *
 */
@SpringBootApplication
@ImportResource("applicationContext.xml")
@EnableAutoConfiguration
@ComponentScan
public class Application {

	/**
	 * Start Application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Configure Jsckson ObjectMapper
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapperCustomizer() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.enable(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS);
		return mapper;
	}

	/**
	 * Customize Embedded Tomcat Container for GZip
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
		return servletContainer -> ((TomcatEmbeddedServletContainerFactory) servletContainer).addConnectorCustomizers(
				connector -> {
					AbstractHttp11Protocol httpProtocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
					httpProtocol.setCompression("on");
					httpProtocol.setCompressionMinSize(64);
					String mimeTypes = httpProtocol.getCompressableMimeTypes();
					String mimeTypesWithJson = mimeTypes + "," + MediaType.APPLICATION_JSON_VALUE
							+ "," + MediaType.APPLICATION_XML_VALUE;
					httpProtocol.setCompressableMimeTypes(mimeTypesWithJson);
				}
		);
	}

	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
	@Configuration
	@EnableGlobalMethodSecurity(securedEnabled = true)
	protected static class AuthenticationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDao userDao;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(new ExampleUserService(userDao));
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("REST-DEMO");
			http.exceptionHandling().authenticationEntryPoint(entryPoint);
			http.requestMatchers().antMatchers("/**").anyRequest()
					.and().httpBasic()
					.and().anonymous().disable().csrf().disable()
					.rememberMe().tokenValiditySeconds(18000);
		}
	}
}