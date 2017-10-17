/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author r176257
 */
public class Venda {
    private final int codigo;
    private Date data;
    private Cliente cliente;
    private Funcionario funcionario;
    private FormaDePagamento formaPagamento;
    private List<Produto> produtos;
    private List<Servico> servicos;

    public void addProduto(Produto p) {
        // Falta verificar se é um produto válido baseado no arquivo de produtos
        this.produtos.add(p);
    }
    
    public void addServico(Servico s) {
        // Falta verificar se é um servico válido baseado no arquivo de servicos
        this.servicos.add(s);
    }
    
    /* Métodos Construtores, Getters & Setters */
    public Venda(int codigo, Cliente cliente, Funcionario funcionario) {
        this.produtos = new ArrayList<Produto>();
        this.servicos = new ArrayList<Servico>();
        this.codigo = codigo;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.setData();
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }

    private void setData() {
        this.data = new Date();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FormaDePagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaDePagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
}
