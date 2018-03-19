package com.virtusa.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.comments.entities.User;
import com.virtusa.comments.rest.repository.UserRepository;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User find(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }
    @GetMapping(params = "email")
    public User findByEmail(@RequestParam(value="email") String email) {
    	return repository.findAllByEmail(email);
    }


    @PostMapping(consumes = "application/json")
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }
    @GetMapping(path = "/deleteall")
    public void deleteAll() {
        repository.deleteAll();
    }
    @PutMapping(path = "/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) throws BadHttpRequest {
        if (repository.exists(id)) {
            user.setId(id);
            return repository.save(user);
        } else {
            throw new BadHttpRequest();
        }
    }

}
