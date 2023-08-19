package com.tushar.e_wallet.controller;

import com.tushar.e_wallet.payloads.ProfileDto;
import com.tushar.e_wallet.service.profile.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile/")
@Tag(name = "PROFILE")
public class ProfileController
{
	@Autowired
	private ProfileService profileService;

	@GetMapping("/{id}")
	@CrossOrigin
	@Operation(description = "GET PROFILE BY ID", summary = "GET ENDPOINT TO FETCH PROFILE FROM ID")
	public ResponseEntity<ProfileDto> getProfileById(@PathVariable("id") UUID id)
	{
		ProfileDto tempProfileDto = profileService.getProfileById(id);
		return new ResponseEntity<ProfileDto>(tempProfileDto, HttpStatus.OK);
	}

	@GetMapping("/")
	@CrossOrigin
	@Operation(description = "GET ALL PROFILES", summary = "GET ENDPOINT TO FETCH ALL PROFILES")
	public ResponseEntity<List<ProfileDto>> getAllProfiles()
	{
		List<ProfileDto> tempProfileDtos = profileService.getAllProfiles();
		return new ResponseEntity<List<ProfileDto>>(tempProfileDtos, HttpStatus.OK);
	}

	@GetMapping("/id/{id}/add/{balance}")
	@CrossOrigin
	@Operation(description = "ADD MONEY", summary = "GET ENDPOINT TO ADD MONEY")
	public ResponseEntity<ProfileDto> addBalance(@PathVariable("id") UUID id, @PathVariable("balance") int balance)
	{
		ProfileDto tempProfileDto = profileService.addBalance(id, balance);
		return new ResponseEntity<ProfileDto>(tempProfileDto, HttpStatus.OK);
	}

	@GetMapping("/id/{id}/withdraw/{balance}")
	@CrossOrigin
	@Operation(description = "WITHDRAW MONEY", summary = "GET ENDPOINT TO WITHDRAW MONEY")
	public ResponseEntity<ProfileDto> withdrawBalance(@PathVariable("id") UUID id, @PathVariable("balance") int balance)
	{
		ProfileDto tempProfileDto = profileService.withdrawBalance(id, balance);
		return new ResponseEntity<ProfileDto>(tempProfileDto, HttpStatus.OK);
	}
}
