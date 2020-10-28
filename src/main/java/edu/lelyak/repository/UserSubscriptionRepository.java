package edu.lelyak.repository;

import edu.lelyak.domain.User;
import edu.lelyak.domain.UserSubscription;
import edu.lelyak.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);

    List<UserSubscription> findByChannel(User channel);

    UserSubscription findByChannelAndSubscriber(User channel, User subscriber);
}
