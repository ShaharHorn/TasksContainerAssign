package com.taskscontainer.services;

import com.taskscontainer.DTOs.TaskDto;
import com.taskscontainer.DTOs.TasksContainerDto;
import com.taskscontainer.Entities.TaskContainerEntity;
import com.taskscontainer.mappers.TasksContainerMapper;
import com.taskscontainer.repos.TaskContainerRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskContainerServiceImp implements TaskContainerService {
    TaskContainerRepo taskContainerRepo;
    TasksContainerMapper tasksContainerMapper;
    private final Map<Long, ArrayList<TaskDto>> tasksContainerMap = new HashMap<>();

    @Autowired
    public TaskContainerServiceImp(TaskContainerRepo taskContainerRepo, TasksContainerMapper tasksContainerMapper) {
        this.taskContainerRepo = taskContainerRepo;
        this.tasksContainerMapper = tasksContainerMapper;
    }


    @Override
    public TasksContainerDto getById(long id) {
        try {
            TaskContainerEntity dto = taskContainerRepo.findById(id).get();
            TasksContainerDto entity = tasksContainerMapper.toDto(dto);
            return entity;
        } catch (NoSuchElementException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void createTasksContainer(TasksContainerDto tasksContainerDto) {
        try {
            TaskContainerEntity entity = tasksContainerMapper.toEntity(tasksContainerDto);
            if (taskContainerRepo.existsById(entity.getId()))
                throw new IllegalArgumentException("");
            taskContainerRepo.save(entity);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteContainer(Long containerId) throws Exception {
        try {
            boolean isFound = taskContainerRepo.existsById(containerId);
            if (isFound) {
                taskContainerRepo.deleteById(containerId);
                return;
            }
            throw new NotFoundException("");
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean isContainerExists(Long id) {
        return taskContainerRepo.existsById(id);
    }

    @Override
    public List<TasksContainerDto> getAllContainers() {
        try {
            List<TaskContainerEntity> entities = (List<TaskContainerEntity>) taskContainerRepo.findAll();
            return tasksContainerMapper.toDtos(entities);
        } catch (Exception e) {
            throw e;
        }
    }
}


