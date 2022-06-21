package com.spring.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.project.model.Employee;
import com.spring.project.service.IEmployeeService;

@SpringBootTest
class SpringRestMiniProjectApplicationTests {

	@Autowired
	private IEmployeeService empService;
	
	@Test
	void contextLoads() {
	}

	
	@DisplayName("create employee")
	@Test
	public void testCreateEmployee(TestInfo info) {
		Employee employee = new Employee(3, "Surya", 26310.31, "Developer", 3000.10, 5000.25);
		Integer id = empService.saveEmployee(employee);
		System.out.println(info.getTestClass().get().getName());
		assertEquals(id>0,true);
	}
}
