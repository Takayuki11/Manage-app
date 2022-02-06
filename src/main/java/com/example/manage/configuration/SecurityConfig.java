package com.example.manage.configuration;

import com.example.manage.JWTProvider;
import com.example.manage.configuration.filters.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
@ServletComponentScan("com.example.manage")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public JWTProvider provider;

    @Autowired
    public SecurityConfig(JWTProvider provider){
        this.provider = provider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/", "/signup", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean jwtAuthenticationFilter(){
        FilterRegistrationBean<JWTAuthenticationFilter> bean = new FilterRegistrationBean<JWTAuthenticationFilter>();
        bean.setFilter(new JWTAuthenticationFilter(this.provider));
        bean.addUrlPatterns("/tasks");
        bean.setOrder(1);
        return bean;
    }
}
