package com.paypal.transactionservice.controller;

import com.paypal.transactionservice.entity.Transaction;
import com.paypal.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.font.TransformAttribute;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Transaction transaction){
        Transaction created= transactionService.createTransaction(transaction);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/all")
    public List<Transaction> getAll(){
        return transactionService.getAllTransaction();
    }

}
