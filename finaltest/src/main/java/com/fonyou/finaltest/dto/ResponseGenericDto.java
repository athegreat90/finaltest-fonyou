package com.fonyou.finaltest.dto;

public class ResponseGenericDto<T>
{
	private String status;
	private String message;
	private boolean isSuccess;
	private T body;
	
	public ResponseGenericDto()
	{
		super();
	}

	public ResponseGenericDto(String status, String message, T body, boolean isSuccess)
	{
		super();
		this.status = status;
		this.message = message;
		this.isSuccess = isSuccess;
		this.body = body;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	public T getBody()
	{
		return body;
	}

	public void setBody(T body)
	{
		this.body = body;
	}
	
	
}
