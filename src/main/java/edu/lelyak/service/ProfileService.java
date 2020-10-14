package edu.lelyak.service;

import edu.lelyak.domain.User;
import edu.lelyak.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserDetailsRepository userRepository;

    /*public User getProfile(User user) {
        return userRepository.findById(user.getId());
    }*/

    public User changeSubscription(User channel, User subscriber) {
        Set<User> subscribers = channel.getSubscribers();

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            subscribers.add(subscriber);
        }

        return userRepository.save(channel);
    }
}
