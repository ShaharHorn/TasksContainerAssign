package com.taskscontainer.Entities;

import com.taskscontainer.Enums.Priority;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
public class TaskEntity {
    @Id
    Long id;

    @Size(max=250)
    String name;

    @Size(max = 250)
    String description;

    OffsetDateTime dueDate;

    @NotNull
    long taskContainerId;

    Priority priority;
}
