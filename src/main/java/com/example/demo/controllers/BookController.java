package com.example.demo.controllers;


import com.example.demo.domain.Book;
import com.example.demo.domain.DTO.BookDto;
import com.example.demo.mappers.Mapper;
import com.example.demo.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private Mapper<Book, BookDto> bookMapper;

    private BookService bookService;

    public BookController(Mapper<Book, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn , @RequestBody BookDto bookDto) {

        Book book = bookMapper.mapFrom(bookDto);
        Book savedBook =  bookService.createUpdateBook(isbn, book);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);
        if(bookService.isExist(isbn)){

            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);



        }

        else{

            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);


        }




    }

    @GetMapping("books")
    public List<BookDto> getAllBooks() {
        List<Book> books  =  bookService.findAll();

        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());

    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<Book> foundBook = bookService.findOne(isbn);

        return foundBook.map(book -> {
            BookDto bookDto = bookMapper.mapTo(book);
            return ResponseEntity.ok(bookDto);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
