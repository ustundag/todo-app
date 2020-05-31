package com.ustundag.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String description;
    private Date deadline;
    private boolean isCompleted;

}