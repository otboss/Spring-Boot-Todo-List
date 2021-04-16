package com.otboss.todo.utility;

import java.util.ArrayList;

import com.otboss.todo.model.TodoListItem;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageTodoListItem {
    private ArrayList<TodoListItem> content = new ArrayList<TodoListItem>();
    private Pageable PageableObject;
    private float totalPages;
    private float totalElements;
    private boolean last;
    private float size;
    private float number;
    private Sort SortObject;
    private float numberOfElements;
    private boolean first;
    private boolean empty;

    public ArrayList<TodoListItem> getContent() {
        return this.content;
    }

    public Pageable getPageable() {
        return PageableObject;
    }

    public float getTotalPages() {
        return totalPages;
    }

    public float getTotalElements() {
        return totalElements;
    }

    public boolean getLast() {
        return last;
    }

    public float getSize() {
        return size;
    }

    public float getNumber() {
        return number;
    }

    public Sort getSort() {
        return SortObject;
    }

    public float getNumberOfElements() {
        return numberOfElements;
    }

    public boolean getFirst() {
        return first;
    }

    public boolean getEmpty() {
        return empty;
    }

    // Setter Methods

    public void setPageable(Pageable pageableObject) {
        this.PageableObject = pageableObject;
    }

    public void setTotalPages(float totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(float totalElements) {
        this.totalElements = totalElements;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public void setSort(Sort sortObject) {
        this.SortObject = sortObject;
    }

    public void setNumberOfElements(float numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
