package com.otboss.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TodoListItem")
public class TodoListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userId")
    private final Long userId;
    @Column(name = "checked")
    private Boolean checked = false;
    @Column(name = "entry")
    private String entry;

    public TodoListItem(String entry, Long userId) {
        this.entry = entry;
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getEntry() {
        return this.entry;
    }

    public Boolean getIsChecked() {
        return this.checked;
    }

}
