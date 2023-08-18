package com.tushar.e_wallet.controller;


import com.tushar.e_wallet.payloads.TransactionDto;
import com.tushar.e_wallet.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction/")
public class TransactionController
{

	@Autowired
	private TransactionService transactionService;


	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") UUID id)
	{
		TransactionDto tempTransactionDto = transactionService.getTransactionById(id);
		return new ResponseEntity<TransactionDto>(tempTransactionDto, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<TransactionDto>> getAllTransactions()
	{
		List<TransactionDto> tempTransactionDtos = transactionService.getAllTransactions();
		return new ResponseEntity<List<TransactionDto>>(tempTransactionDtos, HttpStatus.OK);
	}

	@GetMapping("/sendMoney/sourceAccountId/{id}/destinationPhoneNumber/{phoneNumber}/amount/{amt}/description/{desc}/")
	public ResponseEntity<TransactionDto> sendMoney(@PathVariable("id") UUID id, @PathVariable("phoneNumber") String phoneNumber,
							@PathVariable("amt") int amt, @PathVariable("desc") String desc)
	{

		TransactionDto transactionDto = transactionService.sendMoney(id, phoneNumber, amt, desc);
		return new ResponseEntity<TransactionDto>(transactionDto, HttpStatus.OK);

	}
}
