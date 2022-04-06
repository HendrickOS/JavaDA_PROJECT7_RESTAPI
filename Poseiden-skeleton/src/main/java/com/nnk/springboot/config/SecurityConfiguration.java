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

import com.nnk.springboot.services.MyAppUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private MyAppUserDetailsService myAppUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**")
				.hasAnyAuthority("ADMIN", "USER").antMatchers("/user/**").permitAll().and().formLogin()
				.defaultSuccessUrl("/bidList/list").and().logout().logoutUrl("/app-logout").logoutSuccessUrl("/").and()
				.oauth2Login().defaultSuccessUrl("/app/oauth2LoginSuccess").and().exceptionHandling()
				.accessDeniedPage("/app/error");
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
		auth.parentAuthenticationManager(new CustomUserDetailsService()).jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, 1" + "from users " + "where username = ? ")
				.authoritiesByUsernameQuery("select username, role " + "from users " + "where username = ? ");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
