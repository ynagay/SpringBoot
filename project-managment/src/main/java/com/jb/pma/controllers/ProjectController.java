package com.jb.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.jb.pma.dao.EmployeeRepository;
import com.jb.pma.dao.ProjectRepository;
import com.jb.pma.entities.Employee;
import com.jb.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List <Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		return "projects/projects-home";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List <Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProjectForm(Project project, 
			//@RequestParam List<Long> employees, 
			Model model) {
		
		//this should handle saving to the database
		proRepo.save(project);
		
		/*
		 * //to save employees associated wiyh project Iterable <Employee>
		 * chosenEmployee = empRepo.findAllById(employees);
		 * 
		 * for(Employee emp : chosenEmployee) { emp.setProject(project);
		 * empRepo.save(emp); };
		 */
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/projects";
		
	}

}