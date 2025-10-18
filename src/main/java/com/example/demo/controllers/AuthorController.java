package com.example.demo.controllers;

import com.example.demo.domain.Author;
import com.example.demo.domain.DTO.AuthorDto;
import com.example.demo.mappers.Mapper;
import com.example.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    private Mapper<Author, AuthorDto>  mapper;

    public AuthorController(AuthorService authorService , Mapper<Author, AuthorDto> mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> CreateAuthor(@RequestBody AuthorDto author) {
        Author author1 = mapper.mapFrom(author);
        Author savedAuthor = authorService.createAUthor(author1);

        return new ResponseEntity<>(mapper.mapTo(savedAuthor), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> getAuthors() {
         List<Author> authors =  authorService.findAll();

         return authors.stream()
                 .map(mapper::mapTo).collect(Collectors.toList());


    }

    @GetMapping(path = "/authors/{id}")

    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
           Optional<Author> foundAuthor =  authorService.findOne(id);

          return  foundAuthor.map(Author->{
               AuthorDto authorDto = mapper.mapTo(Author);
               return new ResponseEntity<>(authorDto, HttpStatus.OK);
           }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        if(!authorService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

            authorDto.setId(id);
        Author author = mapper.mapFrom(authorDto);


      Author SavedAuthor =   authorService.createAUthor(author);

           return new ResponseEntity<>(mapper.mapTo(SavedAuthor), HttpStatus.OK);
    }


}
