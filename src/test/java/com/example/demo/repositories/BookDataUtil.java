package com.example.demo.repositories;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;

public  final class BookDataUtil {

    private BookDataUtil() {

    }


   public  static Book CreateTestBook(final Author author) {
        return Book.builder()
                .isbn("g54rtdr")
                .title("atomic habits")
                .author(author)
                .build();
    }

  public   static Book CreateTestBook1(final Author author) {
        return Book.builder()
                .isbn("22c345")
                .title("hajime no ippo")
                .author(author)
                .build();
    }

    static Book CreateTestBook2(final Author author) {
        return Book.builder()
                .isbn("g367ygtf")
                .title("kawasaki")
                .author(author)
                .build();
    }
}
