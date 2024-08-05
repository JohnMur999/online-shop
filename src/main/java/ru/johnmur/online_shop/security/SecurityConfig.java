package ru.johnmur.online_shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.johnmur.online_shop.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Настройка выхода из системы
        http
                .csrf(AbstractHttpConfigurer::disable) // Отключает защиту CSRF
                .authorizeHttpRequests(authorize -> authorize // Начало конфигурации авторизации запросов
                        .requestMatchers("/register", "/login").permitAll() // Разрешает доступ к указанным URL-адресам без аутентификации
                        .anyRequest().authenticated() // Требует аутентификации для всех остальных запросов
                )
                .formLogin(formLogin -> formLogin // Настройка формы логина
                        .loginPage("/login") // Указывает URL-адрес пользовательской страницы логина
                        .defaultSuccessUrl("/home", true) // Указывает URL-адрес, на который пользователь будет перенаправлен после успешного логина
                        .permitAll() // Разрешает доступ ко всем связанным с логином URL-адресам
                )
                .logout(LogoutConfigurer::permitAll // Разрешает доступ к URL-адресу выхода из системы
                );

        return http.build(); // Строит и возвращает объект SecurityFilterChain
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
