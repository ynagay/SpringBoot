package com.jb.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
/*		.withDefaultSchema()
		.withUser("myUser")
			.password("pass")
			.roles("USER")
		.and()
			.withUser("jane")
			.password("pass2")
			.roles("USER")
		.and()
			.withUser("managerUser")
			.password("pass3")
			.roles("ADMIN");*/
		
		//customizing schema
		.usersByUsernameQuery("select username, password, enabled " +
				"from user_accounts where username = ?")
		.authoritiesByUsernameQuery("select username, role " +
				"from user_accounts where username = ?")
		.passwordEncoder(bCryptEncoder);
	}
	
	/*
	 * @Bean public PasswordEncoder getPasswordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/projects/new").hasRole("ADMIN")
			.antMatchers("/projects/save").hasRole("ADMIN")
			.antMatchers("/employees/new").hasAuthority("ADMIN")
			.antMatchers("/employees/save").hasAuthority("ADMIN")
			//.antMatchers("/h2-console/**").permitAll()
			//.antMatchers("/").authenticated().and().formLogin();
			.antMatchers("/","/**").permitAll()
			.and()
			.formLogin();
			//.loginPage("/login-page"); custom logging page
		
			/*
			 * http.csrf().disable(); http.headers().frameOptions().disable();
			 */
		http.csrf().disable();
	}
}
