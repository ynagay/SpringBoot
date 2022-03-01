package com.jb.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jb.pma.dao.EmployeeRepository;
import com.jb.pma.dto.EmployeeProject;
import com.jb.pma.entities.Employee;

@Service
public class EmployeeService {
	
	//Field Injection
	@Autowired
	EmployeeRepository empRepo;

	public Iterable<Employee> getAll() {
		return empRepo.findAll();
	}

	public Employee save(Employee employee) {
		return empRepo.save(employee);
		
	}
	
	public List<EmployeeProject> employeeProjects(){
		return empRepo.employeeProjects();
	}

	public Employee findByEmployeeId(long theId) {
		return empRepo.findByEmployeeId(theId);
	}

	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
		
	}
	
	
	//Constructor Injection
	/*
	 * public EmployeeService(EmployeeRepository empRepo) { super(); this.empRepo =
	 * empRepo; }
	 */
	
	//Setter Injection
	/*
	 * @Autowired public void setEmpRepo(EmployeeRepository empRepo) { this.empRepo
	 * = empRepo; }
	 */
	
}
