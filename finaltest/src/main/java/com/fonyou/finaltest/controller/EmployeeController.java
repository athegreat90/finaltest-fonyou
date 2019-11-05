package com.fonyou.finaltest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fonyou.finaltest.dto.EmployeeDto;
import com.fonyou.finaltest.dto.ResponseGenericDto;
import com.fonyou.finaltest.service.interfaces.IEmployeeService;

/**
 * The Class EmployeeController.
 */
@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EmployeeController
{
	
	/** The service. */
	
	private IEmployeeService service;
	
	@Autowired
	public EmployeeController(IEmployeeService service)
	{
		this.service = service;
	}
	
	/**
	 * Find all the employees.
	 *
	 * @return All the employees
	 * @throws Exception the exception
	 */
	@GetMapping("/")
	public ResponseGenericDto<List<EmployeeDto>> findEmployee() throws Exception
	{
		return new ResponseGenericDto<>("0", "OK", this.service.findAll(), true);
	}
	
	/**
	 * Find employee by id.
	 *
	 * @param id: The employee
	 * @return An Employee
	 * @throws Exception the exception 
	 */
	@GetMapping("/{id}")
	public ResponseGenericDto<EmployeeDto> findEmployee(@PathVariable("id") String id) throws Exception
	{
		return new ResponseGenericDto<>("0", "OK", this.service.findById(id), true);
	}
	
	/**
	 * Save employee.
	 *
	 * @param body
	 * @return The ID of the new employee
	 * @throws Exception the exception
	 */
	@PostMapping("/")
	public ResponseGenericDto<String> saveEmployee(@RequestBody EmployeeDto body) throws Exception
	{
		return new ResponseGenericDto<>("0", "OK", "The new employee was created correcty: " + this.service.save(body), true);
	}
	
	/**
	 * Update employee.
	 *
	 * @param id: The ID of the employee
	 * @param body
	 * @return A message if the update was fine
	 * @throws Exception the exception
	 */
	@PutMapping("/{id}")
	public ResponseGenericDto<String> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto body) throws Exception
	{
		String result = this.service.update(id, body) ? "The Employee was updated OK" : "Something is wrong";
		return new ResponseGenericDto<>("0", "OK", result, true);
	}
	
	/**
	 * Delete employee.
	 *
	 * @param id: The ID of the employee
	 * @return A message if the employee was removed fine
	 * @throws Exception the exception
	 */
	@DeleteMapping("/{id}")
	public ResponseGenericDto<String> deleteEmployee(@PathVariable("id") String id) throws Exception
	{
		return new ResponseGenericDto<>("0", "OK", this.service.delete(id), true);
	}
}
