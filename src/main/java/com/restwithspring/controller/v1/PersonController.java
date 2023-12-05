package com.restwithspring.controller.v1;

import java.util.List;
import java.util.concurrent.atomic.*;

import com.restwithspring.model.vo.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restwithspring.service.PersonService;

import static com.restwithspring.controller.v1.RestPath.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH + "/person")
@Tag(name = "Pessoa", description = "API para controle de pessoas.")
public class PersonController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private PersonService service;

    @CrossOrigin(origins = {"https://localhost:8080", "https://navoz.com.br"})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(
        summary = "Buscar todos",
        description = "Retorna todas as pessoas cadastradas",
        tags = {"Pessoa"},
        responses = {
            @ApiResponse(description = "Suxesso", responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "403", content = {@Content}),
            @ApiResponse(description = "Internal error", responseCode = "500", content = {@Content})
        }
    )
    public List<PersonDTO> getAll() {
        return service.findAll();
    }



    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(
        summary = "Buscar por ID",
        description = "Retorna a pessoa cadastrada pelo ID",
        tags = {"Pessoa"},
        responses = {
            @ApiResponse(description = "Suxesso", responseCode = "200",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "403", content = {@Content}),
            @ApiResponse(description = "Internal error", responseCode = "500", content = {@Content})
        }
    )
    public PersonDTO getById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }



    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(
        summary = "Cria uma pessoa",
        description = "Cria uma nova pessoa no sistema",
        tags = {"Pessoa"},
        responses = {
            @ApiResponse(description = "Created", responseCode = "201",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "403", content = {@Content}),
            @ApiResponse(description = "Internal error", responseCode = "500", content = {@Content})
        }
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }



    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(
        summary = "Atualizar",
        description = "Atualiza uma pessoa no sistema",
        tags = {"Pessoa"},
        responses = {
            @ApiResponse(description = "Updated", responseCode = "200",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "403", content = {@Content}),
            @ApiResponse(description = "Internal error", responseCode = "500", content = {@Content})
        }
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        var response = service.update(person);
        return  response;
    }


    
    @DeleteMapping(value = "/{id}")
    @Operation(
        summary = "Deletes a Pessoa",
        description = "Deletar uma pessoa, ou seja.... CPF cancelado",
        tags = {"Pessoa"},
        responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "403", content = {@Content}),
            @ApiResponse(description = "Internal error", responseCode = "500", content = {@Content})
        }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
