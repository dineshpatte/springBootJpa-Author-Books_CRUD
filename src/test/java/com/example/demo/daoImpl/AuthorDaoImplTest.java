package com.example.demo.daoImpl;

import com.example.demo.Dao.impl.AuthordaoImpl;
import com.example.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthordaoImpl undertest;

    @Test
    public void TestThatCreateAuthorGeneratesTheCorrectSequel(){
        Author author = TestDataUtil.CreateTestAuthor();

        undertest.createAuthor(author);

        verify(jdbcTemplate).update(eq("INSERT INTO authors(id,name,age) VALUES(?,?,?)"),
                eq(1L),eq("dinesh"),eq(18)
        );

    }

    @Test
    public void TestThatFindOnereturnsCorrect(){
                undertest.findOne(1L);

                verify(jdbcTemplate).query(eq("SELECT id,name,age FROM authors WHERE id = ? LIMIT 1"), ArgumentMatchers.<AuthordaoImpl.AuthorMapper>any(),eq(1L));

    }




}
