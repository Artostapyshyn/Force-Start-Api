package com.artostapyshyn.forceStartApi.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.forceStartApi.entity.Project;

public interface ProjectService {
	Optional<Project> findById(Long id);
	
	Project save(Project project);

	List<Project> findAll();
	
	List<Project> generateRandomProjects();
	
	void deleteById(Long id);
}
