package com.tushar.e_wallet.controller;

import com.tushar.e_wallet.payloads.AccountDto;
import com.tushar.e_wallet.service.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@Tag(name = "ACCOUNT")
public class AccountController
{

	@Autowired
	private AccountService accountService;


	@GetMapping("/{id}")
	@CrossOrigin
	@Operation(description = "GET ACCOUNT BY ID", summary = "GET ENDPOINT TO FETCH ACCOUNT FROM ID")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable UUID id)
	{
		AccountDto accountDto = accountService.getAccountById(id);
		return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
	}

	@GetMapping("/")
	@CrossOrigin
	@Operation(description = "GET ALL ACCOUNTS", summary = "GET ENDPOINT TO FETCH ALL ACCOUNTS")
	public ResponseEntity<List<AccountDto>> getAllAccounts()
	{
		List<AccountDto> accountDtos = accountService.getAllAccounts();
		return new ResponseEntity<List<AccountDto>>(accountDtos, HttpStatus.OK);
	}
}
