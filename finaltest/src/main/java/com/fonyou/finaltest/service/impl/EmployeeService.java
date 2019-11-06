package com.fonyou.finaltest.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonyou.finaltest.dto.EmployeeDto;
import com.fonyou.finaltest.model.Employee;
import com.fonyou.finaltest.repo.IEmployeeRepo;
import com.fonyou.finaltest.service.interfaces.IEmployeeService;
import com.fonyou.finaltest.util.IValidationEmployee;

/**
 * The Class EmployeeService.
 */
@Service
public class EmployeeService implements IEmployeeService
{
	
	/** The employee repository. */
	@Autowired
	private IEmployeeRepo repo;
	
	/** The validation. */
	@Autowired
	private IValidationEmployee validation;
	
	/** To transform class in another class. */
	private ModelMapper modelMapper;
	
	/**
	 * Initialize some attributes.
	 */
	@PostConstruct
	private void init()
	{
		this.modelMapper = new ModelMapper();
	}
	

	/**
	 * Find all the employees.
	 *
	 * @return the list transformed of employees
	 * @throws Exception the exception
	 */
	@Override
	public List<EmployeeDto> findAll() throws Exception
	{
		Type listType = new TypeToken<List<EmployeeDto>>() {}.getType();
		return this.modelMapper.map(repo.findAll(), listType);
	}

	/**
	 * Find an employee by id.
	 *
	 * @param id: the id of the employee
	 * @return the employee transformed
	 * @throws Exception the exception
	 */
	@Override
	public EmployeeDto findById(String id) throws Exception
	{
		Optional<Employee> employee = this.repo.findById(id);
		if (employee.isPresent())
		{
			return this.modelMapper.map(employee.get(), EmployeeDto.class);
		}
		else
		{
			throw new Exception("The employee with id: " + id + " doesn't exists");
		}
	}

	/**
	 * Save a new employee.
	 *
	 * @param body: the new employee
	 * @return the ID of the new employee
	 * @throws Exception the exception
	 */
	@Override
	public String save(EmployeeDto body) throws Exception
	{
		validateData(body);
		Employee entity = this.modelMapper.map(body, Employee.class);
		entity = this.repo.save(entity);
		return entity.getId().toString();
	}

	private void validateData(EmployeeDto body) throws Exception
	{
		List<String> errors = this.validation.validate(body);
		if (!errors.isEmpty())
		{
			StringBuilder finalError = new StringBuilder();
			for (String error : errors)
			{
				finalError.append(error).append("\n");
			}
			throw new IllegalArgumentException(finalError.toString().trim());
		}
	}

	/**
	 * Update an exist employee.
	 *
	 * @param id: the ID of the employee
	 * @param body: the changed fields of the employee
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean update(String id, EmployeeDto body) throws Exception
	{
		String errorId = this.validation.validateId(id);
		if (!errorId.isEmpty() || errorId.length() > 0) {
			throw new IllegalArgumentException(errorId);
		}
		validateData(body);
		if (body.getId().isEmpty())
		{
			body.setId(id);
		}
		
		Optional<Employee> savedEmployee = this.repo.findById(id);
		
		if (savedEmployee.isPresent())
		{
			Employee entity = this.modelMapper.map(body, Employee.class);
			this.repo.save(entity);
			return true;
		}
		else 
		{
			throw new Exception("The employee with id: " + id + " doesn't exists");
		}
	}

	/**
	 * Delete an employee.
	 *
	 * @param id: the id of the old employee
	 * @return A message if was fine
	 * @throws Exception the exception
	 */
	@Override
	public String delete(String id) throws Exception
	{
		Optional<Employee> employee = this.repo.findById(id);
		if (employee.isPresent())
		{
			this.repo.delete(employee.get());
			return "The employee " + employee.get().getFirstName() + " " + employee.get().getLastName() + " was removed correctly";
		}
		else
		{
			throw new Exception("The employee with id: " + id + " doesn't exists");
		}
	}

}
