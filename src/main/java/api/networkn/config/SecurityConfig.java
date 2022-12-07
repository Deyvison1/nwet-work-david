package api.networkn.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import api.networkn.security.jwt.JwtAuthFilter;
import api.networkn.security.jwt.JwtService;
import api.networkn.services.impl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer  {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .csrf().disable()
            .cors().configurationSource(corsConfigurationSource()).and()
            .antMatcher("/**").authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/usuario/**")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/product/get-all")
            .permitAll()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
  	CorsConfigurationSource corsConfigurationSource() {
  		CorsConfiguration configuration = new CorsConfiguration();
  		
  		//TODO: substituir * pelos valores corretos
  		configuration.setAllowedOrigins(Collections.singletonList("*"));
  		configuration.setAllowedMethods(Collections.singletonList("*"));
  		configuration.setAllowedHeaders(Collections.singletonList("*"));
//  		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"));
//  		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
  		
  		  
  		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  		source.registerCorsConfiguration("/**", configuration);
  		
  		return source;
  	}

}