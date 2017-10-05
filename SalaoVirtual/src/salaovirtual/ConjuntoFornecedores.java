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
public class ConjuntoFornecedores {
    static private HashMap<Integer, Fornecedor> fornecedores;
    
    public static void inicializarProdutos() {
        // Aqui terá leitura de arquivo posteriormente
        fornecedores = new HashMap<>();
    }
    
    public static void inserirProduto(Fornecedor f) throws ObjetoJaCadastradoException {
        if (getFornecedor(f.getCodigo()) != null)
            throw new ObjetoJaCadastradoException();
        else
            fornecedores.put(f.getCodigo(), f);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = fornecedores.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static Fornecedor getFornecedor(int codigo) {
        Fornecedor f;
        f = fornecedores.get(codigo);
        return f;
    }
}
