# spring-trial

Trial of Spring Framework

## Development environment

- OSX (11.6.5)
- OpenJDK (19.0.2)
- Kotlin (1.8.10-release-430)

## API samples

### Creation

Book

```bash
$ curl -s -X POST http://localhost:8080/api/books -H "Content-Type:application/json" -d '{"title": "book1"}'
{
  "title": "book1",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/books/1"
    },
    "book": {
      "href": "http://localhost:8080/api/books/1"
    },
    "authors": {
      "href": "http://localhost:8080/api/books/1/authors"
    }
  }
}


$ curl -s -X POST http://localhost:8080/api/books -H "Content-Type:application/json" -d '{"title": "book2"}'
{
  "title": "book2",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/books/2"
    },
    "book": {
      "href": "http://localhost:8080/api/books/2"
    },
    "authors": {
      "href": "http://localhost:8080/api/books/2/authors"
    }
  }
}
```

Author

```bash
$ curl -s -X POST http://localhost:8080/api/authors -H "Content-Type:application/json" -d '{"name": "author1"}'
{
  "name" : "author1",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors/1"
    },
    "author" : {
      "href" : "http://localhost:8080/api/authors/1"
    },
    "books" : {
      "href" : "http://localhost:8080/api/authors/1/books"
    }
  }
}


$ curl -s -X POST http://localhost:8080/api/authors -H "Content-Type:application/json" -d '{"name": "author2"}'
{
  "name" : "author2",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors/2"
    },
    "author" : {
      "href" : "http://localhost:8080/api/authors/2"
    },
    "books" : {
      "href" : "http://localhost:8080/api/authors/2/books"
    }
  }
}
```

### Read

Book

```bash
$ curl -s http://localhost:8080/api/books
{
  "_embedded" : {
    "books" : [ {
      "title" : "book1",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/books/1"
        },
        "book" : {
          "href" : "http://localhost:8080/api/books/1"
        },
        "authors" : {
          "href" : "http://localhost:8080/api/books/1/authors"
        }
      }
    }, {
      "title" : "book2",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/books/2"
        },
        "book" : {
          "href" : "http://localhost:8080/api/books/2"
        },
        "authors" : {
          "href" : "http://localhost:8080/api/books/2/authors"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books"
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile/books"
    }
  }
}


$ curl -s http://localhost:8080/api/books/1
{
  "title" : "book1",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/1"
    },
    "book" : {
      "href" : "http://localhost:8080/api/books/1"
    },
    "authors" : {
      "href" : "http://localhost:8080/api/books/1/authors"
    }
  }
}
```

Author

```bash
$ curl -s http://localhost:8080/api/authors
{
  "_embedded" : {
    "authors" : [ {
      "name" : "author1",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/authors/1"
        },
        "author" : {
          "href" : "http://localhost:8080/api/authors/1"
        },
        "books" : {
          "href" : "http://localhost:8080/api/authors/1/books"
        }
      }
    }, {
      "name" : "author2",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/authors/2"
        },
        "author" : {
          "href" : "http://localhost:8080/api/authors/2"
        },
        "books" : {
          "href" : "http://localhost:8080/api/authors/2/books"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors"
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile/authors"
    }
  }
}


$ curl -s http://localhost:8080/api/authors/1
{
  "name" : "author1",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors/1"
    },
    "author" : {
      "href" : "http://localhost:8080/api/authors/1"
    },
    "books" : {
      "href" : "http://localhost:8080/api/authors/1/books"
    }
  }
}


$ curl -s http://localhost:8080/api/authors/1/books
{
  "_embedded" : {
    "books" : [ ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors/1/books"
    }
  }
}
```

### Update

Book

```bash
$ curl -s -X PATCH http://localhost:8080/api/books/1 -H "Content-Type:application/json" -d '{"title": "modified_book1"}'
{
  "title" : "modified_book1",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/1"
    },
    "book" : {
      "href" : "http://localhost:8080/api/books/1"
    },
    "authors" : {
      "href" : "http://localhost:8080/api/books/1/authors"
    }
  }
}
```

Author

```bash
$ curl -s -X PATCH http://localhost:8080/api/authors/1 -H "Content-Type:application/json" -d '{"name": "modified_author1"}'
{
  "name" : "modified_author1",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors/1"
    },
    "author" : {
      "href" : "http://localhost:8080/api/authors/1"
    },
    "books" : {
      "href" : "http://localhost:8080/api/authors/1/books"
    }
  }
}
```

Association between books and authors

```bash
$ echo "http://localhost:8080/api/authors/1\nhttp://localhost:8080/api/authors/2" | \
   curl -s -X PUT http://localhost:8080/api/books/1/authors -H "Content-Type:text/uri-list" --data-binary @-


$ curl -s http://localhost:8080/api/books/1/authors
{
  "_embedded" : {
    "authors" : [ {
      "name" : "author2",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/authors/2"
        },
        "author" : {
          "href" : "http://localhost:8080/api/authors/2"
        },
        "books" : {
          "href" : "http://localhost:8080/api/authors/2/books"
        }
      }
    }, {
      "name" : "modified_author1",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/authors/1"
        },
        "author" : {
          "href" : "http://localhost:8080/api/authors/1"
        },
        "books" : {
          "href" : "http://localhost:8080/api/authors/1/books"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/1/authors"
    }
  }
}
```

### Delete

Book

```bash
$ curl -s http://localhost:8080/api/books/3
{
  "title" : "book3",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/3"
    },
    "book" : {
      "href" : "http://localhost:8080/api/books/3"
    },
    "authors" : {
      "href" : "http://localhost:8080/api/books/3/authors"
    }
  }
}


$ curl -s -X DELETE http://localhost:8080/api/books/3


$ curl -s http://localhost:8080/api/books/3
```

Author

```bash
$ curl -s http://localhost:8080/api/authors/3
{
  "name" : "author3",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors/3"
    },
    "author" : {
      "href" : "http://localhost:8080/api/authors/3"
    },
    "books" : {
      "href" : "http://localhost:8080/api/authors/3/books"
    }
  }
}


$ curl -s -X DELETE http://localhost:8080/api/authors/3


$ curl -s http://localhost:8080/api/authors/3
```

Association between books and authors

```bash
$ curl -s http://localhost:8080/api/books/3/authors
{
  "_embedded" : {
    "authors" : [ {
      "name" : "author3",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/authors/3"
        },
        "author" : {
          "href" : "http://localhost:8080/api/authors/3"
        },
        "books" : {
          "href" : "http://localhost:8080/api/authors/3/books"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/3/authors"
    }
  }
}


$ curl -s -X DELETE http://localhost:8080/api/books/3/authors/3


$ curl -s http://localhost:8080/api/books/3/authors
{
  "_embedded" : {
    "authors" : [ ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/3/authors"
    }
  }
}
```

## Tasks

### Run a server

```bash
$ ./gradlew bootRun
```

### Run tests

```bash
$ ./gradlew test
```

## TODOs

- Persist data
   - Now, data in h2 database is clear on server shutdown.
