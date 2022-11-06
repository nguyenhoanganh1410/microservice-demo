package com.example.departmentservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.departmentservice.model.Department;
import com.example.departmentservice.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository depRepository;
	
	public Department create(Department d) {
		return depRepository.save(d);
	}
	
	public Optional<Department> getOne(Long id) {
		return depRepository.findById(id);
	}
}
