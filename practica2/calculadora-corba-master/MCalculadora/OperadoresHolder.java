package MCalculadora;

/**
* MCalculadora/OperadoresHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calculadora.idl
* domingo 20 de marzo de 2022 23H01' CET
*/

public final class OperadoresHolder implements org.omg.CORBA.portable.Streamable
{
  public MCalculadora.Operadores value = null;

  public OperadoresHolder ()
  {
  }

  public OperadoresHolder (MCalculadora.Operadores initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MCalculadora.OperadoresHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MCalculadora.OperadoresHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MCalculadora.OperadoresHelper.type ();
  }

}
