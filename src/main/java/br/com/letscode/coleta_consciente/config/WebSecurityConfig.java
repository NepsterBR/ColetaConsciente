package br.com.letscode.coleta_consciente.config;


import br.com.letscode.coleta_consciente.service.AutenticacaoService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AutenticacaoService autenticacaoService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/servico").permitAll()
                .antMatchers(HttpMethod.GET, "/ponto-coleta/*").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/cliente/*").hasAuthority("ADMIN")
                .and()
                .csrf().disable()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder codificadorPassword() {
        return new BCryptPasswordEncoder();
    }
}

//                     Salvar em memoria
//                    .inMemoryAuthentication()
//                    .passwordEncoder(codificadorPassword())
//                    .withUser("user")
//                    .password(codificadorPassword().encode("123"))
//                    .roles("USER");

