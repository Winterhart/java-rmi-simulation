package HrCenterApp;


/**
* HrCenterApp/DEMSOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Wednesday, October 24, 2018 5:25:09 o'clock PM EDT
*/

public interface DEMSOperations 
{
  String createMRecord (String firstName, String lastName, String employeeID, String mailID, String managerID, HrCenterApp.DEMSPackage.Project[] projects, HrCenterApp.DEMSPackage.ServerLocation location, String managerAuthorOfRequest);
  String createERecord (String firstName, String lastName, String employeeID, String mailID, String projectID, String managerID);
  String getRecordCounts (String managerID);
  String editRecord (String recordID, String fieldName, String newValue);
  String transferRecord (String managerID, String recordID, HrCenterApp.DEMSPackage.ServerLocation location);
  void shutdown (String managerID);
  boolean managerLogin (String managerID);
  String getWelcomeMessage (String managerID);
} // interface DEMSOperations
