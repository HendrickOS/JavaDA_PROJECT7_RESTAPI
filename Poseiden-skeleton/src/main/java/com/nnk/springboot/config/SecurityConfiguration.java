package com.nnk.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/home/**", "/user/**").permitAll()
//				.antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**")
//				.hasAnyAuthority("ADMIN", "USER").anyRequest().authenticated().and().formLogin().and().logout()
//				.logoutUrl("/app-logout").logoutSuccessUrl("/").and().httpBasic();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home/**", "/user/**").permitAll()
				.antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**")
				.hasAnyAuthority("ADMIN", "USER").and().oauth2Login();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.parentAuthenticationManager(new CustomUserDetailsService());
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, 1" + "from users " + "where username = ? ")
				.authoritiesByUsernameQuery("select username, role " + "from users " + "where username = ? ");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
