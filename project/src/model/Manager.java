package model;

import java.util.List;

public class Manager extends Employee {

	private Location location;
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Project> getCurrentProjects() {
		return currentProjects;
	}

	public void setCurrentProjects(List<Project> currentProjects) {
		this.currentProjects = currentProjects;
	}

	private List<Project> currentProjects;
	
	public Manager(String firstName, String lastName, String mailID, 
			List<Project> projects, Location location) {
		
		super(firstName, lastName, mailID, projects.get(0).getProjectID());
		this.location = location;
		this.currentProjects = projects;
	}

}
