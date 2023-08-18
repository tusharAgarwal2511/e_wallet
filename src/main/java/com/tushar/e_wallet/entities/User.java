package com.tushar.e_wallet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID    id;
	private String  username;
	private String  name;
	private String  email;
	private String  password;
	private String  country;
	private int     age;
	private String  phoneNumber;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Profile profile;

}
