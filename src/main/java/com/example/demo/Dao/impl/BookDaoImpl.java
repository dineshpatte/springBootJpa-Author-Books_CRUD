package com.example.demo.Dao.impl;

import com.example.demo.Dao.Bookdao;
import com.example.demo.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class BookDaoImpl implements Bookdao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createBook(Book book){
        jdbcTemplate.update("INSERT INTO books(isbn,title,author_id) VALUES(?,?,?)",book.getIsbn(),book.getTitle(),book.getAuthor_id());
    }


    @Override
    public Optional<Book> find(String isbn){

        List<Book> results  = jdbcTemplate.query("SELECT isbn,title,author_id from books WHERE isbn = ? LIMIT 1",
                new BookDaoImpl.BookRowMapper(),isbn
                );

        return results.stream().findFirst();


    }

    public static class BookRowMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
         return    Book.builder()
                    .isbn(rs.getString("isbn"))

                    .title(rs.getString("title"))
                    .author_id(rs.getLong("author_id"))

                    .build();
        }
    }

}
