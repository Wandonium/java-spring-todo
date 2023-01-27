package com.konganime.todo.dao;

import com.konganime.todo.model.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class TodoDataAccess implements TodoDao {

    private final JdbcTemplate jdbcTemplate;

    public TodoDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTodo(UUID id, Todo todo) {
        return jdbcTemplate.update("INSERT INTO todo VALUES(?,?,?)",
                id, todo.getName(), todo.isIs_done());
    }

    @Override
    public Optional<Todo> selectTodoById(UUID id) {
        final String sql = "SELECT * FROM todo WHERE id = ?";
        Todo todo = jdbcTemplate.queryForObject(
                sql,
                new Object[] {id},
                (resultSet, i) -> {
                    UUID todoId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    boolean done = resultSet.getBoolean("is_done");
                    return new Todo(todoId, name, done);
                }
        );
        return Optional.ofNullable(todo);
    }

    @Override
    public List<Todo> selectAllTodos() {
        final String sql = "SELECT * FROM todo";
        List<Todo> todos = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            boolean done = resultSet.getBoolean("is_done");
            return new Todo(id, name, done);
        });
        return todos;
    }

    @Override
    public int updateTodo(UUID id, Todo todo) {
        return jdbcTemplate.update("UPDATE todo SET name = ?, is_done = ? WHERE id = ?",
                todo.getName(), todo.isIs_done(), id);
    }

    @Override
    public int deleteTodo(UUID id) {
        final String sql = "DELETE FROM todo WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args);
    }
}
