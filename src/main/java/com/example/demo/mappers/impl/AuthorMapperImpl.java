package com.example.demo.mappers.impl;

import com.example.demo.domain.Author;
import com.example.demo.domain.DTO.AuthorDto;
import com.example.demo.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<Author, AuthorDto> {


    private ModelMapper modelMapper ;
    public AuthorMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;

    }



    @Override
    public AuthorDto mapTo(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public Author mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
