package com.lsp.baas.Controller.ResponseDto;

public record SuccessResponse<T>(
        boolean success,
        int code,
        String message,
        T data
) {
}
//{
//        "success": true,
//        "code": 200,
//        "message": "Operación realizada con éxito.",
//        "meta": {
//            "total_records": 100,
//            "current_page": 1,
//            "limit": 10
//        },
//        "data": [
//            {
//            "id": 1,
//            "nombre": "Servicio de Consultoría",
//            "precio": 150.00
//            }
//        ]
//}