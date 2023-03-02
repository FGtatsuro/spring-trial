package com.trial.book

import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long> {
}

interface AuthorRepository : CrudRepository<Author, Long> {
    fun findByNameStartingWith(name: String): List<Author>
}