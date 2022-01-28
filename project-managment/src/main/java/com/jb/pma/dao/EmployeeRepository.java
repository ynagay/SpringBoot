package com.jb.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jb.pma.dto.EmployeeProject;
import com.jb.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel = "apiemployees", path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	
	/*
	 * @Override public List<Employee> findAll();
	 */
	
	@Query(nativeQuery=true, value="SELECT FIRST_NAME AS FIRSTNAME, LAST_NAME AS LASTNAME, COUNT(PE.EMPLOYEE_ID) AS PROJECTCOUNT " + 
			"FROM EMPLOYEE E " + 
			"LEFT JOIN PROJECT_EMPLOYEE PE ON PE.EMPLOYEE_ID = E.EMPLOYEE_ID " + 
			"GROUP BY E.FIRST_NAME, E.LAST_NAME " + 
			"ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

}
