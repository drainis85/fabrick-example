package com.fabrick.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabrick.example.beans.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
