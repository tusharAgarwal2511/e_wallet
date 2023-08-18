package com.tushar.e_wallet.payloads;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDto
{
	private UUID       id;
	private String     username;
	private String     name;
	private String     email;
	private String     password;
	private String     country;
	private int        age;
	private String     phoneNumber;
	//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ProfileDto profile;
}
