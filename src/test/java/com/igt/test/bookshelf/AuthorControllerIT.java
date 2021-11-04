package com.igt.test.bookshelf;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.graphql.boot.test.*;
import org.springframework.graphql.test.tester.*;

import static org.mockito.Mockito.*;

@GraphQlTest({AuthorController.class, BookController.class})
public class AuthorControllerIT {

	@Autowired
	private GraphQlTester graphQlTester;

	@MockBean
	private AuthorRepository authorRepository;

	@MockBean
	private BookRepository bookRepository;

	@Test
	void martinFowlerHasWrittenRefactoring() {
		when(authorRepository.findAllByName("Martin Fowler")).thenReturn(List.of(new Author(1L, "Martin Fowler")));
		when(bookRepository.findAllByAuthorId(1L)).thenReturn(List.of(new Book(1L, "Refactoring", 1L)));

		var query = """
			{
			  authorsByName(name: "Martin Fowler") {
			    authorId
			    name
			    books {
			      title
			    }
			  }
			}""";
		var response = graphQlTester.query(query).execute();

		response.path("data.authorsByName[0].books[0].title").entity(String.class).isEqualTo("Refactoring");
	}
}
