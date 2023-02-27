package com.trial.book

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

// NOTE: We should use classes, not data classes.
//   Ref: https://github.com/spring-guides/tut-spring-boot-kotlin#persistence-with-jpa
@Entity
class Book(
    @Id @GeneratedValue var id: Long? = null,
    var title: String,

    // NOTE: @ManyToMany may have difficult problems
    //   FYI: https://zenn.dev/dev_yoon/articles/956cbf4606d76b
    @ManyToMany var authors: Set<Author> = mutableSetOf(),
)

@Entity
class Author(
    @Id @GeneratedValue var id: Long? = null,
    var name: String,

    @ManyToMany(mappedBy = "authors") var books: Set<Book> = mutableSetOf(),
)