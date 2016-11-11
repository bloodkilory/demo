package com.example;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.example.config.SpringSecurityUserDetailConfig;
import com.example.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yangkun
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({"applicationContext.xml"})
@PropertySources({@PropertySource(value = "classpath:configs.properties")})
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
	 * Read Project's *.properties file
	 *
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesConfig() {
		PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
        placeholderConfigurer.setOrder(1);
		return placeholderConfigurer;
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

	/**
	 * Spring sercurity configuration
	 */
	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
	@Configuration
	@EnableGlobalMethodSecurity(securedEnabled = true)
	protected static class AuthenticationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDao userDao;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(new SpringSecurityUserDetailConfig(userDao));
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