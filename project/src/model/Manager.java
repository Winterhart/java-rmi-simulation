package model;

import java.util.List;

public class Manager extends Employee {

	private Location location;
	private List<Project> currentProjects;
	private enum Location{ CA,US,UK }
	
	public Manager(String firstName, String lastName, String mailID, 
			List<Project> projects, Location location) {
		
		super(firstName, lastName, mailID, projects.get(0).getProjectID());
		this.location = location;
		this.currentProjects = projects;
	}

}
