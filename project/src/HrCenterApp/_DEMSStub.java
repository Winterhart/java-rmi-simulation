package HrCenterApp;


/**
* HrCenterApp/_DEMSStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Monday, October 22, 2018 12:50:39 o'clock PM EDT
*/

public class _DEMSStub extends org.omg.CORBA.portable.ObjectImpl implements HrCenterApp.DEMS
{

  public String createMRecord (String firstName, String lastName, String employeeID, String mailID, String managerID, HrCenterApp.DEMSPackage.Project project)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createMRecord", true);
                $out.write_wstring (firstName);
                $out.write_wstring (lastName);
                $out.write_wstring (employeeID);
                $out.write_wstring (mailID);
                $out.write_wstring (managerID);
                HrCenterApp.DEMSPackage.ProjectHelper.write ($out, project);
                $in = _invoke ($out);
                String $result = $in.read_wstring ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createMRecord (firstName, lastName, employeeID, mailID, managerID, project        );
            } finally {
                _releaseReply ($in);
            }
  } // createMRecord

  public String createERecord (String firstName, String lastName, String employeeID, String mailID, String projectID, HrCenterApp.DEMSPackage.ServerLocation location, String managerID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createERecord", true);
                $out.write_wstring (firstName);
                $out.write_wstring (lastName);
                $out.write_wstring (employeeID);
                $out.write_wstring (mailID);
                $out.write_wstring (projectID);
                HrCenterApp.DEMSPackage.ServerLocationHelper.write ($out, location);
                $out.write_wstring (managerID);
                $in = _invoke ($out);
                String $result = $in.read_wstring ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createERecord (firstName, lastName, employeeID, mailID, projectID, location, managerID        );
            } finally {
                _releaseReply ($in);
            }
  } // createERecord

  public String getRecordCounts (String managerID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRecordCounts", true);
                $out.write_wstring (managerID);
                $in = _invoke ($out);
                String $result = $in.read_wstring ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRecordCounts (managerID        );
            } finally {
                _releaseReply ($in);
            }
  } // getRecordCounts

  public String editRecord (String recordID, String fieldName, String newValue)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("editRecord", true);
                $out.write_wstring (recordID);
                $out.write_wstring (fieldName);
                $out.write_wstring (newValue);
                $in = _invoke ($out);
                String $result = $in.read_wstring ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return editRecord (recordID, fieldName, newValue        );
            } finally {
                _releaseReply ($in);
            }
  } // editRecord

  public String transferRecord (String managerID, String recordID, HrCenterApp.DEMSPackage.ServerLocation location)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("transferRecord", true);
                $out.write_wstring (managerID);
                $out.write_wstring (recordID);
                HrCenterApp.DEMSPackage.ServerLocationHelper.write ($out, location);
                $in = _invoke ($out);
                String $result = $in.read_wstring ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return transferRecord (managerID, recordID, location        );
            } finally {
                _releaseReply ($in);
            }
  } // transferRecord

  public void shutdown ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("shutdown", false);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                shutdown (        );
            } finally {
                _releaseReply ($in);
            }
  } // shutdown

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:HrCenterApp/DEMS:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _DEMSStub
