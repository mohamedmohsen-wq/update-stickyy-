package com.springmvcproject.stickynotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authRequest -> {
                authRequest.requestMatchers("/about", "/css/**").permitAll(); // الوصول للجميع
                authRequest.requestMatchers("/", "/note/**", "/my-notes/**", "/save/**","/sticky-note/**","/edit-sticky-note/**","/update/**", "/updatesend/**")
                           .authenticated(); // يجب تسجيل الدخول للوصول
                authRequest.requestMatchers("/users/**", "/updatesend/**","/","/note/delete/**")
                           .hasAnyRole("ADMIN"); // الوصول فقط لـ ADMIN
                authRequest.anyRequest().permitAll(); // السماح لجميع المسارات الأخرى
            })
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .headers(AbstractHttpConfigurer::disable)
            .formLogin(Customizer.withDefaults()) // نموذج تسجيل الدخول
            .httpBasic(Customizer.withDefaults()); // التوثيق الأساسي HTTP
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

