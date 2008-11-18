package comm;


/**
* comm/GhostServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ghost.idl
* Ter�a-feira, 18 de Novembro de 2008 20h47min30s GMT-03:00
*/

public abstract class GhostServerPOA extends org.omg.PortableServer.Servant
 implements comm.GhostServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("register", new java.lang.Integer (0));
    _methods.put ("ghostInfo", new java.lang.Integer (1));
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
       case 0:  // comm/GhostServer/register
       {
         comm.GhostListener listener = comm.GhostListenerHelper.read (in);
         this.register (listener);
         out = $rh.createReply();
         break;
       }

       case 1:  // comm/GhostServer/ghostInfo
       {
         short id = in.read_short ();
         org.omg.CORBA.ShortHolder x = new org.omg.CORBA.ShortHolder ();
         org.omg.CORBA.ShortHolder y = new org.omg.CORBA.ShortHolder ();
         boolean $result = false;
         $result = this.ghostInfo (id, x, y);
         out = $rh.createReply();
         out.write_boolean ($result);
         out.write_short (x.value);
         out.write_short (y.value);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:comm/GhostServer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public GhostServer _this() 
  {
    return GhostServerHelper.narrow(
    super._this_object());
  }

  public GhostServer _this(org.omg.CORBA.ORB orb) 
  {
    return GhostServerHelper.narrow(
    super._this_object(orb));
  }


} // class GhostServerPOA
