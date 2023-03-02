package com.trial.book

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

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
}