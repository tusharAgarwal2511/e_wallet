package com.tushar.e_wallet.service.transaction;

import com.tushar.e_wallet.payloads.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService
{

	public TransactionDto sendMoney(UUID sourCeAccountId, String destinationPhoneNumber, int amount, String description);

	public List<TransactionDto> getAllTransactions();

	public TransactionDto getTransactionById(UUID id);
}
