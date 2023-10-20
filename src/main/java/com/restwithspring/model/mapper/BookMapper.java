package com.restwithspring.model.mapper;

import com.restwithspring.model.Book;
import com.restwithspring.model.vo.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToDTO(Book book);

    Book dtoToBook(BookDTO bookDTO);

    List<Book> dtosToBooks(List<BookDTO> bookDTOS);

    List<BookDTO> booksToDtos(List<Book> books);
}
