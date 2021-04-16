package com.otboss.todo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TodoListItems")
public class TodoListItem {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userId", nullable = false, columnDefinition = "BIGINT")
    private Long userId;
    @Column(name = "checked", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean checked = false;
    @Column(name = "entry", nullable = false)
    private String entry;
    @Column(name = "timestamp", nullable = false)
    private Long timestamp = (new Date(System.currentTimeMillis())).getTime();

    public TodoListItem() {
    }

    public TodoListItem(String entry, Long userId) {
        this.entry = entry;
        this.userId = userId;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getEntry() {
        return this.entry;
    }

    public Boolean getChecked() {
        return this.checked;
    }

}
