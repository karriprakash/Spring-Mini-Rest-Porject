package com.spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.project.model.Employee;

public interface EmployeeRepository 
				extends JpaRepository<Employee, Integer>{
	@Modifying
	@Query("update Employee SET empName=:name WHERE empId=:id")
	void updateEmployeeNameById(String name,Integer id);
}
