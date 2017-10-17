/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
        try {
            Funcionario f1 = new Funcionario("login1", "senha1", "Rafael");
        } catch (ChaveNulaException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ObjetoJaCadastradoException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Funcionario f2 = new Funcionario("login2", "senha2", "João");
        } catch (ChaveNulaException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ObjetoJaCadastradoException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Funcionario f3 = new Funcionario("login3", "senha3", "Fábio");
        } catch (ChaveNulaException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ObjetoJaCadastradoException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n\n\n");
        Cliente c = new Cliente();
        
        /*Cliente c1 = new Cliente("Rafael");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            c1.setDataAniversario(formato.parse("23/02/1997"));
        } catch (ParseException ex) {
           // Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        }
        c1.setEmail("rafa@unicamp.br");
        c1.setCpf("4444");
        c1.setTelefone("(13) 9234-5678");
        System.out.println(c1.toString());
        c1.gravarCliente();
        System.out.println("Gravou!");*/
        /*System.out.println("----------------");
        c = c.encontrarCliente(1);
        System.out.println(c.toString());
        System.out.println("----------------");
        c = c.encontrarCliente(2);
        System.out.println(c.toString());
        System.out.println("----------------");
        c = c.encontrarCliente(3);
        System.out.println(c.toString());
        System.out.println("----------------");
        try {
            c = c.encontrarCliente(5);   // Quando for buscar por cliente, precisa do NullPointerException pra saber se o cliente foi ou não encontrado
            System.out.println(c.toString());
        }
        catch (NullPointerException e) {
            System.out.println("Cliente não encontrado");
        }
        System.out.println("----------------");
        c = c.encontrarCliente(2);
        System.out.println(c.toString());
        System.out.println("----------------");
        List<Cliente> clientes = c.encontrarCliente("Rafael");
        System.out.println("TODOS RAFAELSSSSS");
        for (Cliente item : clientes) {
            System.out.println(item.toString());
        }*/
    }         
}
