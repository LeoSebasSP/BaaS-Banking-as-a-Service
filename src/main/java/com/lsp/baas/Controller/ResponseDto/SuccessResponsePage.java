package com.lsp.baas.Controller.ResponseDto;

import java.util.List;
import java.util.Map;

public record SuccessResponsePage<T>(
    boolean success,
    int code,
    String message,
    Meta meta,
    Map<String, String> links,
    List<T> data
) {
}
