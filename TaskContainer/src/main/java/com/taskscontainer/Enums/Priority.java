package com.taskscontainer.Enums;

import lombok.Getter;

@Getter
public enum Priority {
    LOW(0) ,
    MEDIUM(1) ,
    HIGH(2);

    private Integer value;

    Priority(Integer value) {
        this.value = value;
    }
}
