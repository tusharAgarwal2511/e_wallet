package com.tushar.e_wallet.controller;

import com.tushar.e_wallet.payloads.ProfileDto;
import com.tushar.e_wallet.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile/")
public class ProfileController
{
	@Autowired
	private ProfileService profileService;

	@GetMapping("/{id}")
	public ResponseEntity<ProfileDto> getProfileById(@PathVariable("id") UUID id)
	{
		ProfileDto tempProfileDto = profileService.getProfileById(id);
		return new ResponseEntity<ProfileDto>(tempProfileDto, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<ProfileDto>> getAllProfiles()
	{
		List<ProfileDto> tempProfileDtos = profileService.getAllProfiles();
		return new ResponseEntity<List<ProfileDto>>(tempProfileDtos, HttpStatus.OK);
	}

	@GetMapping("/id/{id}/add/{balance}")
	public ResponseEntity<ProfileDto> addBalance(@PathVariable("id") UUID id, @PathVariable("balance") int balance)
	{
		ProfileDto tempProfileDto = profileService.addBalance(id, balance);
		return new ResponseEntity<ProfileDto>(tempProfileDto, HttpStatus.OK);
	}

	@GetMapping("/id/{id}/withdraw/{balance}")
	public ResponseEntity<ProfileDto> withdrawBalance(@PathVariable("id") UUID id, @PathVariable("balance") int balance)
	{
		ProfileDto tempProfileDto = profileService.withdrawBalance(id, balance);
		return new ResponseEntity<ProfileDto>(tempProfileDto, HttpStatus.OK);
	}
}
