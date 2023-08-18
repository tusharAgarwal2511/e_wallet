package com.tushar.e_wallet.repository;

import com.tushar.e_wallet.entities.Account;
import com.tushar.e_wallet.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID>
{
	public Profile findByAccount(Account account);
}
