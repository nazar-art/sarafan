package edu.lelyak.service;

import edu.lelyak.domain.Comment;
import edu.lelyak.domain.Message;
import edu.lelyak.domain.User;
import edu.lelyak.domain.Views;
import edu.lelyak.dto.EventType;
import edu.lelyak.dto.ObjectType;
import edu.lelyak.repository.CommentRepository;
import edu.lelyak.util.WsSender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

/**
 * @author Nazar Lelyak.
 */
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepository commentRepository, WsSender wsSender) {
        this.commentRepository = commentRepository;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepository.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
