package com.example.zti.handlers;

import com.example.zti.exceptions.DishNotFoundException;
import com.example.zti.exceptions.InvalidParametersException;
import com.example.zti.exceptions.PathNotFoundException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

class ThrowableTranslator {

    private final HttpStatus httpStatus;
    private final String message;

    private ThrowableTranslator(final Throwable throwable) {
        this.httpStatus = getStatus(throwable);
        this.message = throwable.getMessage();
    }

    private HttpStatus getStatus(final Throwable error) {
        if (error instanceof InvalidParametersException) {
            return HttpStatus.BAD_REQUEST;
        } else if (error instanceof PathNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (error instanceof DishNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (error instanceof InvalidParametersException) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    String getMessage() {
        return message;
    }

    static <T extends Throwable> Mono<ThrowableTranslator> translate(final Mono<T> throwable) {
        return throwable.flatMap(error -> Mono.just(new ThrowableTranslator(error)));
    }
}
