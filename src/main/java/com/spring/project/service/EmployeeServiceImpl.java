package com.spring.project.service;

import static com.spring.project.util.EmployeeUtil.updateHraAndTa;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.exception.EmployeeNotFoundException;
import com.spring.project.model.Employee;
import com.spring.project.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl 
				implements IEmployeeService{

	@Autowired
	private EmployeeRepository erepo;
	
	
	public Integer saveEmployee(Employee employee) {
		updateHraAndTa(employee);
		employee = erepo.save(employee);
		return employee.getEmpId();
	}
	
	/***
	 * If <code style='color:red'>employee id </code>is not equal to null or employee <code style='color:red'>exists by id </code>is not null then only update data
	 */
	
	public void updateEmployee(Employee employee) {
		if(employee.getEmpId()!=null &&
				erepo.existsById(employee.getEmpId())){
		
		updateHraAndTa(employee);
		erepo.save(employee);
		}
		else {
			throw new EmployeeNotFoundException("Employee '"+employee.getEmpId()+"' is not found!" );
		}
	}

	
	public Employee getOneEmployee(Integer id) {
		Optional<Employee> opt = erepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
		throw new EmployeeNotFoundException("Employee '"+id+"' is not found!" );
//		return erepo.findById(id).orElseThrow(()->
//			 new EmployeeNotFoundException("Employee'"+id+"' is not found" )
//		);
	}

	
	public List<Employee> getAllEmployees() {
		List<Employee> list = erepo.findAll();
		return list;
	}

	/***
	 * Check data is present in db then move forward to delete or throw exception
	 * @throws EmployeeNotFoundException 
	 */
	public void deleteOneEmployee(Integer id){
		erepo.delete(getOneEmployee(id));
		
	}

	@Transactional
	public void updateEmployeeNameById(String name, Integer id) {
		if(erepo.existsById(id))
			erepo.updateEmployeeNameById(name, id);
		else throw new EmployeeNotFoundException(
				"Employee not found with ID: "+id
				);
	}
}
