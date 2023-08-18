package com.tushar.e_wallet.payloads;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TransactionDto
{
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
