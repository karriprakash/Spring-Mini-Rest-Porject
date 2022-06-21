package com.spring.project.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.spring.project.exception.EmployeeNotFoundException;
import com.spring.project.exception.ErrorResponse;
import com.spring.project.model.Employee;
import com.spring.project.model.MessageResponse;
import com.spring.project.service.IEmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/employee")
@Api(description="Employee CRUD Controller")
@Profile({"default","dev","test"})
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {

	@Autowired 
	private IEmployeeService eService;
	
	
	@GetMapping("get/{id}")
	@ApiOperation("Get One Employee by Id")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable Integer id){			
		Employee employee = eService.getOneEmployee(id);
		ResponseEntity<Employee> re = new ResponseEntity<Employee>(employee,HttpStatus.OK);
		return re;
	}
	
	@PostMapping("/create")
	@ApiOperation("Create One Employee")
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		Integer id = eService.saveEmployee(employee);
		String message = "Employee '"+id+"' is saved successfully!!";
		ResponseEntity<String> re= new ResponseEntity<String>(message,HttpStatus.CREATED);
		return re;
	}
	
	@GetMapping("/all")
	@ApiOperation("Get all employees data")
	public ResponseEntity<List<Employee>> getAll(){
		return ResponseEntity.ok(eService.getAllEmployees());
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation("Delete One Employee by Id")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		String message = null;
		ResponseEntity<String> re = null;
		try {
	 	eService.deleteOneEmployee(id);
		message = "Employee '"+id+"' is deleted successfully!!";
		re =  new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (EmployeeNotFoundException e) {
			message = id==null?"Id is not Specified":"Employee " + id + " is not found!";
			re = new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		}
		return re;
	}
	
	//update
	@PutMapping("/update")
	@ApiOperation("Update an Employee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee){
		ResponseEntity<String> re = null;
		String message = null;
		try {
		eService.updateEmployee(employee);
		message = "Employee '"+employee.getEmpId()+"' is updated!!";
		re =  new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (EmployeeNotFoundException e) {
		message = employee.getEmpId()==null?"Employee Id not specified!" : "Given Employee Id:" + employee.getEmpId()+ " not found in our data!";
		re = new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		}
		return re;
	}
	
	@PatchMapping("/update/{id}/{name}")
	@ApiOperation("Change Employee Name Patch Update")
	MessageResponse patchUpdateEmployeeName(
			@PathVariable Integer id,
			@PathVariable String name
			){
		MessageResponse response = null;
		try {
			eService.updateEmployeeNameById(name, id);
			response = 
			MessageResponse
			.builder()
			.message("Employee "+id+" is update successfully!!")
			.date(new Date().toString())
			.module("Employee")
			.status("success")
			.build();
		} catch (EmployeeNotFoundException e) {
			throw e;
		}
		return response;
	}
	
	
	/***
	 * Exception Handler Methods <br>
	 * This exception was class level <br> 
	 * <code> <b style='color:red;font-size:1.15em'>@ExceptionHandler</b> this annotation will be used for class particular exception </code>
	 */
//	@ExceptionHandler(value = EmployeeNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException enfe) {
//		return new ErrorResponse(HttpStatus.NOT_FOUND.value(),enfe.getMessage());
//	}
	
	@ExceptionHandler(value=MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBadRequest(MethodArgumentTypeMismatchException ma) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),"It's a bad request!!<br>"+ma.getMessage());
	}
	
	
}
