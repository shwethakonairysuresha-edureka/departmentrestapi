package com.tcs.departmentrestapi.service;

import java.util.List;
import java.util.Optional;

import com.tcs.departmentrestapi.model.Department;

public interface DepartmentService {
	public Department addDepartment(Department department);
	public String updateDepartment(Department department);
	public void deleteDepartment(int id);
	public Optional<Department> findById(int id);
	public Optional<java.util.List<Department>> getDepartments();
	public Optional<java.util.List<Department>> findByOrganizationId(int id);
	
	public void registerDepartment(Department department);
}