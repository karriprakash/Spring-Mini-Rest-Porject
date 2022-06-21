package com.spring.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="emptab")
@Entity
public class Employee {

	@Id
	@Column(name="empid")
	@GeneratedValue
	private Integer empId;
	
	@Column(name="empname")
	@NotNull(message="Employee name should not be null")
	private String empName;
	
	@Column(name="empsal")
	@Min(value = 10000)
	private Double empSal;
	
	@Column(name="empdept")
	private String empDept;
	
	@Column(name="emphra")
	private Double empHra;
	
	@Column(name="empta")
	private Double empTa;
}
