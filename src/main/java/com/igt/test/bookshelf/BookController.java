package com.igt.test.bookshelf;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.*;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.StreamSupport.*;

@Controller
@SchemaMapping(typeName = "Book")
class BookController {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;

	@QueryMapping
	Iterable<Book> books() {
		return bookRepository.findAll();
	}

	@QueryMapping
	List<Book> booksByTitle(@Argument String title) {
		return bookRepository.findAllByTitleContainingIgnoreCase(title);
	}

	@SchemaMapping
	String shortTitle(Book book, @Argument int length) {
		return book.shortTitle(length);
	}

//
//	@SchemaMapping
//	Author author(Book book) {
//		return authorRepository.findById(book.authorId()).orElseThrow();
//	}

	@BatchMapping
	public Map<Book, Author> author(List<Book> books) {
		var bookIds = books.stream().map(Book::authorId).toList();
		var authors = authorRepository.findAllById(bookIds);
		return books.stream().collect(toMap(identity(), book -> findAuthor(authors, book)));
	}

	private static Author findAuthor(Iterable<Author> authors, Book book) {
		return stream(authors.spliterator(), false)
			.filter(author -> author.authorId() == book.authorId())
			.findFirst().orElseThrow();
	}

	@MutationMapping
	Book addBook(@Argument String title, @Argument String authorName) {
		var author = authorRepository.findAllByName(authorName).stream().findFirst()
			.orElseThrow(() -> new NotFoundException("Cannot find author: " + authorName));
		var book = new Book(null, title, author.authorId());
		return bookRepository.save(book);
	}

	@MutationMapping
	Book addBookAlt(@Argument BookInput bookInput) {
		return addBook(bookInput.title(), bookInput.authorName());
	}
}
