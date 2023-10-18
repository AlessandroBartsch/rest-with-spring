package com.restwithspring.controller;

import java.util.List;
import java.util.concurrent.atomic.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.restwithspring.model.Person;
import com.restwithspring.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private PersonService service;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> getAll() {
        return service.findAll();
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person getById(
            @PathVariable(value = "id") String id) {

        return service.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(
            @RequestBody Person person) {

        return service.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(
            @RequestBody Person person) {

        return service.update(person);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    public void delete(
            @PathVariable(value = "id") String id) {

        service.delete(id);
    }

}
