package edu.lelyak.repository;

import edu.lelyak.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nazar Lelyak.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
