package com.lsp.baas.Controller;

import com.lsp.baas.Service.ICustomerService;
import com.lsp.baas.Service.Record.CustomerCreate;
import com.lsp.baas.Service.Record.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService service;

    @PostMapping()
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerCreate customerCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(customerCreateDto));
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

}
