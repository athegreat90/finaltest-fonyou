package com.fonyou.finaltest.dto;

public class EmployeeDto
{
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String birthDate;
	
	private Double salary;

	public EmployeeDto()
	{
		super();
	}

	public EmployeeDto(String id, String firstName, String lastName, String birthDate, Double salary)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.salary = salary;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(String dateBirth)
	{
		this.birthDate = dateBirth;
	}

	public Double getSalary()
	{
		return salary;
	}

	public void setSalary(Double salary)
	{
		this.salary = salary;
	}

	@Override
	public String toString()
	{
		return "EmployeeDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateBirth=" + birthDate + ", salary=" + salary + "]";
	}
	
	
	
	
}
