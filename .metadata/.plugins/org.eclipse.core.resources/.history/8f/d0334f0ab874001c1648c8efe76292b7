package com.jb.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.pma.dao.EmployeeRepository;
import com.jb.pma.dao.ProjectRepository;
import com.jb.pma.dto.ChartData;
import com.jb.pma.dto.EmployeeProject;
import com.jb.pma.entities.Project;
//import com.jb.pma.entities.Employee;
//import com.jb.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository proRepo;
		
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
		Map<String, Object> map = new HashMap<>();
		
		List<Project> project = proRepo.findAll();
		model.addAttribute("projectList", project);
		
		List<ChartData> projectsData = proRepo.projectStatus();
		
		//Let's convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectsData);
		
		//[["INPROGRESS", 2],["NOTSTARTED",	1],["COMPLETED", 1]]]
		model.addAttribute("projectStatusCnt", jsonString);
		
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectCnt", employeesProjectCnt);
		return "main/home";
	}
}
