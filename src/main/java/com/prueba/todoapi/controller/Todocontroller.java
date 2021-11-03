package com.prueba.todoapi.controller;

import com.prueba.todoapi.entities.Todo;
import com.prueba.todoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class Todocontroller {

    @Autowired
    TodoRepository todoRepository;

    @PostMapping
    public Todo newTodo(@RequestBody Todo todo){
        return this.todoRepository.save(todo);
    }


    @GetMapping("/all")
    public List<Todo> getTodos(){
        return this.todoRepository.findAll();
    }

    @GetMapping("/{todoId}")
    public Optional<Todo> getTodo(@PathVariable("todoId") Long todoId){
        var todo = todoRepository.findById(todoId);
        return  todo;
    }
    
    @PutMapping("/{todoId}")
    public Optional<Todo> updateTodo(@PathVariable("todoId") Long todoId,@RequestBody Todo uptodo){
        return this.todoRepository.findById(todoId)
                .map(oldTodo -> this.todoRepository.save(uptodo));
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable("todoId") Long todoId){
        this.todoRepository.deleteById(todoId);
    }

}
