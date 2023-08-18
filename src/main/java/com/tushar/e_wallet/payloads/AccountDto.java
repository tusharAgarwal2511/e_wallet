package com.tushar.e_wallet.payloads;

import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountDto
{
	private UUID   id;
	private String description;
	private String status;
	private int    balance;
	//	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//	private List<TransactionDto> transaction;
}
