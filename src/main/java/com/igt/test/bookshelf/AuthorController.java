package com.igt.test.bookshelf;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.*;


@Controller
@SchemaMapping(typeName = "Author")
class AuthorController {

	@Autowired AuthorRepository authorRepository;
	@Autowired BookRepository bookRepository;

	@QueryMapping
	List<Author> authorsByName(@Argument String name) {
		return authorRepository.findAllByName(name);
	}

	@SchemaMapping
	List<Book> books(Author author, @Argument Integer first) {
		return first == null
			? bookRepository.findAllByAuthorId(author.authorId())
			: bookRepository.findAllByAuthorId(author.authorId(), Pageable.ofSize(first).first()).toList();
	}

	@MutationMapping
	Author addAuthor(@Argument String name) {
		var author = new Author(null, name);
		return authorRepository.save(author);
	}
}

