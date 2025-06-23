package edu.lelyak.service;

import edu.lelyak.domain.User;
import edu.lelyak.domain.UserSubscription;
import edu.lelyak.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserDetailsRepository userDetailsRepo;

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subcriptions = channel.getSubscribers()
                .stream()
                .filter(subscription ->
                        subscription.getSubscriber().equals(subscriber)
                )
                .toList();

        if (subcriptions.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
//            channel.getSubscribers().removeAll(subcriptions);
            subcriptions.forEach(channel.getSubscribers()::remove);
        }

        return userDetailsRepo.save(channel);
    }
}
