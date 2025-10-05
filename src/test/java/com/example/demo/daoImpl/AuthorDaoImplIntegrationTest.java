package com.example.demo.daoImpl;

import com.example.demo.Dao.impl.AuthordaoImpl;
import com.example.demo.domain.Author;
import org.junit.jupiter.api.BeforeEach;
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



    private AuthordaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthordaoImpl underTest) {
        this.underTest = underTest;
    }


    @Test

    public void TestAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthor();

        underTest.createAuthor(author);
      Optional<Author> result =  underTest.findOne(author.getId());
        assertThat(result.isPresent()).isTrue();


    }

}
