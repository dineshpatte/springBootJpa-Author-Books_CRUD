package com.example.demo.services.impl;

import com.example.demo.domain.Book;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createUpdateBook(String isbn ,Book book){

        book.setIsbn(isbn);
      return   bookRepository.save(book);

    }

    @Override
    public List<Book> findAll() {
        List<Book> books = (List<Book>) bookRepository.findAll();

        return (List<Book>) books;


    }

    @Override
    public Optional<Book> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExist(String isbn) {
        return bookRepository.existsById(isbn);
    }


}
