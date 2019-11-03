package com.fonyou.finaltest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.finaltest.model.Employee;

/**
 * The Interface IEmployeeRepo.
 */
public interface IEmployeeRepo extends JpaRepository<Employee, Integer>
{

}
