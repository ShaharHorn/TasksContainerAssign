package com.taskscontainer.services;

import com.taskscontainer.DTOs.TaskDto;
import com.taskscontainer.Entities.TaskEntity;
import com.taskscontainer.Enums.Priority;
import com.taskscontainer.mappers.TaskMapper;
import com.taskscontainer.repos.TasksRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
//@RequiredArgsConstructor
public class TasksServiceImp implements TasksService {
    private TasksRepo tasksRepo;
    private TaskMapper taskMapper;

    @Autowired
    public TasksServiceImp(TasksRepo tasksRepo, TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
        this.tasksRepo = tasksRepo;
    }

    @Override
    public Long createTask(TaskDto task) throws Exception {
        try{
            TaskEntity taskEntity = taskMapper.toEntity(task);
            if (tasksRepo.existsById(taskEntity.getId()))
                throw new IllegalArgumentException("");
            tasksRepo.save(taskEntity);
            return taskEntity.getId();
        } catch (Exception e) {
            e.getMessage();
            throw e;
        }
    }

    @Override
    public void updateTaskById(Long id,TaskDto taskDto) throws NotFoundException {
        try{
            TaskEntity taskEntity = taskMapper.toEntity(taskDto);
            if(tasksRepo.existsById(id))
            {
                tasksRepo.save(taskEntity);
                return;
            }
            throw new NotFoundException("");
        }
        catch (Exception e) {
        throw e;
        }
    }

    @Override
    public void deleteTask(Long containerId, Long taskId) throws Exception {
        try {
            boolean isFound = tasksRepo.existsById(taskId);
            if (isFound) {
                tasksRepo.deleteById(taskId);
                return;
            }
            throw new NotFoundException("");
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public TaskDto getTask(Long containerId, Long taskId) {
        try {
            TaskEntity entity = tasksRepo.findById(taskId).get();
            TaskDto dto = taskMapper.toDto(entity);
            return dto;
        }
        catch (NoSuchElementException e)
        {
        return null;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void deleteAllTasksByContainerId(long id) {
        try {
            tasksRepo.deleteAllTasksByContainerId(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void updateTaskPriority(Long containerId, Long taskId, Priority priority) throws Exception {
        try {
            if (tasksRepo.existsById(taskId)) {
                tasksRepo.updateTaskPriority(priority, containerId, taskId);
                return;
            }
            throw new NotFoundException("");
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<TaskDto> getAllTasksByContainerId(Long id) {
        try{
            return taskMapper.toDtos(tasksRepo.getAllTasksByContainerId(id));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}


