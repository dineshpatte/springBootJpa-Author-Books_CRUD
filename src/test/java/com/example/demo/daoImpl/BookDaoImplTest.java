package com.example.demo.daoImpl;

import com.example.demo.Dao.impl.BookDaoImpl;
import com.example.demo.domain.Book;
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


public class BookDaoImplTest {

    @Mock
   public JdbcTemplate jdbcTemplate;

  @InjectMocks
    private BookDaoImpl BookTest;

  @Test
  public void TestTheCreatedBook(){
      Book book = BookDataUtil.CreateTestBook();

      BookTest.createBook(book);

      verify(jdbcTemplate).update(eq("INSERT INTO books(isbn,title,author_id) VALUES(?,?,?)"),
              eq("g54rtdr"),eq("atomic habits"),eq(1L)

      );
    }

    @Test
    public void TestTheFindOneBookGenrerated(){

      BookTest.find("g54rtdr");

      verify(jdbcTemplate).query(eq("SELECT isbn,title,author_id from books WHERE isbn = ? LIMIT 1"),
              ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
      eq("g54rtdr")

              );



    }








}
