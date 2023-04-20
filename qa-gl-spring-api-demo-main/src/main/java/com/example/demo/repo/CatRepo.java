package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Cat;

@Repository
// All standard CRUD functions are already available from JpaRepository
public interface CatRepo extends JpaRepository<Cat, Long> {

	// Custom method: find by name
	// JpaRepository generates SQL query for following
	List<Cat> findByName(String name);
}
