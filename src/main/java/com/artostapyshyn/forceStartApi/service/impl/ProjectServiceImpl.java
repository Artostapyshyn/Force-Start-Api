package com.artostapyshyn.forceStartApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artostapyshyn.forceStartApi.entity.Project;
import com.artostapyshyn.forceStartApi.repository.ProjectRepository;
import com.artostapyshyn.forceStartApi.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}

	@Override
	public Project save(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll().stream().toList();
	}

	@Override
	public void deleteById(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public List<Project> generateRandomProjects() {
		return projectRepository.generateRandomProjects();
	}

}
