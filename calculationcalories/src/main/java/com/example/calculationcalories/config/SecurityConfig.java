package com.example.calculationcalories.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/h2-console/**").permitAll() // ✅ トップページとH2を許可
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll()) // ✅ ログインフォームは誰でもアクセス可
            .logout(logout -> logout.permitAll()) // ✅ ログアウトも許可
            .csrf(csrf -> csrf.disable()) // ✅ H2コンソール用に完全CSRF無効化
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // ✅ H2のFrame許可

        return http.build();
    }
}
