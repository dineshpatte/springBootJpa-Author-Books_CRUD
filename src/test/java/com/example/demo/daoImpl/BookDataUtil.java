package com.example.demo.daoImpl;

import com.example.demo.domain.Book;

public  final class BookDataUtil {

    private BookDataUtil() {

    }


    static Book CreateTestBook() {
        return Book.builder()
                .isbn("g54rtdr")
                .title("atomic habits")
                .author_id(1L)
                .build();
    }
}
