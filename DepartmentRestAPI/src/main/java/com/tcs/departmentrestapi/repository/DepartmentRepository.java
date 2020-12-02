package com.tcs.departmentrestapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.departmentrestapi.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Optional<Department> findById(int id);
}
