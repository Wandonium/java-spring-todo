package com.konganime.todo.service;

import com.konganime.todo.dao.TodoDao;
import com.konganime.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoDao todoDao;

    @Autowired
    public TodoService(@Qualifier("postgres") TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public int addTodo(Todo todo) {
        UUID id = UUID.randomUUID();
        return todoDao.insertTodo(id, todo);
    }

    public List<Todo> getAllTodos() {
        return todoDao.selectAllTodos();
    }

    public Optional<Todo> getTodoById(UUID id) {
        return todoDao.selectTodoById(id);
    }

    public int updateTodo(UUID id, Todo todo) {
        return todoDao.updateTodo(id, todo);
    }

    public int deleteTodo(UUID id) {
        return todoDao.deleteTodo(id);
    }
}
