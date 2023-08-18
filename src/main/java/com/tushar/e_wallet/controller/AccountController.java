package com.tushar.e_wallet.controller;

import com.tushar.e_wallet.payloads.AccountDto;
import com.tushar.e_wallet.service.account.AccountService;
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
@RequestMapping("/account")
public class AccountController
{

	@Autowired
	private AccountService accountService;


	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable UUID id)
	{
		AccountDto accountDto = accountService.getAccountById(id);
		return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<AccountDto>> getAllAccounts()
	{
		List<AccountDto> accountDtos = accountService.getAllAccounts();
		return new ResponseEntity<List<AccountDto>>(accountDtos, HttpStatus.OK);
	}
}
