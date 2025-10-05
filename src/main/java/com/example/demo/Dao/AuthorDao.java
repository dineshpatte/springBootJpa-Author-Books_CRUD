package com.example.demo.Dao;

import com.example.demo.domain.Author;

import java.util.Optional;

public interface AuthorDao {

    void createAuthor(Author author);

   Optional<Author> findOne(Long id);


}
