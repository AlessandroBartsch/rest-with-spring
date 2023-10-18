package com.restwithspring.service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.restwithspring.model.Person;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    protected static final Logger logger = LogManager.getLogger(PersonService.class);

    public List<Person> findAll() {
        var persons = new ArrayList<Person>();

        for (var i = 0; i < 8; i++) {
            var person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int id) {
        var person = new Person();
        person.setId((long) id);
        person.setFirstName("Alessandro");
        person.setLastName("Bartsch");
        person.setAddress("Jarivatuba - IN");
        person.setGender("Male");

        return person;
    }

    public Person findById(String id) {
        logger.info("Finding one Person!");

        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Alessandro");
        person.setLastName("Bartsch");
        person.setAddress("Jarivatuba - IN");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person) {
        var personCreate = new Person();

        personCreate.setId(person.getId());
        personCreate.setFirstName(person.getFirstName());
        personCreate.setLastName(person.getLastName());
        personCreate.setAddress(person.getAddress());
        personCreate.setGender(person.getGender());

        return personCreate;
    }

    public Person update(Person person) {
        var personCreate = new Person();

        personCreate.setId(person.getId());
        personCreate.setFirstName(person.getFirstName());
        personCreate.setLastName(person.getLastName());
        personCreate.setAddress(person.getAddress());
        personCreate.setGender(person.getGender());

        return personCreate;
    }

    public void delete(String id) {

    }
}
