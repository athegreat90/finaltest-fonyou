package com.fonyou.finaltest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Cacheable(false)
@Table(name = "employee")
public class Employee  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name", length = 450, nullable = false)
//	@NotBlank(message = "First name is mandatory")
	private String firstName;
	
	@Column(name = "last_name", length = 450, nullable = false)
//	@NotBlank(message = "Last name is mandatory")
	private String lastName;
	
	@Column(name = "date_birth", length = 45, nullable = false)
//	@NotBlank(message = "Date birth is mandatory")
	private String birthDate;
	
	@Column(name = "salary", nullable = false)
	private BigDecimal salary;

	public Employee()
	{
		super();
	}

	public Employee(int id, String firstName, String lastName, String birthDate, BigDecimal salary)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.salary = salary;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	public String getDateBirth()
	{
		return birthDate;
	}

	public void setDateBirth(String dateBirth)
	{
		this.birthDate = dateBirth;
	}

	public BigDecimal getSalary()
	{
		return salary;
	}

	public void setSalary(BigDecimal salary)
	{
		this.salary = salary;
	}

	@Override
	public String toString()
	{
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateBirth=" + birthDate + ", salary=" + salary + "]";
	}
	
	
	
	
}
