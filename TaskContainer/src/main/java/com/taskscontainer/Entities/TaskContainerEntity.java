package com.taskscontainer.Entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class TaskContainerEntity {
    @Id
    Long id;

    @NotNull
    @Column()
    @Size(max = 250)
    String name;

}
