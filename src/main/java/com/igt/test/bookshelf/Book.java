package com.igt.test.bookshelf;

import org.springframework.data.annotation.*;

record Book(@Id Long bookId, String title, long authorId) {

	String shortTitle(int length) {
		return title.length() > length ? title.substring(0, length) : title;
	}
}

