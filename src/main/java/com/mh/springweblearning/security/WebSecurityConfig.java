package com.mh.springweblearning.security;

import com.mh.springweblearning.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {
	    auth.userDetailsService(new CustomUserDetailService())
				.passwordEncoder(new SimplePasswordEncoder());
//		auth.inMemoryAuthentication()
//				.passwordEncoder(new SimplePasswordEncoder())
				//.withUser("manu")
				//.password("dummy").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity  http) throws Exception {
			http
					.authorizeRequests()
						.antMatchers("/login*").permitAll()
						.antMatchers("/webjars**").permitAll()
						.antMatchers("/logout**").permitAll()
						.antMatchers("/**").authenticated()
						.and()
						.formLogin()
						.permitAll()
						.defaultSuccessUrl("/")//.loginPage("/login")
						.failureUrl("/login-error")
							.and()
							.logout().logoutSuccessUrl("/login")
							.permitAll();
		}
}

class SimplePasswordEncoder implements PasswordEncoder {


	@Override
	public String encode(CharSequence charSequence) {
		return charSequence.toString();
	}

	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return true;
	}
}