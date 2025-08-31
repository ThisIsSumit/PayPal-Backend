package com.paypal.transactionservice.service;

import com.paypal.transactionservice.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransaction();
}
