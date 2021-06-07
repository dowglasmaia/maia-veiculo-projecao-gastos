package com.maia.apiveiculos.exception;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;
}
