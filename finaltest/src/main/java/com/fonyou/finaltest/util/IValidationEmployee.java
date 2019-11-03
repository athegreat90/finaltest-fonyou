package com.fonyou.finaltest.util;

import java.util.List;

import com.fonyou.finaltest.dto.EmployeeDto;

public interface IValidationEmployee
{
	public List<String> validate(EmployeeDto employee) throws Exception;
	public String validateId(String id) throws Exception;
}
