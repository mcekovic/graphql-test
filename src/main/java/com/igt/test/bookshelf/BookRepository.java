package com.igt.test.bookshelf;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.repository.*;

interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	List<Book> findAllByTitleContainingIgnoreCase(String title);
	List<Book> findAllByAuthorId(long authorId);
	Page<Book> findAllByAuthorId(long authorId, Pageable pageable);
}
