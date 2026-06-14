package com.lsp.baas.Controller;

import com.lsp.baas.Controller.ResponseDto.Meta;
import com.lsp.baas.Controller.ResponseDto.SuccessResponse;
import com.lsp.baas.Controller.ResponseDto.SuccessResponsePage;
import com.lsp.baas.Exception.ResourceNotFoundException;
import com.lsp.baas.Service.Dto.CustomerUpdate;
import com.lsp.baas.Service.ICustomerService;
import com.lsp.baas.Service.Dto.CustomerCreate;
import com.lsp.baas.Service.Dto.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService service;

    @Value("${page.size}")
    private String SIZE_PAGE;

    @PostMapping()
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerCreate customerCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(customerCreateDto));
    }

    @GetMapping()
    public ResponseEntity<SuccessResponsePage<CustomerResponse>> getAll(
            @RequestParam(name = "numberPage", defaultValue = "1") int numberPage
    ) {
        numberPage = numberPage < 1 ? 0 : numberPage - 1;
        Pageable pageable = PageRequest.of(numberPage, Integer.parseInt(SIZE_PAGE), Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<CustomerResponse> customerResponsesPage = service.findAllPage(pageable);
        SuccessResponsePage<CustomerResponse> successResponsePage = new SuccessResponsePage<>(
                true,
                HttpStatus.OK.value(),
                "Operation completed successfully.",
                new Meta(customerResponsesPage.getTotalPages(), customerResponsesPage.getNumber()+1, customerResponsesPage.getSize()),
                null,
                customerResponsesPage.getContent()
        );
        return ResponseEntity.ok(successResponsePage);
    }

    @GetMapping("/{cuid}")
    public ResponseEntity<SuccessResponse<CustomerResponse>> getByCuid(
            @PathVariable String cuid
    ){
        CustomerResponse customerResponse = service.findByCuid(cuid).orElseThrow(() -> new ResourceNotFoundException.CustomerNotFoundException(cuid));
        SuccessResponse<CustomerResponse> successResponse = new SuccessResponse<>(
                true,
                HttpStatus.OK.value(),
                "Operation Completed Succesfully.",
                customerResponse
        );
        return ResponseEntity.ok(successResponse);
    }

    @PatchMapping("/{cuid}")
    public ResponseEntity<SuccessResponse<CustomerResponse>> update(
            @Valid @RequestBody CustomerUpdate customerUpdate,
            @PathVariable String cuid
    ){
        CustomerResponse customerResponse = service.update(customerUpdate,cuid);
        SuccessResponse<CustomerResponse> successResponse = new SuccessResponse<>(
                true,
                HttpStatus.OK.value(),
                "Operation Completed Succesfully.",
                customerResponse
        );
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/{cuid}")
    public ResponseEntity<SuccessResponse<CustomerResponse>> disable(
            @PathVariable String cuid
    ){
        CustomerResponse customerResponse = service.disable(cuid);
        SuccessResponse<CustomerResponse> successResponse = new SuccessResponse<>(
                true,
                HttpStatus.OK.value(),
                "Operation Completed Succesfully.",
                customerResponse
        );
        return ResponseEntity.ok(successResponse);
    }
}
