package HrCenterApp.DEMSPackage;

/**
* HrCenterApp/DEMSPackage/ServerLocationHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Wednesday, October 24, 2018 6:30:43 o'clock PM EDT
*/

public final class ServerLocationHolder implements org.omg.CORBA.portable.Streamable
{
  public HrCenterApp.DEMSPackage.ServerLocation value = null;

  public ServerLocationHolder ()
  {
  }

  public ServerLocationHolder (HrCenterApp.DEMSPackage.ServerLocation initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HrCenterApp.DEMSPackage.ServerLocationHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HrCenterApp.DEMSPackage.ServerLocationHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HrCenterApp.DEMSPackage.ServerLocationHelper.type ();
  }

}