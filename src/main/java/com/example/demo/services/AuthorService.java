package com.example.demo.services;

import com.example.demo.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

   List <Author> findAll();

    Author createAUthor(Author author);

    Optional<Author> findOne(Long id);

    boolean isExist(Long id);
}
