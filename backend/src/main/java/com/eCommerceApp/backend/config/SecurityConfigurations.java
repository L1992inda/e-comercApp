package com.eCommerceApp.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    // private CustomUserDetailsService customUserDetailsService;

    // public SecurityConfigurations(CustomUserDetailsService customUserDetailsService) {
    //     this.customUserDetailsService = customUserDetailsService;
    // }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                // .requestMatchers("/category/user/**","/item/user/**","/description/user/**","/out").permitAll()
                // .requestMatchers("/category/admin/**","/item/admin/**","/page","/description/admin/**").hasAuthority("admin")
                .anyRequest().permitAll());
                // .formLogin(login -> login
                //         .defaultSuccessUrl("/page",true)
                //         .permitAll())
                // .logout(logout-> logout
                // .logoutSuccessUrl("/out")
                // .deleteCookies("JSESSIONID"));
                //.userDetailsService(customUserDetailsService);
                    
                        
        return http.build();
    }

}
