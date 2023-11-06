package com.boot3.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot3.demo.Entity.Employee;

public interface Employee_Repository extends JpaRepository<Employee, Long> {
}
