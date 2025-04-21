package com.te.beanutils.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.beanutils.dto.EmployeeDto;
import com.te.beanutils.response.AppResponse;
import com.te.beanutils.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("addEmp")
	public ResponseEntity<AppResponse> addEmp(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto empDto = employeeService.addEmployee(employeeDto);
		if (empDto != null) {
			return ResponseEntity.accepted()
					.body(new AppResponse(false, "Employee is added", 200, Arrays.asList(empDto)));
		} else {
			return ResponseEntity.accepted()
					.body(new AppResponse(true, "Employee is not added", 400, Arrays.asList(empDto)));

		}
	}
	
	@GetMapping("getAll")
	public ResponseEntity<AppResponse> getAll() {
		List<EmployeeDto> empDto = employeeService.getAllEmployee();
		if (empDto != null) {
			return ResponseEntity.accepted()
					.body(new AppResponse(false, "got all Employee", 200, Arrays.asList(empDto)));
		} else {
			return ResponseEntity.accepted()
					.body(new AppResponse(true, "Employee list is empty", 400, Arrays.asList(empDto)));

		}
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<AppResponse> getById(@PathVariable Integer id) {
		EmployeeDto empDto = employeeService.getByIdEmployee(id);
		if (empDto != null) {
			return ResponseEntity.accepted()
					.body(new AppResponse(false, "got Employee id", 200, Arrays.asList(empDto)));
		} else {
			return ResponseEntity.accepted()
					.body(new AppResponse(true, "id is not present", 400, Arrays.asList(empDto)));

		}
	}
	
	@GetMapping("getByName/{name}")
	public ResponseEntity<AppResponse> getByName(@PathVariable String name) {
		List<EmployeeDto> empDto = employeeService.getByEmployeeName(name);
		if (empDto != null) {
			return ResponseEntity.accepted()
					.body(new AppResponse(false, "got Employee name", 200, Arrays.asList(empDto)));
		} else {
			return ResponseEntity.accepted()
					.body(new AppResponse(true, "name is not present", 400, Arrays.asList(empDto)));

		}
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<AppResponse> getByName(@RequestBody EmployeeDto employeeDto,@PathVariable Integer id) {
		EmployeeDto empDto = employeeService.updateEmployee(employeeDto,id);
		if (empDto != null) {
			return ResponseEntity.accepted()
					.body(new AppResponse(false, "updated successfully", 200, Arrays.asList(empDto)));
		} else {
			return ResponseEntity.accepted()
					.body(new AppResponse(true, "something went wrong", 400, Arrays.asList(empDto)));

		}
	}
}
