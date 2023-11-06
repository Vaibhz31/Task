package com.boot3.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot3.demo.Entity.Module;

public interface Module_Repository extends JpaRepository<Module, Long> {
}
