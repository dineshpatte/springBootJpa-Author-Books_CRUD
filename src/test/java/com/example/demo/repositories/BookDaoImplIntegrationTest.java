package com.example.demo.repositories;



import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class BookDaoImplIntegrationTest {

    private BookRepository underTest;

    private AuthorRepository  authordao;
    @Autowired
    public BookDaoImplIntegrationTest(BookRepository underTest,AuthorRepository authordao) {
        this.underTest = underTest;
        this.authordao = authordao;
    }

    @Test
    public void ChecWHetherBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthor();
        authordao.save(author);

        Book book = BookDataUtil.CreateTestBook(author);
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void CheckWhetherMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthor1();

        authordao.save(author);

        Book book = BookDataUtil.CreateTestBook(author);
        underTest.save(book);
        Book book1 =  BookDataUtil.CreateTestBook1(author);
        underTest.save(book1);
       Book book2 =  BookDataUtil.CreateTestBook2(author);
       underTest.save(book2);

       Iterable<Book> result = underTest.findAll();

       assertThat(result)
               .hasSize(3)
               .containsExactly(book,book1,book2);



    }

    @Test
    public void CheckWhetherBookCanBeUpdatedOrNot(){
        Author author = TestDataUtil.CreateTestAuthor1();
        authordao.save(author);
        Book book = BookDataUtil.CreateTestBook(author);
        underTest.save(book);
        book.setTitle("narnia");
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result.isPresent()).isTrue();
    }

//    @Test
//    public void ChcekWhetherBookExists() {
//        Author author = TestDataUtil.CreateTestAuthor();
//        authordao.createAuthor(author);
//        Book book = BookDataUtil.CreateTestBook();
//        book.setAuthor_id(author.getId());
//
//
//
//        underTest.createBook(book);
//        Optional<Book> result = underTest.find(book.getIsbn());
//        assertThat(result.isPresent()).isTrue();
//
//
//
//    }
//    @Test
//    public void checkWHetherBookCanBeUPdatedOrNot(){
//        Author author = TestDataUtil.CreateTestAuthor();
//        authordao.createAuthor(author);
//
//        Book book = BookDataUtil.CreateTestBook();
//
//        book.setAuthor_id(author.getId());
//
//        underTest.createBook(book);
//
//        book.setTitle("test");
//        underTest.update(book.getIsbn(), book);
//
//        Optional<Book> result = underTest.find(book.getIsbn());
//
//        assertThat(result.isPresent()).isTrue();
//
//    }


}
