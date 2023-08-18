package com.tushar.e_wallet.service.account;

import com.tushar.e_wallet.payloads.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AccountService
{
	public AccountDto getAccountById(UUID id);

	public List<AccountDto> getAllAccounts();
}
