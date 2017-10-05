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
public class ConjuntoServicos {
    static private HashMap<Integer, Servico> servicos;
    
    public static void inicializarServicos() {
        // Aqui terá leitura de arquivo posteriormente
        servicos = new HashMap<>();
    }
    
    public static void inserirServico(Servico c) throws ObjetoJaCadastradoException {
        if (getServico(c.getCodigo()) != null)
            throw new ObjetoJaCadastradoException();
        else
            servicos.put(c.getCodigo(), c);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = servicos.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static Servico getServico(int codigo) {
        Servico s;
        s = servicos.get(codigo);
        return s;
    }
}
