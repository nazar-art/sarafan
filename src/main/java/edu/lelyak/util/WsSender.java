package edu.lelyak.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.lelyak.dto.EventType;
import edu.lelyak.dto.ObjectType;
import edu.lelyak.dto.WsDto;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

/**
 * @author Nazar Lelyak.
 */
@Component
@AllArgsConstructor
public class WsSender {

    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class<?> view) {
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload) -> {
            String value;

            try {
                value = writer.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            template.convertAndSend(
                    "/topic/activity",
                    new WsDto(objectType, eventType, value)
            );
        };
    }

}
