package edu.lelyak.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
@Entity
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class UserSubscription implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private UserSubscriptionId id;

    @ManyToOne
    @MapsId("channelId")
    @JsonIdentityReference
    @JsonView(Views.IdName.class)
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User channel;

    @ManyToOne
    @MapsId("subscriberId")
    @JsonView(Views.IdName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private User subscriber;

    @JsonView(Views.IdName.class)
    private boolean active;

    public UserSubscription(User channel, User subscriber) {
        this.channel = channel;
        this.subscriber = subscriber;
        this.id = new UserSubscriptionId(channel.getId(), subscriber.getId());
    }
}
