package edu.lelyak.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionId implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Id.class)
    private String channelId;

    @JsonView(Views.Id.class)
    private String subscriberId;
}
