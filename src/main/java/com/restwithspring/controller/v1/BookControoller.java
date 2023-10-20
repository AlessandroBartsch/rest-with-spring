package com.restwithspring.controller.v1;

import com.restwithspring.model.vo.BookDTO;
import com.restwithspring.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.restwithspring.controller.v1.RestPath.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH + "/book")
@Tag(name = "Livros", description = "Api para gestão de livros")
public class BookControoller {

    @Autowired
    private BookService service;




    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<BookDTO> getAll() {
        var responseDTO = service.getAll();
        return responseDTO;
    }




    @GetMapping(value = "/{id}",
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public BookDTO getById(
            @PathVariable(value = "id") Long id
    ) {
        var responseDto = service.getById(id);
        return responseDto;
    }




    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public BookDTO create(@RequestBody BookDTO dto) {
        return service.create(dto);
    }




    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public BookDTO update(BookDTO dto) {
        return service.update(dto);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
