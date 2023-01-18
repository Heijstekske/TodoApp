package com.in28minutes.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {


   private String password;

   @Bean
   public InMemoryUserDetailsManager createUserDetailsManager() {

      Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

      UserDetails userDetails1 = createNewUser(passwordEncoder, "in28minutes", "dummy");
      UserDetails userDetails2 = createNewUser(passwordEncoder, "Nielske", "heijstekker");

      return new InMemoryUserDetailsManager(userDetails1, userDetails2);
   }

   private UserDetails createNewUser(Function<String, String> passwordEncoder, String username, String password) {
      UserDetails userDetails = User.builder()
            .passwordEncoder(passwordEncoder)
            .username(username)
            .password(password)
            .roles("USER", "ADMIN")
            .build();
      return userDetails;
   }

   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

}