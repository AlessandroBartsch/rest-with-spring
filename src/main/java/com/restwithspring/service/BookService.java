package com.restwithspring.service;

import com.restwithspring.model.Book;
import com.restwithspring.model.mapper.BookMapper;
import com.restwithspring.model.vo.BookDTO;
import com.restwithspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookDTO> getAll() {
        var response = BookMapper.INSTANCE.booksToDtos(repository.findAll());
        return response;
    }

    public BookDTO getById(Long id) {
        var responseDto = BookMapper.INSTANCE.bookToDTO(repository.findById(id).orElseThrow());
        return responseDto;
    }

    public BookDTO create(BookDTO dto) {
        var entity = repository.save(BookMapper.INSTANCE.dtoToBook(dto));
        return BookMapper.INSTANCE.bookToDTO(entity);
    }

    public BookDTO update(BookDTO dto) {
        var toUpdate = repository.findById(dto.getId()).orElseThrow();


        toUpdate.setId(dto.getId());
        toUpdate.setTitle(dto.getTitle());
        toUpdate.setAuthor(dto.getAuthor());
        toUpdate.setPrice(dto.getPrice());
        toUpdate.setLaunch_date(dto.getLaunch_date());

        var updated = repository.save(toUpdate);
        return BookMapper.INSTANCE.bookToDTO(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
