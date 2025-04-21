package com.te.beanutils.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.beanutils.dto.EmployeeDto;
import com.te.beanutils.dto.ProjectDto;
import com.te.beanutils.entity.Employee;
import com.te.beanutils.entity.Project;
import com.te.beanutils.exception.EmployeeNotPresentException;
import com.te.beanutils.repository.EmployeeRepository;
import com.te.beanutils.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);

		List<ProjectDto> projectDto = employeeDto.getProject();
		List<Project> projectList = new ArrayList<>();
		projectDto.forEach(e -> {
			Project project = new Project();
			BeanUtils.copyProperties(e, project);
			project.setEmployee(employee);
			projectList.add(project);
		});
		employee.setProject(projectList);
		employeeRepository.save(employee);
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employee = employeeRepository.findAll();
		List<EmployeeDto> employeeDtoList = new ArrayList<>();
		if (!employee.isEmpty()) {
			employee.forEach(employees -> {
				EmployeeDto employeeDto = new EmployeeDto();
				BeanUtils.copyProperties(employees, employeeDto);

				List<Project> project = employees.getProject();
				List<ProjectDto> projectDtosList = new ArrayList<>();
				project.forEach(projects -> {
					ProjectDto projectDto = new ProjectDto();
					BeanUtils.copyProperties(projects, projectDto);
					projectDtosList.add(projectDto);

				});
				employeeDto.setProject(projectDtosList);
				employeeDtoList.add(employeeDto);
			});
			return employeeDtoList;
		} else {
			throw new EmployeeNotPresentException("list is empty");
		}
	}

	@Override
	public EmployeeDto getByIdEmployee(Integer id) {
		Optional<Employee> findById = employeeRepository.findById(id);
		if (findById.isPresent()) {
			Employee employee = findById.get();
			EmployeeDto employeeDto = new EmployeeDto();
			BeanUtils.copyProperties(employee, employeeDto);

			List<Project> pro = employee.getProject();
			List<ProjectDto> projectlist = new ArrayList<>();
			pro.forEach(project -> {
				ProjectDto projectDto = new ProjectDto();
				BeanUtils.copyProperties(project, projectDto);
				projectlist.add(projectDto);
			});
			employeeDto.setProject(projectlist);
			return employeeDto;
		} else {
			throw new EmployeeNotPresentException("with this id employee is not present");
		}

	}

	@Override
	public List<EmployeeDto> getByEmployeeName(String name) {
		List<Employee> employeeName = employeeRepository.getByEmployeeName(name);
		if (employeeName.isEmpty()) {
			throw new EmployeeNotPresentException("employee name is not present");
		} else {
			return employeeName.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
					.collect(Collectors.toList());

		}
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		if (employee != null) {
			// Employee employee=new Employee();
			BeanUtils.copyProperties(employeeDto, employee,"employeeId");
			List<Project> project = employee.getProject();
			for (Project pro : project) {
				for (ProjectDto proDto : employeeDto.getProject()) {
					if (pro.getProjectId().equals(proDto.getProjectId())) {
						BeanUtils.copyProperties(proDto, pro,"projectId");
						break;
					}
				}
			}
			employeeRepository.save(employee);
			return modelMapper.map(employee, EmployeeDto.class);
		} else {
			throw new EmployeeNotPresentException("employee id not found");
		}

	}
}
