package com.fonyou.finaltest.service.interfaces;

import java.util.List;

import com.fonyou.finaltest.dto.EmployeeDto;

public interface IEmployeeService
{
	public List<EmployeeDto> findAll() throws Exception;
	
	public EmployeeDto findById(String id) throws Exception;
	
	public String save(EmployeeDto body) throws Exception;
	
	public boolean update(String id, EmployeeDto body) throws Exception;
	
	public String delete(String id) throws Exception;

	public List<String> getMessages();

	public void resetProperties();
}
