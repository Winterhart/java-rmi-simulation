package HrCenterApp;


/**
* HrCenterApp/DEMSOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Friday, October 26, 2018 5:27:31 o'clock PM EDT
*/

public interface DEMSOperations 
{
  String createMRecord (String firstName, String lastName, String employeeID, String mailID, HrCenterApp.DEMSPackage.Project[] projects, HrCenterApp.DEMSPackage.Location location, String managerAuthorOfRequest);
  String createERecord (String firstName, String lastName, String employeeID, String mailID, String projectID, String managerID);
  String getRecordCounts (String managerID);
  String editRecord (String recordID, String fieldName, String newValue, String managerID);
  String transferRecord (String managerID, String recordID, HrCenterApp.DEMSPackage.Location location);
  String printAllRecords ();
  String printAllProjects ();
  void shutdown (String managerID);
  boolean managerLogin (String managerID);
  String getWelcomeMessage (String managerID);
} // interface DEMSOperations