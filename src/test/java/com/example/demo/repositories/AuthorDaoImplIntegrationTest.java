package com.example.demo.repositories;


import com.example.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplIntegrationTest {



    private final AuthorRepository underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }


    @Test

    public void TestAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthor();

        underTest.save(author);
      Optional<Author> result =  underTest.findById(author.getId());
        assertThat(result.isPresent()).isTrue();


    }

    @Test
    public void TestMultipleAuthorsCanBeCreatedAndRecalled(){
        Author author1 = TestDataUtil.CreateTestAuthor();
        Author author2 = TestDataUtil.CreateTestAuthor1();
        Author author3 = TestDataUtil.CreateTestAuthor2();

        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<Author> authors = underTest.findAll();

        assertThat(authors.iterator().hasNext()).isTrue();
    }

    @Test

    public void TestWhetherAuthorCanBeUpdatedOrNot(){

        Author author = TestDataUtil.CreateTestAuthor();
        underTest.save(author);
        author.setName("ivan");
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void TestThatGetAuthorsWithAgeLessThan18(){

        Author author1 = TestDataUtil.CreateTestAuthor();
        Author author2 = TestDataUtil.CreateTestAuthor1();
        Author author3 = TestDataUtil.CreateTestAuthor2();

        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<Author> results = underTest.ageLessThan(20);
        assertThat(results).containsExactly(author1);
    }

    @Test
    public void TestThatAuthorsWithAgeGreateerThan(){
        Author author1 = TestDataUtil.CreateTestAuthor();
        Author author2 = TestDataUtil.CreateTestAuthor1();
        Author author3 = TestDataUtil.CreateTestAuthor2();

        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<Author> result = underTest.FindAuthorWithAgeGreaterThan(20);
        assertThat(result).containsExactly(author2,author3);
    }
    @Test
    public void TestWhetherAuthorCanBeDeleted(){
        Author author = TestDataUtil.CreateTestAuthor();
        underTest.save(author);

        underTest.delete(author);
        assertThat(underTest.findById(author.getId()).isPresent()).isFalse();
    }


//    @Test
//    public void TestThatAuthorCanBeUpdated(){
//        Author authorA = TestDataUtil.CreateTestAuthor();
//        underTest.createAuthor(authorA);
//        authorA.setName("Dinesh1");
//        underTest.update(authorA);
//        underTest.findOne(authorA.getId());
//        Optional<Author> result =  underTest.findOne(authorA.getId());
//        assertThat(result.isPresent()).isTrue();
//
//
//
//    }

}
