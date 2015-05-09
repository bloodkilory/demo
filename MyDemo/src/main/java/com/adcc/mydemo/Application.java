package com.adcc.mydemo;

import com.adcc.mydemo.pojo.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer servletContainer) {
				((TomcatEmbeddedServletContainerFactory) servletContainer).addConnectorCustomizers(
						new TomcatConnectorCustomizer() {
							@Override
							public void customize(Connector connector) {
								AbstractHttp11Protocol httpProtocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
								httpProtocol.setCompression("on");
								httpProtocol.setCompressionMinSize(64);
								String mimeTypes = httpProtocol.getCompressableMimeTypes();
								String mimeTypesWithJson = mimeTypes + "," + MediaType.APPLICATION_JSON_VALUE;
								httpProtocol.setCompressableMimeTypes(mimeTypesWithJson);
							}
						}
				);
			}
		};
	}

//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
	@Configuration
	@EnableGlobalMethodSecurity(securedEnabled = true)
	protected static class AuthenticationSecurity extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
//					.and().withUser("admin").password("admin").roles("ADMIN")
//					.and().withUser("x").password("x").roles("X");
			auth.authenticationProvider(new AuthenticationProvider() {

				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					//1、获取用户名、密码
					String username = authentication.getName();
					String password = authentication.getCredentials().toString();

					//2、调用第三方认证
					// 模拟从Cache中取得用户信息
					Map<String, User> users = getUsersFromCache();
					User user = thirdPartyAuthenticate(username, password, users);

					//3、创建权限集合
					List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
					for(String auth : user.getAuthorities()) {
						authorities.add(new SimpleGrantedAuthority(auth));
					}
					//4、返回令牌对象
					return new UsernamePasswordAuthenticationToken(username, password, authorities);
				}

				@Override
				public boolean supports(Class<?> aClass) {
					return true;
				}

				private Map<String, User> getUsersFromCache() {
					String auth1 = "ADMIN";
					String auth2 = "USER";
					String auth3 = "X";
					List<String> u1au = Arrays.asList(auth1);
					List<String> u2au = Arrays.asList(auth1, auth2);
					List<String> u3au = Arrays.asList(auth3);
					User u1 = new User("user", "user", u1au);
					User u2 = new User("admin", "admin", u2au);
					User u3 = new User("x", "x", u3au);
					Map<String, User> map = new HashMap<String, User>();
					map.put(u1.getName(), u1);
					map.put(u2.getName(), u2);
					map.put(u3.getName(), u3);
					return map;
				}

				private User thirdPartyAuthenticate(String username, String password, Map<String, User> users) {
					User user = users.get(username);
					if(user == null) {
						throw new RuntimeException("user is not exist");
					}
					if(!user.getPassword().equals(password)) {
						throw new RuntimeException("password is wrong!");
					}
					return user;
				}
			});
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("ADCC");
			http.exceptionHandling().authenticationEntryPoint(entryPoint);
			http.requestMatchers().antMatchers("/**").anyRequest()
					.and().httpBasic()
					.and().anonymous().disable().csrf().disable()
					.rememberMe().tokenValiditySeconds
					(1800);
		}
	}

//	@Configuration
//	@EnableGlobalMethodSecurity(securedEnabled = true)
//	protected static class AuthenticationSecurity extends GlobalMethodSecurityConfiguration {
//
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
//					.and().withUser("admin").password("admin").roles("ADMIN")
//					.and().withUser("x").password("x").roles("X");
//		}
//
//	}

}