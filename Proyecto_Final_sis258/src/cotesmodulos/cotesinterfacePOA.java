package cotesmodulos;


/**
* cotesmodulos/cotesinterfacePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from cotesfacturas.idl
* lunes 29 de abril de 2019 01:34:08 AM BOT
*/

public abstract class cotesinterfacePOA extends org.omg.PortableServer.Servant
 implements cotesmodulos.cotesinterfaceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("facturaspendientes", new java.lang.Integer (0));
    _methods.put ("pagar", new java.lang.Integer (1));
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
       case 0:  // cotesmodulos/cotesinterface/facturaspendientes
       {
         int numero = in.read_long ();
         String $result = null;
         $result = this.facturaspendientes (numero);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // cotesmodulos/cotesinterface/pagar
       {
         int idfactura = in.read_long ();
         this.pagar (idfactura);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:cotesmodulos/cotesinterface:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public cotesinterface _this() 
  {
    return cotesinterfaceHelper.narrow(
    super._this_object());
  }

  public cotesinterface _this(org.omg.CORBA.ORB orb) 
  {
    return cotesinterfaceHelper.narrow(
    super._this_object(orb));
  }


} // class cotesinterfacePOA
