package com.te.beanutils.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
@ToString
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id", nullable = false, length = 40)
	private Integer employeeId;
	@Column(name = "emp_name", nullable = false, length = 40)
	private String employeeName;
	@Column(name = "emp_email", nullable = false, length = 40)
	private String employeeEmail;
	@Column(name = "emp_password", nullable = false, length = 40)
	private String employeePassword;
	@Column(name = "emp_salary", nullable = false, length = 40)
	private double employeeSalary;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Project> project;
}
