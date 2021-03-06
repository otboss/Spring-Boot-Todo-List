package com.otboss.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoListItemDto {
    private Long id;
    private Boolean checked;
    private String entry;
    private Long timestamp;
}
