package com.example.departmentservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.departmentservice.model.Department;
import com.example.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentServicea;
	
	@PostMapping
	public Department createDepartment(@RequestBody Department d) {
		return departmentServicea.create(d);
	}
	
	@GetMapping("/{id}")
	public Optional<Department> getDepartmentById(@PathVariable("id") Long id) {
		return departmentServicea.getOne(id);
	}
}
