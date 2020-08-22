package com.taskscontainer.mappers;


import com.taskscontainer.DTOs.TasksContainerDto;
import com.taskscontainer.Entities.TaskContainerEntity;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;

@Mapper(componentModel="spring")
public interface TasksContainerMapper extends DtoEntityMapper<TasksContainerDto, TaskContainerEntity> {
}
