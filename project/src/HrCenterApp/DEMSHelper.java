package HrCenterApp;


/**
* HrCenterApp/DEMSHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DEMS.idl
* Wednesday, October 24, 2018 6:30:43 o'clock PM EDT
*/

abstract public class DEMSHelper
{
  private static String  _id = "IDL:HrCenterApp/DEMS:1.0";

  public static void insert (org.omg.CORBA.Any a, HrCenterApp.DEMS that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static HrCenterApp.DEMS extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (HrCenterApp.DEMSHelper.id (), "DEMS");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static HrCenterApp.DEMS read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_DEMSStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, HrCenterApp.DEMS value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static HrCenterApp.DEMS narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HrCenterApp.DEMS)
      return (HrCenterApp.DEMS)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HrCenterApp._DEMSStub stub = new HrCenterApp._DEMSStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static HrCenterApp.DEMS unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HrCenterApp.DEMS)
      return (HrCenterApp.DEMS)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HrCenterApp._DEMSStub stub = new HrCenterApp._DEMSStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
