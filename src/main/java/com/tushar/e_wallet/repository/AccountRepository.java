package com.tushar.e_wallet.repository;

import com.tushar.e_wallet.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID>
{
}
