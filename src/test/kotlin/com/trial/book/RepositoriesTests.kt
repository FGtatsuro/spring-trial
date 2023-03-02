package com.trial.book

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository,
) {

    @Test
    fun `When findByIdOrNull then return a book`() {
        val book1 = Book(title = "book1")
        val book2 = Book(title = "book2")
        entityManager.persist(book1)
        entityManager.persist(book2)
        entityManager.flush()

        // NOTE: findById returns Optional<Entity>, not Entity.
        val found = bookRepository.findByIdOrNull(book1.id!!)
        assertThat(found).isEqualTo(book1)
        assertThat(found).isNotEqualTo(book2)
    }

    @Test
    fun `When findByIdOrNull then return a author`() {
        val author1 = Author(name = "author1")
        val author2 = Author(name = "author2")
        entityManager.persist(author1)
        entityManager.persist(author2)
        entityManager.flush()

        val found = authorRepository.findByIdOrNull(author2.id!!)
        assertThat(found).isEqualTo(author2)
        assertThat(found).isNotEqualTo(author1)
    }

    @Test
    fun `When findByNameStartingWith then return list of authors with startsWith condition`() {
        val author1 = Author(name = "author1")
        val author2 = Author(name = "author2")
        val author3 = Author(name = "another_prefix_author3")
        entityManager.persist(author1)
        entityManager.persist(author2)
        entityManager.persist(author3)
        entityManager.flush()

        val found = authorRepository.findByNameStartingWith("author")
        assertThat(found).isEqualTo(listOf(author1, author2))
    }

    @Test
    fun `When findByIsbn then return a book with exact-matched isbn`() {
        val book1 = Book(title = "book1", isbn = "12345")
        val book2 = Book(title = "book2", isbn = "1234567")
        entityManager.persist(book1)
        entityManager.persist(book2)
        entityManager.flush()

        val found = bookRepository.findByIsbn("12345")
        assertThat(found).isEqualTo(book1)
    }

    @Test
    fun `When findByDescription(Book) then return list of books with containing condition`() {
        val book1 = Book(title = "book1", description = "hit-suffix")
        val book2 = Book(title = "book2", description = "prefix-hit-suffix")
        val book3 = Book(title = "book3", description = "prefix-hit")
        val book4 = Book(title = "book4", description = "not-match")
        entityManager.persist(book1)
        entityManager.persist(book2)
        entityManager.persist(book3)
        entityManager.persist(book4)
        entityManager.flush()

        val found = bookRepository.findByDescriptionContaining("hit")
        assertThat(found).isEqualTo(listOf(book1, book2, book3))
    }

    @Test
    fun `When findByDescription(Author) then return list of authors with containing condition`() {
        val author1 = Author(name = "author1", description = "hit-suffix")
        val author2 = Author(name = "author2", description = "prefix-hit-suffix")
        val author3 = Author(name = "author3", description = "prefix-hit")
        val author4 = Author(name = "author4", description = "not-match")
        entityManager.persist(author1)
        entityManager.persist(author2)
        entityManager.persist(author3)
        entityManager.persist(author4)
        entityManager.flush()

        val found = authorRepository.findByDescriptionContaining("hit")
        assertThat(found).isEqualTo(listOf(author1, author2, author3))
    }

    @Test
    fun `When findByPublishedDateBetween then return list of books with between condition`() {
        val book1 = Book(title = "book1", publishedDate = LocalDate.of(2001, 1, 11))
        val book2 = Book(title = "book2", publishedDate = LocalDate.of(2001, 1, 13))
        val book3 = Book(title = "book3", publishedDate = LocalDate.of(2001, 2, 11))
        val book4 = Book(title = "book4", publishedDate = LocalDate.of(2001, 2, 13))
        entityManager.persist(book1)
        entityManager.persist(book2)
        entityManager.persist(book3)
        entityManager.persist(book4)
        entityManager.flush()

        val found = bookRepository.findByPublishedDateBetween(
            start = LocalDate.of(2001, 1, 12),
            end = LocalDate.of(2001, 2, 12),
        )
        assertThat(found).isEqualTo(listOf(book2, book3))
    }
}