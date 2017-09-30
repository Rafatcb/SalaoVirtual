/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.Date;

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
    // Aqui precisará ter uma lista de produtos que foram comprados pelo cliente nesta compra
    // Aqui precisará ter uma lista de serviços que foram realizados pelo cliente nesta compra

    
    /* Métodos Construtores, Getters & Setters */
    public Venda(int codigo, Cliente cliente, Funcionario funcionario) {
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