package com.tushar.e_wallet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Account
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID   id;
	private String description;
	private String status;
	private int    balance;
//	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//	private List<Transaction> transactions;
}
