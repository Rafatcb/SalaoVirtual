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
public final class ConjuntoFuncionarios {
    static private HashMap<String, Funcionario> funcionarios;
    
    public static void inicializarFuncionarios() {
        // Aqui terá leitura de arquivo posteriormente
        funcionarios = new HashMap<>();
    }
    
    public static void inserirFuncionario(Funcionario f) throws ObjetoJaCadastradoException {
        if (getFuncionario(f.getLogin()) != null)
            throw new ObjetoJaCadastradoException();
        else
            funcionarios.put(f.getLogin(), f);
    }
 
    public static void testeMostrarTudo() {
        Iterator it = funcionarios.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue().toString());
        }
    }
    
    public static Funcionario getFuncionario(String login) {
        Funcionario f;
        f = funcionarios.get(login);
        return f;
    }
    
    public static void validarLoginSenha(String login, String senha) throws LoginOuSenhaInvalidosException {
        Funcionario f;
        f = funcionarios.get(login);
        if ((f == null) || (!(f.getSenha()).equals(Criptografar.criptografarMD5(senha)))) { // Se inválido
            throw new LoginOuSenhaInvalidosException();
        }
    }
}
