package com.konganime.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Todo {

    private final UUID id;
    @NotBlank
    private final String name;
    private final boolean is_done;

    public Todo(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("is_done") boolean is_done) {
        this.id = id;
        this.name = name;
        this.is_done = is_done;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isIs_done() {
        return is_done;
    }
}
