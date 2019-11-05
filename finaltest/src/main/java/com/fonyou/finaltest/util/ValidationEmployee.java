package com.fonyou.finaltest.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fonyou.finaltest.dto.EmployeeDto;

@Component
public class ValidationEmployee implements IValidationEmployee
{

	/**
	 * Validate all the fields of the new or exists employee.
	 *
	 * @param EmployeeDto the employee
	 * @return the list of errors
	 * @throws Exception the exception
	 */
	@Override
	public List<String> validate(EmployeeDto employee) throws Exception
	{
		List<String> result = new ArrayList<>();
		if (this.validateString(employee.getFirstName()))
		{
			result.add("The first name is missing");
		}
		if (this.validateString(employee.getLastName()))
		{
			result.add("The last name is missing");
		}
		if (!this.validateString(employee.getBirthDate()))
		{
			if (!this.validateDateString(employee.getBirthDate()))
			{
				result.add("The date birth is in invalid format");
			}
		}
		else
		{
			result.add("The date birth is missing");
		}
		if (this.validateDouble(employee.getSalary()))
		{
			result.add("The salary is wrong");
		}
		return result;
	}
	
	private boolean validateString(String value)
	{
		if (value != null)
		{
			return value.trim().equals("") || value.trim().isEmpty() || value.trim().length() == 0;
		}
		return false;
	}
	
	/**
	 * Validate if the the date have the correct format.
	 *
	 * @param String dateString the date of birth of an employee
	 * @return a boolean if was fine
	 * @throws Exception the exception
	 */
	private boolean validateDateString(String dateString)
	{
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.parse(dateString);
			return true;
		} 
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Validate if the salary is positive.
	 *
	 * @param double salary
	 * @return a boolean if was fine
	 */
	private boolean validateDouble(double value)
	{
		int maxSize = (String.valueOf(value).contains(".")) ? 15 : 10;
		return String.valueOf(value).length() > maxSize || value <= -1;
	}

	/**
	 * Validate if the id is a integer (number).
	 *
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@Override
	public String validateId(String id) throws Exception
	{
		try
		{
			if (this.validateString(id))
			{
				return "Invalid id argument";
			}
			Integer.parseInt(id);
			return "";
		} 
		catch (NumberFormatException nfe)
		{
			return "Invalid id format";
		}
		catch (Exception e)
		{
			return "Unhandled transformation id";
		}
	}

}
