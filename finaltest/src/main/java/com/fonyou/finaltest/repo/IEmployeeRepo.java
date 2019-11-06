package com.fonyou.finaltest.repo;

// import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.finaltest.model.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The Interface IEmployeeRepo.
 */
public interface IEmployeeRepo extends MongoRepository<Employee, String>
{

}
