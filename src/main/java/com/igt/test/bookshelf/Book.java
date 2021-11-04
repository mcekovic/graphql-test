package com.igt.test.bookshelf;

import org.springframework.data.annotation.*;
import org.springframework.graphql.data.method.annotation.*;

record Book(@Id Long bookId, String title, long authorId) {
	String shortTitle(@Argument int length) {
		return title.length() > length ? title.substring(0, length) : title;
	}
}

