package com.artostapyshyn.forceStartApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artostapyshyn.forceStartApi.entity.Project;
import com.artostapyshyn.forceStartApi.exceptions.ProjectNotFoundException;
import com.artostapyshyn.forceStartApi.service.ProjectService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/v1/projects")
@AllArgsConstructor
public class ProjectController {

	private final ProjectService projectService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Object> getAllProjects() {
		List<Object> response = new ArrayList<>();
		response.add(projectService.findAll());

		log.info("Listing all projects");
		return response;
	}
	
	@GetMapping("/random")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Object> getRandomProjects() {
		List<Object> response = new ArrayList<>();
		response.add(projectService.generateRandomProjects());

		log.info("Random projects");
		return response;
	}

	@GetMapping("/find{id}")
	@ResponseStatus(value = HttpStatus.OK)
	List<Object> getProjectById(@PathParam("id") Long id) {
		List<Object> response = new ArrayList<>();
		Optional<Project> projectById = projectService.findById(id);

		if (projectById.isEmpty()) {
			throw new ProjectNotFoundException(id.toString());
		} else {
			response.add(projectById.get());
			log.info("Getting project by id - " + id);

			return response;
		}
	}

	@PostMapping("/add")
	@ResponseStatus(value = HttpStatus.OK)
	List<Object> addAdvertisement(@Valid @RequestBody Project project) {
		List<Object> response = new ArrayList<>();
		Project newProject = projectService.save(project);

		response.add(newProject);
		log.info("New project added");

		return response;
	}
	
	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	List<Object> editAdvertisement(@Valid @RequestBody Project project, @PathParam("id") Long id) {
		List<Object> response = new ArrayList<>();

		project.setId(id);
		Project prj = projectService.findById(project.getId()).get();

		edit(project, prj);

		Project updatedProject = projectService.save(prj);
		response.add(updatedProject);
		log.info("Project updated");
		
		return response;
	}
	
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public List<Object> deleteProject(@PathParam("id") Long id) {
		List<Object> response = new ArrayList<>();
		projectService.findById(id);
		projectService.deleteById(id);

		response.add("Your project has been deleted");
		log.info("Project - " + id + " has been deleted");

		return response;
	}
	
	private void edit(Project project, Project existingProject) {
		 existingProject.setDescription(project.getDescription());
		 existingProject.setPrice(project.getPrice());
	} 
}
