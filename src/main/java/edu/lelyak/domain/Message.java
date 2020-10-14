package edu.lelyak.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Data
@Table
@Entity
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@JsonIdentityInfo(
        property = "id",
        generator = ObjectIdGenerators.PropertyGenerator.class
)
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.FullMessage.class)
    private User author;

    @OneToMany(mappedBy = "message", orphanRemoval = true)
    @JsonView(Views.FullMessage.class)
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
/*@Data
@Table
@Entity
@NoArgsConstructor
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
//        generator = ObjectIdGenerators.IntSequenceGenerator.class
)
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

    //    @JsonManagedReference
    @JsonView(Views.FullMessage.class)
    @OneToMany(mappedBy = "message", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> comments;


    *//**
     * Meta information for links:
     *//*
    @JsonView(Views.FullMessage.class)
    private String link;

    @JsonView(Views.FullMessage.class)
    private String linkTitle;

    @Column(length = 1000)
    @JsonView(Views.FullMessage.class)
    private String linkDescription;

    @JsonView(Views.FullMessage.class)
    private String linkCover;

}*/
