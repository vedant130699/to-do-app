package com.vedant.todo.controller;

import com.vedant.todo.entity.Todo;
import com.vedant.todo.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

    private TodoRepository todoRepository;

    @Autowired
    TodoController(TodoRepository theTodoRepository){
        todoRepository = theTodoRepository;
    }

    @GetMapping("/tasks")
    public String listTodos(Model model){
        model.addAttribute("todos", todoRepository.findAll());
        return "list-tasks";
    }
    @GetMapping("/tasks/new")
    public String showForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "task-form";
    }

    @PostMapping("/tasks")
    public String save(@Valid @ModelAttribute Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "task-form";
        }

        todoRepository.save(todo);
        return "redirect:/tasks";
    }
}
