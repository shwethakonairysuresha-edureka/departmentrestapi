package com.tcs.departmentrestapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.departmentrestapi.model.Department;
import com.tcs.departmentrestapi.service.DepartmentService;
import com.tcs.departmentrestapi.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;

	@GetMapping
	public List<Department> getDepartments()
	{
		return departmentService.getDepartments().get();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable("id") int id) throws ResourceNotFoundException
	{
		Department department = departmentService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
		return ResponseEntity.ok().body(department);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEmployeeById(@PathVariable int id) throws ResourceNotFoundException
	{
		Department department = departmentService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
		departmentService.deleteDepartment(id);
		HashMap<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		return hashMap;
	}
	
	@PostMapping
	public ResponseEntity<?> createEmployee(@RequestBody Department department,UriComponentsBuilder uriComponentsBuilder,HttpServletRequest request)
	{
		Department department2 = departmentService.addDepartment(department);
		UriComponents uriComponents = uriComponentsBuilder
				.path(request.getRequestURI()+"/{id}")
				.buildAndExpand(department2.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(department2);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateEmployee(@PathVariable("id") Integer id,
			@Valid @RequestBody Department department ) throws ResourceNotFoundException {
		Department department2 = departmentService.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product not found"));
		department.setId(id);
		Department department3 =departmentService.addDepartment(department);		
		return ResponseEntity.ok(department3);
	}
}