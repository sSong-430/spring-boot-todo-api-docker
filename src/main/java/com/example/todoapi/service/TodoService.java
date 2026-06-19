package com.example.todoapi.service;

import com.example.todoapi.dto.CreateTodoRequest;
import com.example.todoapi.dto.UpdateTodoRequest;
import com.example.todoapi.model.Todo;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    private final Map<Long, Todo> todos = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public List<Todo> findAll() {
        return new ArrayList<>(todos.values())
                .stream()
                .sorted(Comparator.comparing(Todo::getId))
                .collect(Collectors.toList());
    }

    public Todo findById(Long id) {
        Todo todo = todos.get(id);
        if (todo == null) {
            throw new NoSuchElementException("Todo not found. id=" + id);
        }
        return todo;
    }

    public Todo create(CreateTodoRequest request) {
        Long id = sequence.getAndIncrement();
        Todo todo = new Todo(id, request.getTitle());
        todos.put(id, todo);
        return todo;
    }

    public Todo update(Long id, UpdateTodoRequest request) {
        Todo todo = findById(id);

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            todo.setTitle(request.getTitle());
        }
        if (request.getCompleted() != null) {
            todo.setCompleted(request.getCompleted());
        }
        return todo;
    }

    public void delete(Long id) {
        Todo removed = todos.remove(id);
        if (removed == null) {
            throw new NoSuchElementException("Todo not found. id=" + id);
        }
    }
}
