package com.tushar.e_wallet.controller;

import com.tushar.e_wallet.payloads.ApiResponse;
import com.tushar.e_wallet.payloads.UserDto;
import com.tushar.e_wallet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{

		UserDto tempUserDto = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.CREATED);
	}


	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID id)
	{
		UserDto tempUserDto = userService.getUserById(id);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.OK);
	}


	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> tempUserDto = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(tempUserDto, HttpStatus.OK);
	}


	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto, @PathVariable UUID id)
	{
		UserDto tempUserDto = userService.updateUserById(userDto, id);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable UUID id)
	{
		userService.deleteUserById(id);
		ApiResponse deleteApiResponse = new ApiResponse("USER DELETED SUCCESSFULLY", true);
		return new ResponseEntity<ApiResponse>(deleteApiResponse, HttpStatus.OK);
	}

	@GetMapping("/phone/{phoneNumber}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("phoneNumber") String phoneNumber)
	{
		UserDto tempUserDto = userService.getUserByPhoneNumber(phoneNumber);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.OK);
	}

}
