package com.artostapyshyn.forceStartApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.artostapyshyn.forceStartApi.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM projects ORDER BY random() LIMIT 10")
	List<Project> generateRandomProjects();
}
