package com.example.demo.controllers;


import com.example.demo.domain.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.TestDataUtil;
import com.example.demo.services.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc


public class AuthorControllerINtegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper mapper ;

    private AuthorRepository authorRepository;

    private AuthorService authorService;

    @Autowired
    public AuthorControllerINtegrationTest(MockMvc mockMvc , ObjectMapper mapper, AuthorRepository authorRepository, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAUthorRequestIsValid() throws Exception {
        Author author = TestDataUtil.CreateTestAuthor();
        author.setId(null);
        String authorJson = mapper.writeValueAsString(author);
       mockMvc.perform(
               MockMvcRequestBuilders.post("/authors")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(authorJson)
       ).andExpect(
               MockMvcResultMatchers.status().isCreated()
       );
    }

    @Test
    public void testCreateAuthorSavedOrNot() throws Exception {
        Author author = TestDataUtil.CreateTestAuthor();
        author.setId(null);
        String authorjson = mapper.writeValueAsString(author);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorjson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("dinesh")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("18")
        );
    }

    @Test
    public void testFindOneWorksOrNot() throws Exception {

        Author author = TestDataUtil.CreateTestAuthor();
        authorRepository.save(author);

        Long id = author.getId();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("dinesh")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("18")
        );

    }

    @Test
    public void TestWhetherUpdateAuthorWorkingOrNot() throws Exception {
        Author author = TestDataUtil.CreateTestAuthor();
        Author savesAuthor = authorService.createAUthor(author);

        Author authorDto = TestDataUtil.CreateTestAuthor1();
        authorDto.setId(author.getId());

        String updatedAUthorDto = mapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+savesAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedAUthorDto)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savesAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
        );
    }

}
