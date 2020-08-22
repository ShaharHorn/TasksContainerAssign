package com.taskscontainer.repos;

import com.taskscontainer.Entities.TaskContainerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskContainerRepo extends CrudRepository<TaskContainerEntity,Long> {
}

