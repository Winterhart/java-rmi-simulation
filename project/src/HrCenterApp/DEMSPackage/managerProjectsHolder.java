package HrCenterApp.DEMSPackage;


/**
* HrCenterApp/DEMSPackage/managerProjectsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Tuesday, October 23, 2018 5:10:12 o'clock PM EDT
*/

public final class managerProjectsHolder implements org.omg.CORBA.portable.Streamable
{
  public HrCenterApp.DEMSPackage.Project value[] = null;

  public managerProjectsHolder ()
  {
  }

  public managerProjectsHolder (HrCenterApp.DEMSPackage.Project[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HrCenterApp.DEMSPackage.managerProjectsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HrCenterApp.DEMSPackage.managerProjectsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HrCenterApp.DEMSPackage.managerProjectsHelper.type ();
  }

}
