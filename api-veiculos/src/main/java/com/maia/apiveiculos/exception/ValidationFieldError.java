package com.maia.apiveiculos.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationFieldError extends ExceptionResponse {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationFieldError(
            Date timestamp,
            String message,
            String details,
            String path) {
        super(timestamp, message, details, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String msg) {
        erros.add(new FieldMessage(fieldName, msg));
    }
}




