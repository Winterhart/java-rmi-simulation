package HrCenterApp.DEMSPackage;


/**
* HrCenterApp/DEMSPackage/ServerLocationHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Wednesday, October 24, 2018 5:25:09 o'clock PM EDT
*/

abstract public class ServerLocationHelper
{
  private static String  _id = "IDL:HrCenterApp/DEMS/ServerLocation:1.0";

  public static void insert (org.omg.CORBA.Any a, HrCenterApp.DEMSPackage.ServerLocation that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static HrCenterApp.DEMSPackage.ServerLocation extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (HrCenterApp.DEMSPackage.ServerLocationHelper.id (), "ServerLocation", new String[] { "CA", "US", "UK"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static HrCenterApp.DEMSPackage.ServerLocation read (org.omg.CORBA.portable.InputStream istream)
  {
    return HrCenterApp.DEMSPackage.ServerLocation.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, HrCenterApp.DEMSPackage.ServerLocation value)
  {
    ostream.write_long (value.value ());
  }

}
