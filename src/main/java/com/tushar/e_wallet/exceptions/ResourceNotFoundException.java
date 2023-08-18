package com.tushar.e_wallet.exceptions;

public class ResourceNotFoundException extends RuntimeException
{


	String resourceName;
	String fieldName;
	Object fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue)
	{
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue.toString()));
		this.resourceName = resourceName;
		this.fieldName    = fieldName;
		this.fieldValue   = fieldValue;
	}


}
