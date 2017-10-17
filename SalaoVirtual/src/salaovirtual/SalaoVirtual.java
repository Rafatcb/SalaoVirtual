/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

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
        
        // TODO code application logic here
        Cliente c1 = new Cliente(1, "Rafael");
        Cliente c2 = new Cliente(2, "João");
        Cliente c3 = new Cliente(3, "Fábio");
        Cliente c4 = new Cliente(4, "Fabiano");
        Cliente c5 = new Cliente(5, "Murilo");
        c2.setEmail("olokobixo@tapegandofogo.com");
        Cliente c6 = new Cliente(6, "Olocoooooooo");
        
        System.out.println("\n" + c1.toString());
        c1.gravarCliente();
        System.out.println(c2.toString());
        c2.gravarCliente();
        System.out.println(c2.getProxCodigo());
    }         
}
