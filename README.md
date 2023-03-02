# spring-trial

Trial of Spring Framework

## Development environment

- OSX (11.6.5)
- OpenJDK (19.0.2)
- Kotlin (1.8.10-release-430)

## API

### 書籍登録

```bash
$ curl -s -X POST http://localhost:8080/api/books -H "Content-Type:application/json" \
   -d '{"title": "サンプルBook", "publishedDate": "2022-02-22", "description": "サンプルBookです", "pageCount": 100, "isbn": "1111111111111"}'
{
  "title" : "サンプルBook",
  "publishedDate" : "2022-02-22",
  "description" : "サンプルBookです",
  "pageCount" : 100,
  "isbn" : "1111111111111",
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

### 著者登録

```bash
$ curl -s -X POST http://localhost:8080/api/authors -H "Content-Type:application/json" \
   -d '{"name": "著者1", "description": "専門は〜です"}'
{
  "name" : "著者1",
  "description" : "専門は〜です",
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

### 書籍-著者の関連付け

1つの書籍に複数の著者を関連付ける事ができます。

```bash
$ echo "http://localhost:8080/api/authors/1\nhttp://localhost:8080/api/authors/2" | \
   curl -s -X PUT http://localhost:8080/api/books/1/authors -H "Content-Type:text/uri-list" --data-binary @-

$ curl -s http://localhost:8080/api/books/1/authors
{
  "_embedded" : {
    "authors" : [ {
      "name" : "著者1",
      "description" : "専門は〜です",
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
      "name" : "とても面白い作者",
      "description" : "とても面白い本を書きます",
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
      "href" : "http://localhost:8080/api/books/1/authors"
    }
  }
}%
```

### 著者に紐付く書籍の取得

著者名で検索後、該当リソースにアクセスする事により取得できます。

```bash
# name=著者
$ curl -s "http://localhost:8080/api/authors/search/findByNameContaining?name=%E8%91%97%E8%80%85"
{
  "_embedded" : {
    "authors" : [ {
      "name" : "著者1",
      "description" : "専門は〜です",
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
      "href" : "http://localhost:8080/api/authors/search/findByNameContaining?name=%E8%91%97%E8%80%85"
    }
  }
}

$ curl -s http://localhost:8080/api/authors/1/books
{
  "_embedded" : {
    "books" : [ {
      "title" : "サンプルBook",
      "publishedDate" : "2022-02-22",
      "description" : "サンプルBookです",
      "pageCount" : 100,
      "isbn" : "1111111111111",
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
      "title" : "とても面白い本",
      "publishedDate" : "2022-03-22",
      "description" : "とても面白い本です",
      "pageCount" : 200,
      "isbn" : "2222222222222",
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
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/authors/1/books"
    }
  }
}
```

### 書籍検索

以下の検索APIをサポートします。

```bash
$ curl -s http://localhost:8080/api/books/search
{
  "_links" : {
    "findByIsbn" : {
      "href" : "http://localhost:8080/api/books/search/findByIsbn{?isbn}",
      "templated" : true
    },
    "findByPublishedDateBetween" : {
      "href" : "http://localhost:8080/api/books/search/findByPublishedDateBetween{?start,end}",
      "templated" : true
    },
    "findByDescriptionContaining" : {
      "href" : "http://localhost:8080/api/books/search/findByDescriptionContaining{?description}",
      "templated" : true
    },
    "self" : {
      "href" : "http://localhost:8080/api/books/search"
    }
  }
}
```

### 著者検索

以下の検索APIをサポートします。

```bash
$ curl -s http://localhost:8080/api/authors/search
{
  "_links" : {
    "findByNameContaining" : {
      "href" : "http://localhost:8080/api/authors/search/findByNameContaining{?name}",
      "templated" : true
    },
    "findByDescriptionContaining" : {
      "href" : "http://localhost:8080/api/authors/search/findByDescriptionContaining{?description}",
      "templated" : true
    },
    "self" : {
      "href" : "http://localhost:8080/api/authors/search"
    }
  }
}
```

### 書籍更新

```bash
$ curl -s -X PATCH http://localhost:8080/api/books/1 -H "Content-Type:application/json" -d '{"description": "サンプルBookの改訂版です"}'
{
  "title" : "サンプルBook",
  "publishedDate" : "2022-02-22",
  "description" : "サンプルBookの改訂版です",
  "pageCount" : 100,
  "isbn" : "1111111111111",
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

### 著者更新

```bash
$ curl -s -X PATCH http://localhost:8080/api/authors/1 -H "Content-Type:application/json" -d '{"description": "専門はKotlinです"}'
{
  "name" : "著者1",
  "description" : "専門はKotlinです",
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

## タスク

### サーバ起動

```bash
$ ./gradlew bootRun
```

### テスト実行

```bash
$ ./gradlew test
```

## TODOs

- MySQL/PostgreSQLを使う
   - 組み込みのh2 databaseを使ったため、サーバの停止でデータが消える
- Controllerレベルでのテスト作成
   - Spring Data RESTを使った状態で上手くテストを作成できなかった
- 著者に紐付いた書籍取得を1つのAPIで完結させる
   - 現状では著者取得、紐付いた書籍取得で2回のAPI実行が必要
