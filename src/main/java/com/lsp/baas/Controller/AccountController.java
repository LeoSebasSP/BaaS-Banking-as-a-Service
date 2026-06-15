package com.lsp.baas.Controller;

import com.lsp.baas.Controller.ResponseDto.Meta;
import com.lsp.baas.Controller.ResponseDto.SuccessResponse;
import com.lsp.baas.Controller.ResponseDto.SuccessResponsePage;
import com.lsp.baas.Exception.ResourceNotFoundException;
import com.lsp.baas.Service.Dto.AccountCreate;
import com.lsp.baas.Service.Dto.AccountResponse;
import com.lsp.baas.Service.IAccountService;
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
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService service;

    @Value("${page.size}")
    private String SIZE_PAGE;

    @PostMapping()
    public ResponseEntity<SuccessResponse<AccountResponse>> save(@Valid @RequestBody AccountCreate accountCreate) {
        AccountResponse accountResponse = service.save(accountCreate);
        SuccessResponse<AccountResponse> successResponse = new SuccessResponse<>(
                true,
                HttpStatus.CREATED.value(),
                "Operation completed successfully.",
                accountResponse
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @GetMapping()
    public ResponseEntity<SuccessResponsePage<AccountResponse>> getAll(
            @RequestParam(name = "numberPage", defaultValue = "1") int numberPage
    ) {
        numberPage = numberPage < 1 ? 0 : numberPage - 1;
        Pageable pageable = PageRequest.of(numberPage, Integer.parseInt(SIZE_PAGE), Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<AccountResponse> accountResponsesPage = service.findAllPage(pageable);
        SuccessResponsePage<AccountResponse> successResponsePage = new SuccessResponsePage<>(
                true,
                HttpStatus.OK.value(),
                "Operation completed successfully.",
                new Meta(accountResponsesPage.getTotalPages(), accountResponsesPage.getNumber()+1, accountResponsesPage.getSize()),
                null,
                accountResponsesPage.getContent()
        );
        return ResponseEntity.ok(successResponsePage);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<SuccessResponse<AccountResponse>> getByAccountNumber(
            @PathVariable String accountNumber
    ){
        AccountResponse accountResponse = service.findByAccountNumber(accountNumber).orElseThrow(() -> new ResourceNotFoundException.AccountNotFoundException(accountNumber));
        SuccessResponse<AccountResponse> successResponse = new SuccessResponse<>(
                true,
                HttpStatus.OK.value(),
                "Operation Completed Succesfully.",
                accountResponse
        );
        return ResponseEntity.ok(successResponse);
    }



}
