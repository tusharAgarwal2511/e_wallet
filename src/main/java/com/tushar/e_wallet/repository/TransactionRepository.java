package com.tushar.e_wallet.repository;

import com.tushar.e_wallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>
{
	
}
