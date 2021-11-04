package com.igt.test.bookshelf;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.graphql.boot.test.tester.*;
import org.springframework.graphql.test.tester.*;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebGraphQlTester
class BookshelfApplicationIT {

	@Autowired
	private WebGraphQlTester graphQlTester;

	@Test
	void contextLoads() {}

	@Test
	void booksAreSearched() {
		var query =  /* language=GraphQL */ """
			query searchBooks($title: String!) {
			  books: booksByTitle(title: $title) {
			    bookId
			    title
			    author {
			      name
			    }
			  }
			}""";
		var response = graphQlTester.query(query)
			.variable("title", "clean")
			.execute();

		response.path("data.books[*].author.name").entityList(String.class).hasSize(3);
	}
}
