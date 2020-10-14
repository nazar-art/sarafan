package edu.lelyak.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nazar Lelyak.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(Views.IdName.class)
    private String id;

    @JsonView(Views.IdName.class)
    private String name;

    @JsonView(Views.IdName.class)
    private String userPic;

    private String email;

    @JsonView(Views.FullProfile.class)
    private String locale;

    @JsonView(Views.FullProfile.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    @JsonIdentityReference
    @JsonView(Views.FullProfile.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    private Set<User> subscriptions = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "user_subscribers",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    @JsonIdentityReference
    @JsonView(Views.FullProfile.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    private Set<User> subscribers = new HashSet<>();
}
