import java.io.*;
import java.util.*;
import MCalculadora.*;

public class ImplementacionFunciones extends ICalculadoraPOA {
    public ImplementacionFunciones() {
        super();
    }

    public double sumar(Operadores ops) {
        return (ops.a + ops.b);
    }

    public double restar(Operadores ops) {
        return ops.a - ops.b;
    }

    public double multiplicar(Operadores ops) {
        return ops.a * ops.b;
    }

    public double dividir(Operadores ops) {
        return ops.a / ops.b;
    }
}