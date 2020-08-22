package com.taskscontainer.DTOs;

import com.taskscontainer.Enums.Priority;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TaskDto implements Comparable<TaskDto> {
    @NotNull(message = "Id cannot be null or empty")
    Long id;

    @Size(max=250)
    @NotNull
    String name;

    @Size(max=250)
    String description;

    String dueDate;

    @NotNull
    long taskContainerId;

    Priority priority;

    @Override
    public int compareTo(TaskDto a) {
        return a.priority.compareTo(this.priority);
    }
}
