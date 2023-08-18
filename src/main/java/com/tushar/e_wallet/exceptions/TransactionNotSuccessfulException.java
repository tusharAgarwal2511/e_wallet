package com.tushar.e_wallet.exceptions;

public class TransactionNotSuccessfulException extends RuntimeException
{

	public TransactionNotSuccessfulException()
	{
		super("TRANSACTION NOT SUCCESSFUL");
	}

}
