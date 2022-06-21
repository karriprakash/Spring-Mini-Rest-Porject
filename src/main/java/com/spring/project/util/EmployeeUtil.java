package com.spring.project.util;

import com.spring.project.model.Employee;

public interface EmployeeUtil {

	public static void updateHraAndTa(Employee employee) {
		var salary = employee.getEmpSal();
		var hra = salary * 20/100.0;
		var ta = salary * 12/100.0;
		employee.setEmpHra(hra);
		employee.setEmpTa(ta);
	}
}
