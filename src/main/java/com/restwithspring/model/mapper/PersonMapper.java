package com.restwithspring.model.mapper;

import javax.swing.*;
import java.util.List;

import com.restwithspring.model.vo.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.restwithspring.model.Person;


@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "id", target = "personId")
    PersonDTO personToDTO(Person person);

    @Mapping(source = "personId", target = "id")
    Person DTOToPerson(PersonDTO personDTO);

    List<Person> DTOsToPersons(List<PersonDTO> personDTOList);

    List<PersonDTO> personsToDTOs(List<Person> personList);
}
