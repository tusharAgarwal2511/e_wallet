package com.tushar.e_wallet.service.user;

import com.tushar.e_wallet.entities.Account;
import com.tushar.e_wallet.entities.Profile;
import com.tushar.e_wallet.entities.User;
import com.tushar.e_wallet.exceptions.ResourceNotFoundException;
import com.tushar.e_wallet.payloads.UserDto;
import com.tushar.e_wallet.repository.ProfileRepository;
import com.tushar.e_wallet.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private ModelMapper       modelMapper;
	@Autowired
	private UserRepository    userRepository;
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public UserDto createUser(UserDto userDto)
	{
		Account account = new Account();
		account.setDescription("Savings Account");
		account.setBalance(0);
		account.setStatus("ACTIVE");

		Profile profile = new Profile();
		profile.setName(userDto.getName());
		profile.setCreatedOn(LocalDate.now());
		profile.setBalance(account.getBalance());
		profile.setAccount(account);

		User tempUser = modelMapper.map(userDto, User.class);
		tempUser.setProfile(profile);
		userRepository.save(tempUser);

		return modelMapper.map(tempUser, UserDto.class);

	}

	@Override
	public UserDto getUserById(UUID id)
	{
		User tempUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));
		return modelMapper.map(tempUser, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers()
	{
		List<User>    users    = userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto updateUserById(UserDto userDto, UUID id)
	{
		User tempUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));

		tempUser.setName(userDto.getName());
		tempUser.setUsername(userDto.getUsername());
		tempUser.setEmail(userDto.getEmail());
		tempUser.setPassword(userDto.getPassword());
		tempUser.setCountry(userDto.getCountry());
		tempUser.setAge(userDto.getAge());
		tempUser.setPhoneNumber(userDto.getPhoneNumber());

		// update name in profile when user updates the name
		Profile tempProfile = tempUser.getProfile();
		tempProfile.setName(tempUser.getName());
		tempUser.setProfile(tempProfile);

		userRepository.save(tempUser);
		return modelMapper.map(tempUser, UserDto.class);

	}

	@Override
	public void deleteUserById(UUID id)
	{
		User tempUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));

		userRepository.delete(tempUser);
	}

	@Override
	public UserDto getUserByPhoneNumber(String phoneNumber)
	{
		User tempUser = userRepository.findByPhoneNumber(phoneNumber);
		if (tempUser == null)
		{
			throw new ResourceNotFoundException("User", "User phone number", phoneNumber);
		}
		return modelMapper.map(tempUser, UserDto.class);

	}

	@Override
	public UserDto getUserByProfile(UUID id)
	{
		Profile tempProfile = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile", "Profile id", id));
		User    tempUser    = userRepository.findByProfile(tempProfile);
		if (tempUser == null)
		{
			throw new ResourceNotFoundException("User", "User phone number", id);
		}
		return modelMapper.map(tempUser, UserDto.class);

	}
}
