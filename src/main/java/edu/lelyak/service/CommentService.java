package edu.lelyak.service;

import edu.lelyak.domain.Comment;
import edu.lelyak.domain.User;
import edu.lelyak.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        return commentRepository.save(comment);
    }
}
