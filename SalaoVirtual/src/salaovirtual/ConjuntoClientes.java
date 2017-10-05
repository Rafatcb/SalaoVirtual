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
public class ConjuntoClientes {
    static private HashMap<Integer, Cliente> clientes;
    
    public static void inicializarFormaDePagamentos() {
        // Aqui terá leitura de arquivo posteriormente
        clientes = new HashMap<>();
    }
    
    public static void inserirCliente(Cliente c) throws ObjetoJaCadastradoException {
        if (getCliente(c.getCodigo()) != null)
            throw new ObjetoJaCadastradoException();
        else
            clientes.put(c.getCodigo(), c);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = clientes.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static Cliente getCliente(int codigo) {
        Cliente c;
        c = clientes.get(codigo);
        return c;
    }
}
