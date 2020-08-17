package edu.lelyak.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.lelyak.domain.Message;
import edu.lelyak.domain.Views;
import edu.lelyak.dto.EventType;
import edu.lelyak.dto.ObjectType;
import edu.lelyak.repository.MessageRepository;
import edu.lelyak.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
//@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageRepository messageRepository;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepository messageRepository, WsSender wsSender) {
        this.messageRepository = messageRepository;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        Message createdMessage = messageRepository.save(message);

        wsSender.accept(EventType.CREATE, createdMessage);

        return createdMessage;
    }

    @PutMapping("/{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message) {

        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updatedMessage = messageRepository.save(messageFromDb);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    /*@MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message change(Message message) {
        return messageRepository.save(message);
    }*/
}
