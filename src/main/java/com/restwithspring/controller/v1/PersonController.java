package com.restwithspring.controller.v1;

import java.util.List;
import java.util.concurrent.atomic.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restwithspring.model.PersonDTO;
import com.restwithspring.service.PersonService;

import static com.restwithspring.controller.v1.RestPath.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH + "/person")
public class PersonController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private PersonService service;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> getAll() {
        return service.findAll();
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO getById(
            @PathVariable(value = "id") Long id) {

        return service.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(
            @RequestBody PersonDTO person) {

        return service.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(
            @RequestBody PersonDTO person) {

        var response = service.update(person);
        return  response;
    }

    @DeleteMapping(
            value = "/{id}"
    )
    public ResponseEntity<?> delete(
            @PathVariable(value = "id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
