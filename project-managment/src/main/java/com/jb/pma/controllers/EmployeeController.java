package com.jb.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jb.pma.dao.EmployeeRepository;
import com.jb.pma.entities.Employee;
import com.jb.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayProjects(Model model) {
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("employeesList", employees);
		return "employees/employees-home";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee( Model model, @Valid Employee employee, Errors errors) {
		
		if(errors.hasErrors())
			return "employees/new-employee";
		//save to the database using an employee crud repository
		empService.save(employee);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/new";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId,  Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		model.addAttribute("employee", theEmp);
		return "employees/new-employee";

	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId,  Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp);
		return "redirect:/employees";

	}

}
