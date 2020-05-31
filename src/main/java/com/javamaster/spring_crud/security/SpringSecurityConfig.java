package com.javamaster.spring_crud.security;

import com.javamaster.spring_crud.security.jwt.JwtSecurityConfigurer;
import com.javamaster.spring_crud.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SpringSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception  {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/auth/signIn").permitAll()
                .antMatchers("/auth/signUp").permitAll()
                .antMatchers(HttpMethod.GET, "/employees/list").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/employees/list/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/employees/addEmployee").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/employees/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/departments/list").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/departments/list/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/departments/{name}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/departments/addDepartment").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/departments/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/projects/list").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/projects/list/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/projects/{name}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/projects/addProject").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/projects/delete/{id}").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }
}
