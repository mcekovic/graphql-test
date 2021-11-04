package com.igt.test.bookshelf;

import org.springframework.data.annotation.*;

record Author(@Id Long authorId, String name) {}
