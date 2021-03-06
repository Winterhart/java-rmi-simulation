package HrCenterApp;


/**
* HrCenterApp/DEMSPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Friday, October 26, 2018 5:27:31 o'clock PM EDT
*/

public abstract class DEMSPOA extends org.omg.PortableServer.Servant
 implements HrCenterApp.DEMSOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("createMRecord", new java.lang.Integer (0));
    _methods.put ("createERecord", new java.lang.Integer (1));
    _methods.put ("getRecordCounts", new java.lang.Integer (2));
    _methods.put ("editRecord", new java.lang.Integer (3));
    _methods.put ("transferRecord", new java.lang.Integer (4));
    _methods.put ("printAllRecords", new java.lang.Integer (5));
    _methods.put ("printAllProjects", new java.lang.Integer (6));
    _methods.put ("shutdown", new java.lang.Integer (7));
    _methods.put ("managerLogin", new java.lang.Integer (8));
    _methods.put ("getWelcomeMessage", new java.lang.Integer (9));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // HrCenterApp/DEMS/createMRecord
       {
         String firstName = in.read_wstring ();
         String lastName = in.read_wstring ();
         String employeeID = in.read_wstring ();
         String mailID = in.read_wstring ();
         HrCenterApp.DEMSPackage.Project projects[] = HrCenterApp.DEMSPackage.managerProjectsHelper.read (in);
         HrCenterApp.DEMSPackage.Location location = HrCenterApp.DEMSPackage.LocationHelper.read (in);
         String managerAuthorOfRequest = in.read_wstring ();
         String $result = null;
         $result = this.createMRecord (firstName, lastName, employeeID, mailID, projects, location, managerAuthorOfRequest);
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 1:  // HrCenterApp/DEMS/createERecord
       {
         String firstName = in.read_wstring ();
         String lastName = in.read_wstring ();
         String employeeID = in.read_wstring ();
         String mailID = in.read_wstring ();
         String projectID = in.read_wstring ();
         String managerID = in.read_wstring ();
         String $result = null;
         $result = this.createERecord (firstName, lastName, employeeID, mailID, projectID, managerID);
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 2:  // HrCenterApp/DEMS/getRecordCounts
       {
         String managerID = in.read_wstring ();
         String $result = null;
         $result = this.getRecordCounts (managerID);
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 3:  // HrCenterApp/DEMS/editRecord
       {
         String recordID = in.read_wstring ();
         String fieldName = in.read_wstring ();
         String newValue = in.read_wstring ();
         String managerID = in.read_wstring ();
         String $result = null;
         $result = this.editRecord (recordID, fieldName, newValue, managerID);
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 4:  // HrCenterApp/DEMS/transferRecord
       {
         String managerID = in.read_wstring ();
         String recordID = in.read_wstring ();
         HrCenterApp.DEMSPackage.Location location = HrCenterApp.DEMSPackage.LocationHelper.read (in);
         String $result = null;
         $result = this.transferRecord (managerID, recordID, location);
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 5:  // HrCenterApp/DEMS/printAllRecords
       {
         String $result = null;
         $result = this.printAllRecords ();
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 6:  // HrCenterApp/DEMS/printAllProjects
       {
         String $result = null;
         $result = this.printAllProjects ();
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 7:  // HrCenterApp/DEMS/shutdown
       {
         String managerID = in.read_wstring ();
         this.shutdown (managerID);
         out = $rh.createReply();
         break;
       }

       case 8:  // HrCenterApp/DEMS/managerLogin
       {
         String managerID = in.read_wstring ();
         boolean $result = false;
         $result = this.managerLogin (managerID);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 9:  // HrCenterApp/DEMS/getWelcomeMessage
       {
         String managerID = in.read_wstring ();
         String $result = null;
         $result = this.getWelcomeMessage (managerID);
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:HrCenterApp/DEMS:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public DEMS _this() 
  {
    return DEMSHelper.narrow(
    super._this_object());
  }

  public DEMS _this(org.omg.CORBA.ORB orb) 
  {
    return DEMSHelper.narrow(
    super._this_object(orb));
  }


} // class DEMSPOA
