package com.ustundag.service.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class TodoDTO {
    private Long id;
    @NotNull
    private String description;
    private Date deadline;

    public TodoDTO(Long id, @NotNull String description, Date deadline, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    private boolean isCompleted;
    private URI uri;
}
