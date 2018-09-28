package model;

public class Employee extends Record {
	private String firstName;
	private String lastName;
	private String mailID;
	private String projectID;
	private String employeeID;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailID() {
		return mailID;
	}

	public void setMailID(String mailID) {
		this.mailID = mailID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	private void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public Employee(String employeeId, String firstName, String lastName, String mailID, String projectID) {
		super(employeeId);
		
		//TODO: Ensure Employee Id is okay
		
		this.employeeID = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailID = mailID;
		this.projectID = projectID;
	}
	
	

}
