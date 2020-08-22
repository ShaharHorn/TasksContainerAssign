package com.taskscontainer.services;

import com.taskscontainer.DTOs.TaskDto;
import com.taskscontainer.Enums.Priority;
import com.taskscontainer.mappers.TaskMapper;
import com.taskscontainer.repos.TasksRepo;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TasksServiceImpTest {

    @InjectMocks
    TasksServiceImp tasksService;

    @Mock
    TasksRepo tasksRepo;

    @Mock
    TaskMapper taskMapper;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateTaskByIdKeyAlreadyExistsExeption() {
        Long id = 1L;
        TaskDto taskDto = new TaskDto();

        when(tasksRepo.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class,
                () -> {
                    tasksService.updateTaskById(id, taskDto);
                });

    }

    @Test
    public void deleteTaskThrowNotFoundExeption() {
        Long taskId = 1L;
        Long containerId = 1L;

        when(tasksRepo.existsById(taskId)).thenReturn(false);

        assertThrows(NotFoundException.class,
                ()->  {
                    tasksService.deleteTask(containerId,taskId);
                });
    }

    @Test
    public void updateTaskPriority() {
        {
            Long taskId = 1L;
            Long containerId = 1L;

            when(tasksRepo.existsById(taskId)).thenReturn(false);

            assertThrows(NotFoundException.class,
                            () -> {
                        tasksService.updateTaskPriority(containerId,taskId, Priority.HIGH);
                    });

        }
    }

}