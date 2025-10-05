package com.example.demo.daoImpl;

import com.example.demo.domain.Author;

public  final class   TestDataUtil {

    private  TestDataUtil(){}

    static Author CreateTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("dinesh")
                .age(18)
                .build();
    }


}
