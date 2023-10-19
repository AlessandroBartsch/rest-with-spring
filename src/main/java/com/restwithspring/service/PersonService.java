package com.restwithspring.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restwithspring.model.Person;
import com.restwithspring.repository.PersonRepository;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    protected static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        var entity = repository.findById(person.getId())
                .orElseThrow();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow();

        repository.delete(entity);
    }
}
