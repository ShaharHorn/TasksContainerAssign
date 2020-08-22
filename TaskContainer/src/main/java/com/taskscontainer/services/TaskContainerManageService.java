package com.taskscontainer.services;

import com.taskscontainer.DTOs.TaskDto;
import com.taskscontainer.DTOs.TasksContainerDto;
import com.taskscontainer.Enums.Priority;
import javassist.NotFoundException;

import java.util.List;

public interface TaskContainerManageService {
    public void createTasksContainer(TasksContainerDto taskContainerDto);

    public void deleteContainer(Long containerId) throws Exception;

    public Long createTask(TaskDto task) throws Exception;

    public void updateTaskById(Long id,TaskDto task) throws NotFoundException;

    public void deleteTask(Long containerId, Long taskId) throws Exception;

    public TaskDto getTask(Long containerId, Long taskId);

    public void deleteAllTasksByContainerId(long id) throws Exception;

    public void updateTaskPriority(Long containerId, Long id, Priority priority) throws Exception;

    List<TaskDto> getAllTasksByContainerId(Long id);

}
