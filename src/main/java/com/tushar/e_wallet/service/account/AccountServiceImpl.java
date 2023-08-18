package com.tushar.e_wallet.service.account;


import com.tushar.e_wallet.entities.Account;
import com.tushar.e_wallet.exceptions.ResourceNotFoundException;
import com.tushar.e_wallet.payloads.AccountDto;
import com.tushar.e_wallet.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService
{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountRepository accountRepository;


	@Override
	public AccountDto getAccountById(UUID id)
	{
		Account tempAccount = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "Account id", id));
		return modelMapper.map(tempAccount, AccountDto.class);
	}

	@Override
	public List<AccountDto> getAllAccounts()
	{
		List<Account>    tempAccounts    = accountRepository.findAll();
		List<AccountDto> tempAccountDtos = tempAccounts.stream().map(account -> modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
		return tempAccountDtos;
	}
}
