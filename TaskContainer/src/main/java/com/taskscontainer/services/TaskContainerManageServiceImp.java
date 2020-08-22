package com.taskscontainer.services;

import com.taskscontainer.DTOs.TaskDto;
import com.taskscontainer.DTOs.TasksContainerDto;
import com.taskscontainer.Enums.Priority;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskContainerManageServiceImp implements TaskContainerManageService {
    private TaskContainerService taskContainerService;
    private TasksService tasksService;
    private Map<Long, Map<Long,TaskDto>> tasksContainerMap = new HashMap<>();


    @Autowired
    public TaskContainerManageServiceImp(TaskContainerService taskContainerService,TasksService tasksService)
    {
        this.taskContainerService = taskContainerService;
        this.tasksService = tasksService;
        populateTaskContainerMap();
    }

    private void populateTaskContainerMap() {
        List<TasksContainerDto> taskContainerDtos = taskContainerService.getAllContainers();
        for(TasksContainerDto tasksContainerDto : taskContainerDtos)
        {
            List<TaskDto> tasksDtosList = tasksService.getAllTasksByContainerId(tasksContainerDto.getId());
            Map<Long, TaskDto> tasksMap = getMapFromList(tasksDtosList);
            tasksContainerMap.put(tasksContainerDto.getId(),tasksMap);
        }
    }



    @Override
    public void createTasksContainer(TasksContainerDto taskContainerDto) {
        try{
            taskContainerService.createTasksContainer(taskContainerDto);
            tasksContainerMap.put(taskContainerDto.getId(),new HashMap<Long, TaskDto>());
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void deleteContainer(Long containerId) throws Exception {
        try{
            tasksService.deleteAllTasksByContainerId(containerId);
            taskContainerService.deleteContainer(containerId);
            tasksContainerMap.remove(containerId);
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    @Override
    public Long createTask(TaskDto taskDto) throws Exception {
        try {
            if(taskContainerService.isContainerExists(taskDto.getTaskContainerId())) {
                Long id = tasksService.createTask(taskDto);
                tasksContainerMap.get(taskDto.getTaskContainerId()).put(id,taskDto);
                return id;
            }
         return null;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void updateTaskById(Long id, TaskDto task) throws NotFoundException {
        try {
            tasksService.updateTaskById(id, task);
            Map<Long, TaskDto> taskDtos = tasksContainerMap.get(task.getTaskContainerId());
            taskDtos.put(id, task);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void updateTaskPriority(Long containerId, Long id, Priority priority) throws Exception {
        {
            try {
                tasksService.updateTaskPriority(containerId,id,priority);
                Map<Long, TaskDto> taskDtos = tasksContainerMap.get(id);
                taskDtos.get(id).setPriority(priority);
            }
            catch (Exception e)
            {
                throw e;
            }
        }
    }

    @Override
    public void deleteTask(Long containerId, Long taskId) throws Exception {
        try {
            tasksService.deleteTask(containerId,taskId);
            tasksContainerMap.get(containerId).remove(taskId);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public TaskDto getTask(Long containerId, Long taskId) {
       return tasksContainerMap.get(containerId).get(taskId);
    }

    @Override
    public void deleteAllTasksByContainerId(long id) throws Exception {
        try {
            tasksService.deleteAllTasksByContainerId(id);
            taskContainerService.deleteContainer(id);
            tasksContainerMap.remove(id);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<TaskDto> getAllTasksByContainerId(Long id) {
        if (tasksContainerMap.containsKey(id)) {
            Map<Long, TaskDto> tasksContainerDtos = tasksContainerMap.get(id);
            List<TaskDto> tasksDtos = new ArrayList<>(tasksContainerDtos.values());
            Collections.sort(tasksDtos);
            return tasksDtos;
        }
        return new ArrayList<>();
    }

    private Map<Long, TaskDto> getMapFromList(List<TaskDto> tasksDtosList) {
        return tasksDtosList.stream()
                .collect(Collectors.toMap(TaskDto::getId, taskDto -> taskDto));
    }

}
