package tads.ufrn.provapw2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/admin", "/cadastro","/salvar", "/editar", "/deletar" ).hasRole("ADMIN");
//                    auth.requestMatchers("/verCarrinho/", "/adicionarCarrinho", "/finalizarCompra").hasRole("USER");
                    auth.anyRequest().permitAll();
                })
                .formLogin(login -> login.loginPage("/login").permitAll())
                //.defaultSuccessUrl("/", true)
                //.and()
                .logout(logout -> logout.logoutUrl("/logout"))
                //.and()
                .build();
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}