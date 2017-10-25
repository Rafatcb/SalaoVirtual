/*
 * Trabalho realizado para a disciplina de POO II da Faculdade de Tecnologia - UNICAMP
 * O projeto visa a criação de um sistema para salão de beleza
 */
package salaovirtual;

import salaovirtual.access.Consulta;
import salaovirtual.access.Cadastro;
import exceptions.TipoDeCartaoInvalidoException;
import exceptions.ChaveNulaException;
import exceptions.DataInvalidaException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fábio Augusto
 * @author João Gabriel
 * @author Rafael Tavares
 */
public class SalaoVirtual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ChaveNulaException, TipoDeCartaoInvalidoException {        
        //System.out.println("\n\n\n");
        /* ROTEIRO DE TESTE DO CSV (neste caso, para Cliente, basta adaptar para outros casos) */
        
        
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
        
        /*Cadastro cad = new Cadastro();
        Consulta con = new Consulta();
        Fornecedor f = new Fornecedor();
        f.setCidade("Limeira");
        f.setCnpj("111111");
        f.setComplemento("A");
        f.setEmail("forn@ecedor.com");
        f.setEstado("São Paulo");
        f.setNome("Padrão");
        f.setNumero(10);
        f.setRua("Avenida Dr. Fabrício Vampré");
        f.setTelefone("(19) 1020-3040");
        Map<Integer, Float> mapa = new HashMap<>();
        mapa.put(1, 10.3f);
        mapa.put(2, 15.0f);
        f.setProdutos(mapa);
        System.out.println(f.toString());
        cad.gravarFornecedor(f);
        System.out.println("Gravou!");
        List<Fornecedor> fornecedores = new ArrayList<>();
        fornecedores = con.encontrarFornecedorNome("Padrão");
        for (Fornecedor item : fornecedores) {
            System.out.println(item.toString());
            for (Map.Entry<Integer, Float> pair : item.getProdutos().entrySet()){
                System.out.println(item.getCodigo() + ";" + pair.getKey() + ";" + pair.getValue());
            }
        }*/       
        
        Cadastro cad = new Cadastro();
        Consulta con = new Consulta();
        //Venda v = new Venda();
        /*Cliente c = con.encontrarCliente(1);
        Dinheiro d = con.encontrarFormaDePagamentoDinheiro(5);
        Funcionario f = con.encontrarFuncionarioLogin("rafatcb");
        
        v.setCliente(c);
        v.setData();
        v.setFormaPagamento(d);
        v.setFuncionario(f);
        v.addProduto(1, 1);
        v.addProduto(2, 2);
        v.addServico(1, 1);
        v.addServico(2, 1);
        
        
        System.out.println(v.toString());
        cad.gravarVenda(v);
        System.out.println("Gravou!");
        
        
        c = con.encontrarCliente(2);
        d = con.encontrarFormaDePagamentoDinheiro(2);
        f = con.encontrarFuncionarioLogin("leonidas");
        v = new Venda();
        v.setCliente(c);
        v.setData();
        v.setFormaPagamento(d);
        v.setFuncionario(f);
        v.addProduto(3, 1);
        v.addServico(3, 1);
        
        
        System.out.println(v.toString());
        cad.gravarVenda(v);
        System.out.println("Gravou!");*/
        /*v = con.encontrarVenda(2);
        System.out.println(v.toString());
        System.out.println("Vendas");
        for (Map.Entry<Integer, Integer> pair : v.getProdutos().entrySet()){
            System.out.println(v.getCodigo() + ";" + pair.getKey() + ";" + pair.getValue());
        }
        System.out.println("Serviços");
        for (Map.Entry<Integer, Integer> pair : v.getServicos().entrySet()){
            System.out.println(v.getCodigo() + ";" + pair.getKey() + ";" + pair.getValue());
        }*/
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Servico s = new Servico();
        Cliente c = new Cliente();
        Funcionario f = new Funcionario();
        c = con.encontrarCliente(1);
        f = con.encontrarFuncionarioLogin("leonidas");
        try {
            s.agendarServico(c, f, sdf.parse("27/07/2020"));
            s.setNome("Corte de cabelo");
            s.setValor(20.0f);
        } catch (ParseException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataInvalidaException ex)  {
            
        }
        cad.gravarServico(s);
        c = con.encontrarCliente(2);
        f = con.encontrarFuncionarioLogin("rafatcb");
        try {
            s.agendarServico(c, f, sdf.parse("20/05/2018"));
            s.setNome("Pintura de cabelo");
            s.setValor(20.0f);
        } catch (ParseException ex) {
            //Logger.getLogger(SalaoVirtual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataInvalidaException ex)  {
            
        }
        cad.gravarServico(s);*/
    }
}
