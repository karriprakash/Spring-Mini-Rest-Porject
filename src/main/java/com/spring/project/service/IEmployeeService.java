package com.spring.project.service;

import java.util.List;

import com.spring.project.model.Employee;

public interface IEmployeeService {

	Integer saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	Employee getOneEmployee(Integer id);
	
	List<Employee> getAllEmployees();
	
	void deleteOneEmployee(Integer id);
	
	void updateEmployeeNameById(String name,Integer id);
}
