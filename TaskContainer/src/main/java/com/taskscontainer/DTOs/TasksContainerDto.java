package com.taskscontainer.DTOs;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TasksContainerDto {

    @NotNull
    Long id;

    @Size(max = 250)
    @NotNull
    String name;
}
