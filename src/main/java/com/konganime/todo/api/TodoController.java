package com.konganime.todo.api;

import com.konganime.todo.model.Todo;
import com.konganime.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public void addTodo(@Valid @NonNull @RequestBody Todo todo) {
        todoService.addTodo(todo);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping(path = "{id}")
    public Todo getTodoById(@PathVariable("id") UUID id) {
        return todoService.getTodoById(id).orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateTodo(@PathVariable("id") UUID id,
                           @Valid @NonNull @RequestBody Todo todo) {
        todoService.updateTodo(id, todo);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTodo(@PathVariable("id") UUID id) {
        todoService.deleteTodo(id);
    }
}
