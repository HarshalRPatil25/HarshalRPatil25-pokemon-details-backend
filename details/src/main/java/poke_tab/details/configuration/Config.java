package poke_tab.details.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {
    @Autowired 
    private UserDetailsService userDetailsService;

   
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.csrf(Customizer->Customizer.disable());
            http.httpBasic(Customizer.withDefaults());
            http.sessionManagement(Session->Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.authorizeHttpRequests(
                request -> request.requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/pokemon/**").permitAll()
                          .requestMatchers("/admin/**").hasRole("admin")
                        .anyRequest().authenticated()
            );

           return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
    
}
