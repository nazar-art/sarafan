package edu.lelyak.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Data
@Table
@Entity
@NoArgsConstructor
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(Views.Id.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @Column(updatable = false)
    @JsonView(Views.FullMessage.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.FullMessage.class)
    private User author;

    @JsonView(Views.FullMessage.class)
    @OneToMany(mappedBy = "message", orphanRemoval = true)
    private List<Comment> comments;


    /**
     * Meta information for links:
     */
    @JsonView(Views.FullMessage.class)
    private String link;

    @JsonView(Views.FullMessage.class)
    private String linkTitle;

    @Column(length = 1000)
    @JsonView(Views.FullMessage.class)
    private String linkDescription;

    @JsonView(Views.FullMessage.class)
    private String linkCover;

}
