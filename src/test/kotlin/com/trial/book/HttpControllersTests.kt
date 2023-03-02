package com.trial.book

import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

// NOTE: These tests are FAILED because @WebMvcTest doesn't support Spring Data REST.
//   Ref: https://github.com/spring-projects/spring-data-rest/issues/1447
@WebMvcTest
class HttpControllersTests @Autowired constructor(
    private val mockMvc: MockMvc,
) {
    @MockkBean
    lateinit var bookRepository: BookRepository

    @MockkBean
    lateinit var authorRepository: AuthorRepository

    @Test
    fun `List books`() {
        mockMvc.perform(get("/api/books").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }

    @Test
    fun `List authors`() {
        mockMvc.perform(get("/api/authors").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }
}