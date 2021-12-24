package org.test.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Bean public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
   }
   
   @Autowired
   private DataSource dataSource;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
	  http.csrf().disable(); 
      http
        .csrf().disable()
        .authorizeRequests()
            .antMatchers("/", "/account/register", "/board/**", "/css/**","/img/**", "/api/**").permitAll()
            .mvcMatchers("/manage/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/account/login")
            .permitAll()
            .and()
        .logout()
            .permitAll();

   }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth)
           throws Exception {
       auth.jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery("select username, password, enabled "
                       + "from user "
                       + "where username = ?")
               .authoritiesByUsernameQuery("select u.username, r.name "
                       + "from user_role ur inner join user u on ur.user_id = u.id "
                       + "inner join role r on ur.role_id = r.id "
                       + "where u.username = ?");
   }

   

    
}