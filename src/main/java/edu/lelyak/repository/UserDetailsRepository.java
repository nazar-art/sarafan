package edu.lelyak.repository;

import edu.lelyak.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
public interface UserDetailsRepository extends JpaRepository<User, String> {
    @Override
    @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
    Optional<User> findById(String id);
}
