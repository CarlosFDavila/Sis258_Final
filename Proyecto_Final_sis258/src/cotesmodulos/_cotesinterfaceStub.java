package cotesmodulos;


/**
* cotesmodulos/_cotesinterfaceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from cotesfacturas.idl
* lunes 29 de abril de 2019 01:34:08 AM BOT
*/

public class _cotesinterfaceStub extends org.omg.CORBA.portable.ObjectImpl implements cotesmodulos.cotesinterface
{

  public String facturaspendientes (int numero)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("facturaspendientes", true);
                $out.write_long (numero);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return facturaspendientes (numero        );
            } finally {
                _releaseReply ($in);
            }
  } // facturaspendientes

  public void pagar (int idfactura)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("pagar", true);
                $out.write_long (idfactura);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                pagar (idfactura        );
            } finally {
                _releaseReply ($in);
            }
  } // pagar

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:cotesmodulos/cotesinterface:1.0"};

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
} // class _cotesinterfaceStub