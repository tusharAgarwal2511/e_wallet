package com.tushar.e_wallet.exceptions;

public class LowBalanceException extends RuntimeException
{

	public LowBalanceException()
	{
		super("ACCOUNT BALANCE IS LOW");
	}

}
