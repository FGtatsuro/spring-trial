package com.trial.book

import jakarta.persistence.*
import java.time.LocalDate

// NOTE: We should use classes, not data classes.
//   Ref: https://github.com/spring-guides/tut-spring-boot-kotlin#persistence-with-jpa
@Entity
class Book(
    @Id @GeneratedValue var id: Long? = null,
    var title: String,
    var publishedDate: LocalDate? = null,
    var description: String? = null,
    var pageCount: Int? = 0,

    // TODO: Handle book identifiers strictly
    //   - Types(ex. ISBN_10, ISBN_13)
    //   - Validation per types
    @Column(unique = true) var isbn: String? = null,

    // NOTE: @ManyToMany may have difficult problems
    //   FYI: https://zenn.dev/dev_yoon/articles/956cbf4606d76b
    @ManyToMany var authors: Set<Author> = mutableSetOf(),
)

@Entity
class Author(
    @Id @GeneratedValue var id: Long? = null,
    var name: String,
    var description: String? = null,

    @ManyToMany(mappedBy = "authors") var books: Set<Book> = mutableSetOf(),
)