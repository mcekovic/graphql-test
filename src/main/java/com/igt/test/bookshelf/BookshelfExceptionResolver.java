package com.igt.test.bookshelf;

import graphql.*;
import graphql.schema.*;
import org.springframework.graphql.execution.*;
import org.springframework.stereotype.*;

import static org.springframework.graphql.execution.ErrorType.*;

@Component
class BookshelfExceptionResolver extends DataFetcherExceptionResolverAdapter {

	@Override protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		return switch (ex) {
			case NotFoundException nfEx -> GraphqlErrorBuilder.newError(env)
				.errorType(NOT_FOUND)
				.message(nfEx.getMessage())
				.build();
			default -> super.resolveToSingleError(ex, env);
		};
	}
}
