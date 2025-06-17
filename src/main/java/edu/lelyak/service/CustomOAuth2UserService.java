package edu.lelyak.service;

import edu.lelyak.domain.CustomOAuth2User;
import edu.lelyak.domain.User;
import edu.lelyak.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserDetailsRepository userDetailsRepo;
    private final DefaultOAuth2UserService defaultService = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("Loading user {}; from {}",
            userRequest.getClientRegistration().getRegistrationId(),
            userRequest.getClientRegistration().getRedirectUri()
        );
        OAuth2User oauth2User = defaultService.loadUser(userRequest);
        return processOAuth2User(oauth2User);
    }

    private CustomOAuth2User processOAuth2User(OAuth2User oauth2User) {
        Map<String, Object> attributes = oauth2User.getAttributes();
        String id = (String) attributes.get("sub");

        User user = userDetailsRepo.findById(id).orElseGet(() -> {
            User newUser = new User();
            newUser.setId(id);
            newUser.setName((String) attributes.get("name"));
            newUser.setEmail((String) attributes.get("email"));
            newUser.setGender((String) attributes.get("gender"));
            newUser.setLocale((String) attributes.get("locale"));
            newUser.setUserpic((String) attributes.get("picture"));
            return newUser;
        });

        user.setLastVisit(LocalDateTime.now());
        User savedUser = userDetailsRepo.save(user);
        log.info("Saved user {}", savedUser);

        // Return a custom OAuth2User implementation
        return new CustomOAuth2User(savedUser, oauth2User.getAttributes(), "sub");
    }
}
