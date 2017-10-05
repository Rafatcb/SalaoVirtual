/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author r176257
 */
public class SalaoVirtual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConjuntoFuncionarios.inicializarFuncionarios();
        Funcionario f = null;
        try {
            f = new Funcionario("login1", "senha1", "Rafael");
        } catch (ChaveNulaException ex) {
            System.out.println("O login não pode ser nulo");
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ObjetoJaCadastradoException ex) {
            System.out.println("Este login já está cadastrado");
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConjuntoFuncionarios.testeMostrarTudo();
        try {
            ConjuntoFuncionarios.validarLoginSenha("login1", "123");
            System.out.println("Login realizado");
        } catch (LoginOuSenhaInvalidosException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        }       
 
    }         
}
