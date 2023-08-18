package com.tushar.e_wallet.service.profile;

import com.tushar.e_wallet.entities.Account;
import com.tushar.e_wallet.entities.Profile;
import com.tushar.e_wallet.exceptions.LowBalanceException;
import com.tushar.e_wallet.exceptions.ResourceNotFoundException;
import com.tushar.e_wallet.payloads.ProfileDto;
import com.tushar.e_wallet.repository.AccountRepository;
import com.tushar.e_wallet.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService
{

	@Autowired
	private ProfileRepository profileRepository;


	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountRepository accountRepository;


	@Override
	public ProfileDto getProfileById(UUID id)
	{
		Profile tempProfile = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile", "Profile id", id));
		return modelMapper.map(tempProfile, ProfileDto.class);

	}

	@Override
	public List<ProfileDto> getAllProfiles()
	{
		List<Profile>    profiles    = profileRepository.findAll();
		List<ProfileDto> profileDtos = profiles.stream().map(profile -> modelMapper.map(profile, ProfileDto.class)).collect(Collectors.toList());
		return profileDtos;
	}

	@Override
	public ProfileDto addBalance(UUID id, int balance)
	{

		Profile tempProfile = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile", "Profile id", id));


		tempProfile.setBalance(tempProfile.getBalance() + balance);

		Account tempAccount = tempProfile.getAccount();
		tempAccount.setBalance(tempAccount.getBalance() + balance);
		tempProfile.setAccount(tempAccount);
		profileRepository.save(tempProfile);

		return modelMapper.map(tempProfile, ProfileDto.class);
	}

	@Override
	public ProfileDto withdrawBalance(UUID id, int balance)
	{

		Profile tempProfile = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile", "Profile id", id));

		if (balance > tempProfile.getBalance())
		{
			throw new LowBalanceException();
		}
		tempProfile.setBalance(tempProfile.getBalance() - balance);

		Account tempAccount = tempProfile.getAccount();
		tempAccount.setBalance(tempAccount.getBalance() - balance);
		tempProfile.setAccount(tempAccount);
		profileRepository.save(tempProfile);


		return modelMapper.map(tempProfile, ProfileDto.class);

	}

	@Override
	public ProfileDto getProfileByAccount(UUID id)
	{
		Account tempAccount = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "Account id", id));
		Profile tempProfile = profileRepository.findByAccount(tempAccount);

		if (tempProfile == null)
		{
			throw new ResourceNotFoundException("Profile", "Account id", id);
		}
		return modelMapper.map(tempProfile, ProfileDto.class);
	}

}
