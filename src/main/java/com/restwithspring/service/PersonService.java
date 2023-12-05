package com.restwithspring.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.restwithspring.controller.v1.PersonController;
import com.restwithspring.model.vo.PersonDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restwithspring.model.Person;
import com.restwithspring.model.mapper.PersonMapper;
import com.restwithspring.repository.PersonRepository;

@Service
public class PersonService {

    protected static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        var response = PersonMapper.INSTANCE.personsToDTOs(repository.findAll());

        response.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).getAll()).withSelfRel()));

        return response;
    }

    public PersonDTO findById(Long id) {
        var repsonse = repository.findById(id)
                .orElseThrow();
        var responseDto = PersonMapper.INSTANCE.personToDTO(repsonse);


        responseDto.add(linkTo(methodOn(PersonController.class).getById(id)).withSelfRel());


        return responseDto;
    }

    public PersonDTO create(PersonDTO person) {

        var entity = PersonMapper.INSTANCE.DTOToPerson(person);
        var response = repository.save(entity);

        var responseVO = PersonMapper.INSTANCE.personToDTO(response);

        responseVO.add(linkTo(methodOn(PersonController.class).create(person)).withSelfRel());

        return responseVO;
    }

    public PersonDTO update(PersonDTO person) {
        var entity = repository.findById(person.getPersonId())
                .orElseThrow();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var entityResponse = repository.save(entity);

        var response = PersonMapper.INSTANCE.personToDTO(entityResponse);
        response.add(linkTo(methodOn(PersonController.class).update(person)).withSelfRel());

        return response;
    }

    public void delete(Long id) {

        var entity = repository.findById(id)
                .orElseThrow();


        repository.delete(entity);
    }
}
