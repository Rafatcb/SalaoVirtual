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
public class ConjuntoFormaDePagamentos {
    static private HashMap<Integer, FormaDePagamento> pagamentos;
    
    public static void inicializarFormaDePagamentos() {
        // Aqui terá leitura de arquivo posteriormente
        pagamentos = new HashMap<>();
    }
    
    public static void inserirPagamento(FormaDePagamento f) throws ObjetoJaCadastradoException {
        if (getFormaDePagamento(f.getCodigo()) != null)
            throw new ObjetoJaCadastradoException();
        else
            pagamentos.put(f.getCodigo(), f);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = pagamentos.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static FormaDePagamento getFormaDePagamento(int codigo) {
        FormaDePagamento f;
        f = pagamentos.get(codigo);
        return f;
    }
}
