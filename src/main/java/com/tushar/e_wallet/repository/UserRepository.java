package com.tushar.e_wallet.repository;

import com.tushar.e_wallet.entities.Profile;
import com.tushar.e_wallet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>
{
	public User findByPhoneNumber(String phoneNumber);

	public User findByProfile(Profile profile);
}
