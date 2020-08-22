package com.taskscontainer.controllers;

import com.taskscontainer.DTOs.TaskDto;
import com.taskscontainer.DTOs.TasksContainerDto;
import com.taskscontainer.Enums.Priority;
import com.taskscontainer.services.TaskContainerManageService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TasksController {
    private TaskContainerManageService taskContainerManageService;

    @Autowired
    public TasksController(TaskContainerManageService taskContainerManageService)
    {
        this.taskContainerManageService = taskContainerManageService;
    }

    @PostMapping("/createTask")
    public ResponseEntity<Long> createTask(@Valid @RequestBody TaskDto taskDto) throws Exception {
        try {
            Long id = taskContainerManageService.createTask(taskDto);
            if (id != null) {
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/updateTaskById/{id}")
    public ResponseEntity<Void> updateTaskById(@PathVariable Long id,@Valid @RequestBody TaskDto taskDto)
    {
        try{
            taskContainerManageService.updateTaskById(id,taskDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTasksByContainerId/{id}")
    public ResponseEntity<List<TaskDto>> getAllTasksByContainerId(@PathVariable Long id)
    {
        try {
            List<TaskDto> tasksDto = taskContainerManageService.getAllTasksByContainerId(id);
            return new ResponseEntity<>(tasksDto,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTask/{containerId}/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long containerId,@PathVariable Long taskId)
    {
        try {
            TaskDto dto = taskContainerManageService.getTask(containerId, taskId);
            if (dto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateTaskPriority/{containerId}/{id}")
    public ResponseEntity<Void> updateTaskPriority(@PathVariable Long containerId, @PathVariable Long id, @RequestBody Priority priority)
    {
        try{
           taskContainerManageService.updateTaskPriority(containerId, id, priority);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteTask/{containerId}/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long containerId,@PathVariable Long taskId)
    {
        try {
            taskContainerManageService.deleteTask(containerId,taskId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createTaskContainer")
    public ResponseEntity<Void> createTaskContainer(@Valid @RequestBody TasksContainerDto tasksContainerDto)
    {
        try{
            taskContainerManageService.createTasksContainer(tasksContainerDto);
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTasksContainer/{containerId}")
    public ResponseEntity<Void> deleteTasksContainer(@PathVariable Long containerId)
    {
        try {
            taskContainerManageService.deleteContainer(containerId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
