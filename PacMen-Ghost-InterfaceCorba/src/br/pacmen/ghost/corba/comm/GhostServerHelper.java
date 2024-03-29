package br.pacmen.ghost.corba.comm;


/**
* comm/GhostServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ghost.idl
* Segunda-feira, 8 de Dezembro de 2008 00h12min01s GMT-03:00
*/

abstract public class GhostServerHelper
{
  private static String  _id = "IDL:comm/GhostServer:1.0";

  public static void insert (org.omg.CORBA.Any a, br.pacmen.ghost.corba.comm.GhostServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static br.pacmen.ghost.corba.comm.GhostServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (br.pacmen.ghost.corba.comm.GhostServerHelper.id (), "GhostServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static br.pacmen.ghost.corba.comm.GhostServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_GhostServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, br.pacmen.ghost.corba.comm.GhostServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static br.pacmen.ghost.corba.comm.GhostServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof br.pacmen.ghost.corba.comm.GhostServer)
      return (br.pacmen.ghost.corba.comm.GhostServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      br.pacmen.ghost.corba.comm._GhostServerStub stub = new br.pacmen.ghost.corba.comm._GhostServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static br.pacmen.ghost.corba.comm.GhostServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof br.pacmen.ghost.corba.comm.GhostServer)
      return (br.pacmen.ghost.corba.comm.GhostServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      br.pacmen.ghost.corba.comm._GhostServerStub stub = new br.pacmen.ghost.corba.comm._GhostServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
