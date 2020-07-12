package edu.lelyak.config;


import edu.lelyak.domain.User;
import edu.lelyak.repository.UserDetailsRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

/**
 * @author Nazar Lelyak.
 */
@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/js/**", "/error**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepository detailsRepository) {
        return map -> {
            String id = (String) map.get("sub");

            User user = detailsRepository.findById(id)
                    .orElseGet(() -> User.builder()
                            .id(id)
                            .name((String) map.get("name"))
                            .email((String) map.get("email"))
                            .locale((String) map.get("locale"))
                            .userPic((String) map.get("picture"))
                            .build());

            user.setLastVisit(LocalDateTime.now());
            return detailsRepository.save(user);
        };
    }
}
