package edu.lelyak.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import edu.lelyak.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nazar Lelyak.
 */
@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsDto {

    private ObjectType objectType;
    private EventType eventType;

    @JsonRawValue
    private String body;
}
