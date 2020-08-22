package com.taskscontainer.services;

import com.taskscontainer.DTOs.TasksContainerDto;

import java.util.List;

public interface TaskContainerService {
    public TasksContainerDto getById(long id) throws Exception;

    public void createTasksContainer(TasksContainerDto taskContainerDto);

    public void deleteContainer(Long containerId) throws Exception;

    public Boolean isContainerExists(Long id);

    public List<TasksContainerDto> getAllContainers();
}
