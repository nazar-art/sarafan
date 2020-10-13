package edu.lelyak.dto;

import com.fasterxml.jackson.annotation.JsonView;
import edu.lelyak.domain.Message;
import edu.lelyak.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Getter
@Setter
@AllArgsConstructor
@JsonView(Views.FullMessage.class)
public class MessagePageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Message> messages;
    private int currentPage;
    private int totalPages;
}
