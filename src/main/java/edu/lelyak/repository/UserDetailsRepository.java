package edu.lelyak.repository;

import edu.lelyak.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nazar Lelyak.
 */
public interface UserDetailsRepository extends JpaRepository<User, String> {
}
