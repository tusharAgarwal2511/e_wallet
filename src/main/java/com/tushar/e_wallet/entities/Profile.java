package com.tushar.e_wallet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Profile
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID      id;
	private String    name;
	private int       balance;
	private LocalDate createdOn;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Account   account;
}
