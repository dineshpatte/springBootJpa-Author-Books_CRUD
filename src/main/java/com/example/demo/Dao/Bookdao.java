package com.example.demo.Dao;

import com.example.demo.domain.Book;

import java.util.Optional;

public interface Bookdao {

    void createBook(Book book);

    Optional<Book> find(String isbn);
}
