/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author rafae
 */
public class ConjuntoCompras {
    static private HashMap<Integer, Compra> compras;
    
    public static void inicializarCompras() {
        // Aqui terá leitura de arquivo posteriormente
        compras = new HashMap<>();
    }
    
    public static void inserirCompra(Compra c) throws ObjetoJaCadastradoException {
        if (getCompra(c.getCodigo()) != null)
            throw new ObjetoJaCadastradoException();
        else
            compras.put(c.getCodigo(), c);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = compras.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static Compra getCompra(int codigo) {
        Compra c;
        c = compras.get(codigo);
        return c;
    }
}
