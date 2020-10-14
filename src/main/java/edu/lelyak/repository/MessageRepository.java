package edu.lelyak.repository;

import edu.lelyak.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nazar Lelyak.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Override
    @EntityGraph(attributePaths = {"comments"})
    Page<Message> findAll(Pageable pageable);

}
