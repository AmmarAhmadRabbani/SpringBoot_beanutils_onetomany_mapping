package com.te.beanutils.service;

import java.util.List;

import com.te.beanutils.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto addEmployee(EmployeeDto employeeDto);

	List<EmployeeDto> getAllEmployee();

	EmployeeDto getByIdEmployee(Integer id);

	List<EmployeeDto> getByEmployeeName(String name);

	EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer id);

}
