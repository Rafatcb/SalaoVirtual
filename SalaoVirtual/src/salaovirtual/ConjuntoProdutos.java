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
public class ConjuntoProdutos {
    static private HashMap<Integer, Produto> produtos;
    
    public static void inicializarProdutos() {
        // Aqui terá leitura de arquivo posteriormente
        produtos = new HashMap<>();
    }
    
    public static void inserirProduto(Produto p) throws ObjetoJaCadastradoException {
        if (getProduto(p.getCodigo()) != null)
            throw new ObjetoJaCadastradoException();
        else
            produtos.put(p.getCodigo(), p);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = produtos.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static Produto getProduto(int codigo) {
        Produto p;
        p = produtos.get(codigo);
        return p;
    }
}