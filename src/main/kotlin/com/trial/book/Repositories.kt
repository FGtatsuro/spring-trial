package com.trial.book

import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface BookRepository : CrudRepository<Book, Long> {
    fun findByIsbn(isbn: String): Book

    // NOTE: Performance issue may occur on growing data.
    // Containing(=LIKE %(string)%) can't use database index.
    fun findByDescriptionContaining(description: String): List<Book>

    fun findByPublishedDateBetween(start: LocalDate, end: LocalDate): List<Book>
}

interface AuthorRepository : CrudRepository<Author, Long> {
    // NOTE: Performance issue may occur on growing data.
    // Containing(=LIKE %(string)%) can't use database index.
    fun findByNameContaining(name: String): List<Author>

    // NOTE: Performance issue may occur on growing data.
    // Containing(=LIKE %(string)%) can't use database index.
    fun findByDescriptionContaining(description: String): List<Author>
}