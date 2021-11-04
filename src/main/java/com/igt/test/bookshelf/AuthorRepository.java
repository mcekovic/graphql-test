package com.igt.test.bookshelf;

import java.util.*;

import org.springframework.data.repository.*;

interface AuthorRepository extends CrudRepository<Author, Long> {

	List<Author> findAllByName(String name);
}
