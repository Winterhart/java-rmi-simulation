package model;

import java.util.List;

public class Manager extends Employee {

	private String managerID;
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
	
	public Manager(String recordId, String managerId, String firstName, String lastName, String mailID, 
			List<Project> projects, Location location) {
		
		super(recordId, firstName, lastName, mailID, projects.get(0).getProjectID());
		//TODO:  Verify MangerID is okay
		
		this.setManagerID(managerId);
		this.location = location;
		this.currentProjects = projects;
	}

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

}
