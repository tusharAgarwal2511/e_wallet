package com.tushar.e_wallet.payloads;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ApiResponse
{
	private String  message;
	private boolean success;
}
