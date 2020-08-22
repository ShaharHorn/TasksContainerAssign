package com.taskscontainer.repos;

import com.taskscontainer.Entities.TaskEntity;
import com.taskscontainer.Enums.Priority;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface TasksRepo extends CrudRepository<TaskEntity,Long> {

   @Transactional
   @Modifying
   @Query("UPDATE #{#entityName} SET priority = (:priority) where id = (:id) and task_container_id = (:containerId)")
   void updateTaskPriority(@Param("priority")Priority priority,@Param("containerId") Long containerId,@Param("id") Long id );

   @Transactional
   @Modifying
   @Query("DELETE FROM #{#entityName} WHERE task_container_id = (:id) ")
   void deleteAllTasksByContainerId(@Param("id") Long id);

   @Transactional
   @Modifying
   @Query("FROM #{#entityName} WHERE task_container_id = (:id) order by priority DESC")
   List<TaskEntity> getAllTasksByContainerId(@Param("id") Long id);
}
