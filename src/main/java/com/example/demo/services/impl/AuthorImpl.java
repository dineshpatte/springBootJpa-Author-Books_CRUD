package com.example.demo.services.impl;

import com.example.demo.domain.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.services.AuthorService;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
      return (List<Author>) authorRepository.findAll();
    }

    @Override
    public Author createAUthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> findOne(Long id) {

        return authorRepository.findById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return authorRepository.existsById(id);
    }
}
