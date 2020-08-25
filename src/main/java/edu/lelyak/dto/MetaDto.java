package edu.lelyak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nazar Lelyak.
 */
@Data
@AllArgsConstructor
public class MetaDto {
    private String title;
    private String description;
    private String cover;
}
