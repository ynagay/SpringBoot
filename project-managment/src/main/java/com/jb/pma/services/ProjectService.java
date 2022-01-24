package com.jb.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jb.pma.dao.ProjectRepository;
import com.jb.pma.dto.ChartData;
import com.jb.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository proRepo;

	public List<Project> getAll() {
		return proRepo.findAll();
	}

	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.projectStatus();
	}
}
