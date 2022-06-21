package com.spring.project.util;

import com.spring.project.model.Employee;

public interface EmployeeUtil {

	public static void updateHraAndTa(Employee employee) {
		double salary = employee.getEmpSal();
		double hra = salary * 20/100.0;
		double ta = salary * 12/100.0;
		employee.setEmpHra(hra);
		employee.setEmpTa(ta);
	}
}
