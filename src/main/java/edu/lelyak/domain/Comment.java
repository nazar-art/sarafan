package edu.lelyak.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Nazar Lelyak.
 */
@Data
@Table
@Entity
@EqualsAndHashCode(of = {"id"})
public class Comment {

    @Id
    @JsonView(Views.IdName.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id")
    @JsonView(Views.FullComment.class)
    private Message message;

    @ManyToOne
    @JsonView(Views.IdName.class)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User author;
}
