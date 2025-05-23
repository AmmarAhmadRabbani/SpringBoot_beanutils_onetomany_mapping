package com.te.beanutils.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id", nullable = false, length = 40)
	private Integer projectId;
	@Column(name = "pro_name", nullable = false, length = 40)
	private String projectName;
	@Column(name = "pro_duration", nullable = false, length = 40)
	private String projectDuration;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="emp_id")
	private Employee employee;
}
