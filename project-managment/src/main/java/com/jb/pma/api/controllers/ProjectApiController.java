package com.jb.pma.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jb.pma.dao.ProjectRepository;
import com.jb.pma.entities.Employee;
import com.jb.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
@Autowired
ProjectRepository proRepo;

@GetMapping
public Iterable<Project> getProjects(){
	return proRepo.findAll();
}

@GetMapping("/{id}")
public  Project getProjectById(@PathVariable("id") Long id) {
	return proRepo.findById(id).get();
}


@PostMapping(consumes = "application/json")
@ResponseStatus(HttpStatus.CREATED)
public Project createProject(@RequestBody Project project) {
	return proRepo.save(project);
}

@PutMapping(consumes = "application/json")
@ResponseStatus(HttpStatus.OK)
public Project updateProject(@RequestBody Project project) {
	return proRepo.save(project);
}

@PatchMapping(path="/{id}", consumes = "application/json")
@ResponseStatus(HttpStatus.OK)
public Project partialUpdateProject(@PathVariable("id") Long id, @RequestBody Project project) {
	Project pro = proRepo.findById(id).get();
	
	if(project.getName() != null) {
		pro.setName(project.getName());
	}
	
	if(project.getStage() != null) {
		pro.setStage(project.getStage());
	}
	
	if(project.getDescription() != null) {
		pro.setDescription(project.getDescription());
	}
	return proRepo.save(pro);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteProject(@PathVariable("id") Long id) {
	
	try {
		proRepo.deleteById(id);
	} catch (EmptyResultDataAccessException e) {
		e.printStackTrace();
	}

}


	

}
