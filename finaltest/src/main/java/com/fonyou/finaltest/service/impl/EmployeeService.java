package com.fonyou.finaltest.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;

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
		List<EmployeeDto> result = this.modelMapper.map(repo.findAll(), listType);
		return result;
	}

	/**
	 * Find an employee by id.
	 *
	 * @param String id: the id of the employee
	 * @return the employee transformed
	 * @throws Exception the exception
	 */
	@Override
	public EmployeeDto findById(String id) throws Exception
	{
		String errorId = this.validation.validateId(id);
		if (!errorId.isEmpty() || errorId.length() > 0)
		{
			throw new IllegalArgumentException(errorId);
		}
		Integer idT = Integer.parseInt(id);
		Optional<Employee> employee = this.repo.findById(idT);
		if (employee.isPresent())
		{
			EmployeeDto result = this.modelMapper.map(employee.get(), EmployeeDto.class);
			return result;
		}
		else
		{
			throw new NoResultException("The employee with id: " + id + " doesn't exists");
		}
	}

	/**
	 * Save a new employee.
	 *
	 * @param EmployeeDto body: the new employee
	 * @return the ID of the new employee
	 * @throws Exception the exception
	 */
	@Override
	public int save(EmployeeDto body) throws Exception
	{
		List<String> errors = this.validation.validate(body);
		if (!errors.isEmpty() || errors.size() > 0)
		{
			StringBuilder finalError = new StringBuilder();
			for (String error : errors)
			{
				finalError.append(error).append("\n");
			}
			throw new IllegalArgumentException(finalError.toString().trim());
		}
		Employee entity = this.modelMapper.map(body, Employee.class);
		entity = this.repo.saveAndFlush(entity);
		return entity.getId();
	}

	/**
	 * Update an exist employee.
	 *
	 * @param String id: the ID of the employee
	 * @param EmployeeDto body: the changed fields of the employee
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean update(String id, EmployeeDto body) throws Exception
	{
		String errorId = this.validation.validateId(id);
		if (!errorId.isEmpty() || errorId.length() > 0)
		{
			throw new IllegalArgumentException(errorId);
		}
		Integer idT = Integer.parseInt(id);
		
		List<String> errors = this.validation.validate(body);
		if (!errors.isEmpty() || errors.size() > 0)
		{
			StringBuilder finalError = new StringBuilder();
			for (String error : errors)
			{
				finalError.append(error).append("\n");
			}
			
			throw new IllegalArgumentException(finalError.toString().trim());
		}
		if (body.getId() < 0)
		{
			body.setId(idT);
		}
		
		Optional<Employee> savedEmployee = this.repo.findById(idT);
		
		if (savedEmployee.isPresent())
		{
			Employee entity = this.modelMapper.map(body, Employee.class);
			entity = this.repo.saveAndFlush(entity);
			return true;
		}
		else 
		{
			throw new NoResultException("The employee with id: " + id + " doesn't exists");
		}
	}

	/**
	 * Delete an employee.
	 *
	 * @param String id: the id of the old employee
	 * @return A message if was fine
	 * @throws Exception the exception
	 */
	@Override
	public String delete(String id) throws Exception
	{
		String errorId = this.validation.validateId(id);
		if (!errorId.isEmpty() || errorId.length() > 0)
		{
			throw new IllegalArgumentException(errorId);
		}
		Integer idT = Integer.parseInt(id);
		Optional<Employee> employee = this.repo.findById(idT);
		if (employee.isPresent())
		{
			this.repo.delete(employee.get());
			return "The employee " + employee.get().getFirstName() + " " + employee.get().getLastName() + " was removed correctly";
		}
		else
		{
			throw new NoResultException("The employee with id: " + id + " doesn't exists");
		}
	}

}
