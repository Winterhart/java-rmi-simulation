package HrCenterApp.DEMSPackage;


/**
* HrCenterApp/DEMSPackage/Project.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Monday, October 22, 2018 1:59:17 o'clock PM EDT
*/

public final class Project implements org.omg.CORBA.portable.IDLEntity
{
  public String projectID = null;
  public String clientName = null;
  public String projectName = null;

  public Project ()
  {
  } // ctor

  public Project (String _projectID, String _clientName, String _projectName)
  {
    projectID = _projectID;
    clientName = _clientName;
    projectName = _projectName;
  } // ctor

} // class Project