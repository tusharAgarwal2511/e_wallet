package com.tushar.e_wallet.payloads;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProfileDto
{
	private UUID       id;
	private String     name;
	private int        balance;
	private LocalDate  createdOn;
	//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private AccountDto account;
}
