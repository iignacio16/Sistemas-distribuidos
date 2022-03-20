// Lo primero de todo importamos los paquetes necesarios para el server
import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;

//Definimos la clase para el objeto, el servidor es una cubclase de HelloPOA que hereda
//la funcionalidad general de CORBA
class HelloImpl extends HelloPOA {
  private ORB orb;

  // Metodo serORB se utiliza para poder establecer el valor de ORB con el servidor.
  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  // implement sayHello() method
  public String sayHello() {
    return "\nHello world !!\n";
  }
    
  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
    //Essta instruccion indica que el servidor debe apagarse sin esperar a que se complete el procesado del codigo.
  }
}

//Declaramos la clase server
public class HelloServer {

  public static void main(String args[]) {
    // try-catch porque CORBA puede lanzar errores mientras se compila y los imprima por pantalla 
    try{
      // un server de CORBA necesita un objeto ORB local, cada servidor instancia un ORB para que este
      // pueda encontrar el servidor cuando recibe una peticion
      ORB orb = ORB.init(args, null);

      // El ORB obtiene las referencias de objetos iniciales
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // create servant and register it with the ORB
      HelloImpl helloImpl = new HelloImpl();
      helloImpl.setORB(orb); 

      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
      Hello href = HelloHelper.narrow(ref);
          
      // get the root naming context
      // NameService invokes the name service
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      // Usar NamingContextExt lo que es parte de la interoperabilidad
      // Naming Service (INS) specification.
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // bind the Object Reference in Naming
      String name = "Hello";
      //Creamos un array de un elemento porque el path a hello tiene solo un elemento.
      NameComponent path[] = ncRef.to_name( name );
      //Pasamos el path y el objeto del servidor al naming service
      ncRef.rebind(path, href);

      //Ahora cuando el cliente llama al metodo resolve("Hello"), el naming service devuelve una referencia al  objeto Hello del servidor

      System.out.println("HelloServer ready and waiting ...");

      //Hasta aqui el codigo inicializa el servidor
      // orb.run() es el codigo incia el servidor que se queda a la espera de un cliente
      orb.run();
    } 
        
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
          
      System.out.println("HelloServer Exiting ...");
        
  }
}
 