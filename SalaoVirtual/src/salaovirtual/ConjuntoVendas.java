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
public class ConjuntoVendas {
    static private HashMap<Integer, Venda> vendas;
    
    public static void inicializarVendas() {
        // Aqui terá leitura de arquivo posteriormente
        vendas = new HashMap<>();
    }
    
    public static void inserirProduto(Venda v) throws ObjetoJaCadastradoException {
        if (getVenda(v.getCodigo()) != null)
            throw new ObjetoJaCadastradoException();
        else
            vendas.put(v.getCodigo(), v);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = vendas.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static Venda getVenda(int codigo) {
        Venda v;
        v = vendas.get(codigo);
        return v;
    }
}
