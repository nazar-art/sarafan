package edu.lelyak.repository;

import edu.lelyak.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nazar Lelyak.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
