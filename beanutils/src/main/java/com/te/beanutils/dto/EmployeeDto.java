package com.te.beanutils.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {
	private Integer employeeId;
	private String employeeName;	
	private String employeeEmail;
	private String employeePassword;
	private double employeeSalary;
	private List<ProjectDto> project;
}

