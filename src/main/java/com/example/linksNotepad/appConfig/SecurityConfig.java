package com.example.linksNotepad.appConfig;

import com.example.linksNotepad.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsServiceImpl userDetailsService;
//
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
public BCryptPasswordEncoder bCryptPasswordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/private/**").hasRole("USER")
                //Доступ разрешен всем пользователей
                .antMatchers("/welcome").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/private/home")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/welcome");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
