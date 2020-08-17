package edu.lelyak.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Nazar Lelyak.
 */
//@Getter
//@Setter
//@ToString(of = {"id", "text"})
//@EqualsAndHashCode(of = {"id"})
@Data
@Table
@Entity
@NoArgsConstructor
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
}
