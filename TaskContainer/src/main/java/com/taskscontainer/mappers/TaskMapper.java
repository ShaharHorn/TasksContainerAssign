package com.taskscontainer.mappers;

import com.taskscontainer.DTOs.TaskDto;
import com.taskscontainer.Entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class},componentModel="spring")
public interface TaskMapper extends DtoEntityMapper<TaskDto,TaskEntity> {

}

