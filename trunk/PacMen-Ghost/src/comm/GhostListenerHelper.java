package comm;


/**
* comm/GhostListenerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ghost.idl
* Ter�a-feira, 18 de Novembro de 2008 20h47min30s GMT-03:00
*/

abstract public class GhostListenerHelper
{
  private static String  _id = "IDL:comm/GhostListener:1.0";

  public static void insert (org.omg.CORBA.Any a, comm.GhostListener that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static comm.GhostListener extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (comm.GhostListenerHelper.id (), "GhostListener");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static comm.GhostListener read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_GhostListenerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, comm.GhostListener value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static comm.GhostListener narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof comm.GhostListener)
      return (comm.GhostListener)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      comm._GhostListenerStub stub = new comm._GhostListenerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static comm.GhostListener unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof comm.GhostListener)
      return (comm.GhostListener)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      comm._GhostListenerStub stub = new comm._GhostListenerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
