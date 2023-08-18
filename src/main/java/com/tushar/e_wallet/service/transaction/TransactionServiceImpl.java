package com.tushar.e_wallet.service.transaction;

import com.tushar.e_wallet.entities.Account;
import com.tushar.e_wallet.entities.Profile;
import com.tushar.e_wallet.entities.Transaction;
import com.tushar.e_wallet.entities.User;
import com.tushar.e_wallet.exceptions.LowBalanceException;
import com.tushar.e_wallet.exceptions.ResourceNotFoundException;
import com.tushar.e_wallet.payloads.TransactionDto;
import com.tushar.e_wallet.repository.AccountRepository;
import com.tushar.e_wallet.repository.ProfileRepository;
import com.tushar.e_wallet.repository.TransactionRepository;
import com.tushar.e_wallet.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService
{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public TransactionDto sendMoney(UUID sourceAccountId, String destinationPhoneNumber, int amount, String description)
	{
		Account tempSourceAccount = accountRepository.findById(sourceAccountId).orElseThrow(() -> new ResourceNotFoundException("Account", "Account id", sourceAccountId));

		Profile tempSourceProfile = profileRepository.findByAccount(tempSourceAccount);

		if (tempSourceProfile == null)
		{
			throw new ResourceNotFoundException("Account", "Account", sourceAccountId);
		}

		User tempSourceUser = userRepository.findByProfile(tempSourceProfile);

		if (tempSourceUser == null)
		{
			throw new ResourceNotFoundException("Account", "Account id", sourceAccountId);
		}


		User tempDestinationUser = userRepository.findByPhoneNumber(destinationPhoneNumber);

		if (tempDestinationUser == null)
		{
			throw new ResourceNotFoundException("User", "User Phone Number", destinationPhoneNumber);
		}

		Profile tempDestinationProfile = tempDestinationUser.getProfile();
		Account tempDestinationAccount = tempDestinationProfile.getAccount();

//		if (!(tempDestinationAccount.getStatus() == "ACTIVE") || !(tempSourceAccount.getStatus() == "ACTIVE"))
//		{
//			throw new TransactionNotSuccessfulException();
//		}

		if (tempSourceAccount.getBalance() < amount)
		{
			throw new LowBalanceException();
		}

		tempSourceAccount.setBalance(tempSourceAccount.getBalance() - amount);
		tempSourceProfile.setBalance(tempSourceProfile.getBalance() - amount);

		tempDestinationAccount.setBalance(tempDestinationAccount.getBalance() + amount);
		tempDestinationProfile.setBalance(tempDestinationProfile.getBalance() + amount);


		profileRepository.save(tempDestinationProfile);
		profileRepository.save(tempSourceProfile);

		accountRepository.save(tempSourceAccount);
		accountRepository.save(tempDestinationAccount);

		Transaction tempTransaction = new Transaction();

		tempTransaction.setSourceId(tempSourceUser.getId());
		tempTransaction.setSourceProfileId(tempSourceProfile.getId());
		tempTransaction.setSourceAccountId(tempSourceAccount.getId());
		tempTransaction.setSourcePhoneNumber(tempSourceUser.getPhoneNumber());
		tempTransaction.setToUser(tempDestinationUser.getId());
		tempTransaction.setToProfile(tempDestinationProfile.getId());
		tempTransaction.setToAccountId(tempDestinationAccount.getId());

		tempTransaction.setStatus("SUCCESS");
		tempTransaction.setAmount(amount);
		tempTransaction.setDescription(description);
		tempTransaction.setCreatedOn(LocalDate.now());

		transactionRepository.save(tempTransaction);

		return modelMapper.map(tempTransaction, TransactionDto.class);


	}

	@Override
	public List<TransactionDto> getAllTransactions()
	{
		List<Transaction>    tempTransactions     = transactionRepository.findAll();
		List<TransactionDto> tempTransactionsDtos = tempTransactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).collect(Collectors.toList());
		return tempTransactionsDtos;
	}

	@Override
	public TransactionDto getTransactionById(UUID id)
	{
		Transaction tempTransaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", "Transaction id", id));
		return modelMapper.map(tempTransaction, TransactionDto.class);

	}
}
