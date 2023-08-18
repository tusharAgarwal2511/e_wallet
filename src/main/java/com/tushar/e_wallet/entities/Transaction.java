package com.tushar.e_wallet.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Transaction
{

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID      id;
	private UUID      sourceId;
	private UUID      sourceProfileId;
	private UUID      sourceAccountId;
	private String    sourcePhoneNumber;
	private UUID      toAccountId;
	private UUID      toUser;
	private UUID      toProfile;
	private String    status;
	private int       amount;
	private String    description;
	private LocalDate createdOn;

}
