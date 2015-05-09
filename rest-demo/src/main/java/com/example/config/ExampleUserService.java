package com.example.config;

import com.example.dao.UserDao;
import com.example.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bloodkilory on 15/5/5.
 */
public class ExampleUserService implements UserDetailsService {

	private UserDao userDao;

	public ExampleUserService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.findByName(username);
		if(user != null) {
			List<GrantedAuthority> authorities = user.getAuthorties().stream()
					.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}
}
