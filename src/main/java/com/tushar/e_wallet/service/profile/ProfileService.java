package com.tushar.e_wallet.service.profile;

import com.tushar.e_wallet.payloads.ProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProfileService
{
	public ProfileDto getProfileById(UUID id);

	public List<ProfileDto> getAllProfiles();

	public ProfileDto addBalance(UUID id, int balance);

	public ProfileDto withdrawBalance(UUID id, int balance);

	public ProfileDto getProfileByAccount(UUID id);
}
