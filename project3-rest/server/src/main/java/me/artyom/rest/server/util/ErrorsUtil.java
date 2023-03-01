package me.artyom.rest.server.util;

import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

public final class ErrorsUtil {

    private ErrorsUtil() {
    }

    public static String fieldErrorsFullMessage(Errors errors) {
        List<String> fieldErrorsMessages = errors.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return String.join("; ", fieldErrorsMessages);
    }
}
