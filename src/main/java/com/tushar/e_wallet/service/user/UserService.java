package com.tushar.e_wallet.service.user;

import com.tushar.e_wallet.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService
{
	public UserDto createUser(UserDto userDto);

	public UserDto getUserById(UUID id);

	public List<UserDto> getAllUsers();

	public UserDto updateUserById(UserDto userDto, UUID id);

	public void deleteUserById(UUID id);

	public UserDto getUserByPhoneNumber(String phoneNumber);

	public UserDto getUserByProfile(UUID id);
}
