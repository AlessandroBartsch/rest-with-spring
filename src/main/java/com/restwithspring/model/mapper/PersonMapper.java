package com.restwithspring.model.mapper;

import javax.swing.*;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.restwithspring.model.Person;
import com.restwithspring.model.PersonDTO;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO personToDTO(Person person);

    Person DTOToPerson(PersonDTO personDTO);

    List<Person> DTOsToPersons(List<PersonDTO> personDTOList);

    List<PersonDTO> personsToDTOs(List<Person> personList);
}
