package edu.lelyak.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.lelyak.domain.Comment;
import edu.lelyak.domain.User;
import edu.lelyak.domain.Views;
import edu.lelyak.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nazar Lelyak.
 */
@RestController
@AllArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @JsonView(Views.FullComment.class)
    public Comment create(
            @RequestBody Comment comment,
            @AuthenticationPrincipal User user) {

        return commentService.create(comment, user);
    }

}
