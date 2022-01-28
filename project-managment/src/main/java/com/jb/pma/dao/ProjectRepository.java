package com.jb.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jb.pma.dto.ChartData;
import com.jb.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	
	/*
	 * @Override public List<Project> findAll();
	 */
	
	@Query(nativeQuery=true, value="SELECT STAGE AS LABEL, COUNT(*) AS VALUE FROM PROJECT " + 
			"GROUP BY STAGE " + 
			"ORDER BY VALUE DESC")
	public List<ChartData> projectStatus();
	
}
