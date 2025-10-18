package com.example.demo.repositories;

import com.example.demo.domain.Author;

public  final class   TestDataUtil {

    private  TestDataUtil(){}

    public static Author CreateTestAuthor() {
        return Author.builder()
                .name("dinesh")
                .age(18)
                .build(); // no .id()
    }

    public static Author CreateTestAuthor1() {
        return Author.builder()
                .name("pogba")
                .age(22)
                .build(); // no .id()
    }

    static Author CreateTestAuthor2() {
        return Author.builder()
                .name("bruno")
                .age(28)
                .build(); // no .id()
    }



}
