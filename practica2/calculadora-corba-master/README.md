# Calculadora CORBA

Implementación de Una calculadora en CORBA.

### Setup

1. Run `tnameserv -ORBInitialPort 2000`
2. Move to the director containing `Calculadora.idl` and run `idlj -fall Calculadora.idl`
3. Build and run `CalcServer.java`  with arugments `java CalcServer -ORBInitialHost localhost -ORBInitialPort 2000`
4. Build and run any number of `CalcClient` with arguments `java CalcClient -ORBInitialHost localhost -ORBInitialPort 2000`