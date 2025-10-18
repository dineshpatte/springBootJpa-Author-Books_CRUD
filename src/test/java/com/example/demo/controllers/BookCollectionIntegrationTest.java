package com.example.demo.controllers;


import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repositories.BookDataUtil;
import com.example.demo.repositories.TestDataUtil;
import com.example.demo.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookCollectionIntegrationTest {
    @Autowired
    private MockMvc mockMvc;



    private ObjectMapper objectMapper;

    private BookService bookService;

@Autowired
    public BookCollectionIntegrationTest(MockMvc mockMvc,ObjectMapper objectMapper,BookService bookService) {
        this.mockMvc = mockMvc;

        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }


    @Test
    public void TestWhetherCreateBookReturnsValidOrNotFound() throws Exception {

        Author author = TestDataUtil.CreateTestAuthor();

        Book book = BookDataUtil.CreateTestBook1(author);
          String isbn =   book.getIsbn();

        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/{isbn}",isbn)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );


    }

    @Test
    public void TestWhetherListOfBooksAreComingOrNot() throws Exception {

    Book testBook = BookDataUtil.CreateTestBook(null);
    bookService.createBook(testBook.getIsbn(),testBook);
    mockMvc.perform(

            MockMvcRequestBuilders.get("/books")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].isbn").value("g54rtdr")
            )
            .andExpect(
            MockMvcResultMatchers.jsonPath("$[0].title").value("atomic habits")
    );





    }

    @Test
    public void checkWhetherFindBookWorking() throws Exception {
        Book testBook = BookDataUtil.CreateTestBook(null);
        bookService.createBook(testBook.getIsbn(),testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/{isbn}",testBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }


}
