package com.konganime.todo.dao;

import com.konganime.todo.model.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoDao {

    int insertTodo(UUID id, Todo todo);
    Optional<Todo> selectTodoById(UUID id);
    List<Todo> selectAllTodos();
    int updateTodo(UUID id, Todo todo);
    int deleteTodo(UUID id);
}
