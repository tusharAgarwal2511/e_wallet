package com.tushar.e_wallet.controller;

import com.tushar.e_wallet.payloads.ApiResponse;
import com.tushar.e_wallet.payloads.UserDto;
import com.tushar.e_wallet.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/")
@Tag(name = "USER")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping("/")
	@CrossOrigin
	@Operation(description = "POST USER", summary = "POST ENDPOINT TO POST A NEW USER")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{

		UserDto tempUserDto = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.CREATED);
	}


	@GetMapping("/{id}")
	@CrossOrigin
	@Operation(description = "GET USER BY ID", summary = "GET ENDPOINT TO FETCH USER FROM ID")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID id)
	{
		UserDto tempUserDto = userService.getUserById(id);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.OK);
	}


	@GetMapping("/")
	@CrossOrigin
	@Operation(description = "GET ALL TRANSACTIONS", summary = "GET ENDPOINT TO FETCH ALL TRANSACTIONS")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> tempUserDto = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(tempUserDto, HttpStatus.OK);
	}


	@PutMapping("/{id}")
	@CrossOrigin
	@Operation(description = "UPDATE USER", summary = "PUT ENDPOINT TO UPDATE USER BY ID")
	public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto, @PathVariable UUID id)
	{
		UserDto tempUserDto = userService.updateUserById(userDto, id);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	@Operation(description = "DELETE USER", summary = "DELETE ENDPOINT TO DELETE USER BY ID")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable UUID id)
	{
		userService.deleteUserById(id);
		ApiResponse deleteApiResponse = new ApiResponse("USER DELETED SUCCESSFULLY", true);
		return new ResponseEntity<ApiResponse>(deleteApiResponse, HttpStatus.OK);
	}

	@GetMapping("/phone/{phoneNumber}")
	@CrossOrigin
	@Operation(description = "GET USER", summary = "GET ENDPOINT TO FETCH USER BY PHONE NUMBER")
	public ResponseEntity<UserDto> getUserById(@PathVariable("phoneNumber") String phoneNumber)
	{
		UserDto tempUserDto = userService.getUserByPhoneNumber(phoneNumber);
		return new ResponseEntity<UserDto>(tempUserDto, HttpStatus.OK);
	}

}
