package com.restwithspring.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restwithspring.model.Person;
import com.restwithspring.model.PersonDTO;
import com.restwithspring.model.mapper.PersonMapper;
import com.restwithspring.repository.PersonRepository;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    protected static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        var response = PersonMapper.INSTANCE.personsToDTOs(repository.findAll());
        return response;
    }

    public PersonDTO findById(Long id) {
        var repsonse = repository.findById(id)
                .orElseThrow();
        return PersonMapper.INSTANCE.personToDTO(repsonse);
    }

    public PersonDTO create(PersonDTO person) {

        var entity = PersonMapper.INSTANCE.DTOToPerson(person);
        var response = repository.save(entity);

        var responseVO = PersonMapper.INSTANCE.personToDTO(response);

        return responseVO;
    }

    public PersonDTO update(PersonDTO person) {
        var entity = repository.findById(person.getId())
                .orElseThrow();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        var entityResponse = repository.save(entity);

        return PersonMapper.INSTANCE.personToDTO(entityResponse);
    }

    public void delete(Long id) {

        var entity = repository.findById(id)
                .orElseThrow();


        repository.delete(entity);
    }
}
