package com.example.demo.daoImpl;


import com.example.demo.Dao.impl.AuthordaoImpl;
import com.example.demo.Dao.impl.BookDaoImpl;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class BookDaoImplIntegrationTest {

    private BookDaoImpl underTest;

    private AuthordaoImpl  authordao;
    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest,AuthordaoImpl authordao) {
        this.underTest = underTest;
        this.authordao = authordao;
    }

    @Test
    public void ChcekWhetherBookExists() {
        Author author = TestDataUtil.CreateTestAuthor();
        authordao.createAuthor(author);
        Book book = BookDataUtil.CreateTestBook();
        book.setAuthor_id(author.getId());



        underTest.createBook(book);
        Optional<Book> result = underTest.find(book.getIsbn());
        assertThat(result.isPresent()).isTrue();



    }

    @Test
    public void FindMantTest(){

    }
}
