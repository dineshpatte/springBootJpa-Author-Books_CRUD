package com.example.demo.services;

import com.example.demo.domain.Book;
import com.example.demo.domain.DTO.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createUpdateBook(String isbn , Book book);

    List<Book> findAll();

    Optional<Book> findOne(String isbn);

    boolean isExist(String isbn);
}
