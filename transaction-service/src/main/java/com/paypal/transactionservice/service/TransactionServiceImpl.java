package com.paypal.transactionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.transactionservice.entity.Transaction;
import com.paypal.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements  TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private  ObjectMapper objectMapper;
    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
}
