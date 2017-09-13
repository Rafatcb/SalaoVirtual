package salaovirtual;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author faad2
 */
public class FormaDePagamento {
    // private int codigo; Depois precisará colocar o código por causa do banco de dados
    private double valorTotal;
    private int identificador;

    public FormaDePagamento(double valor, int id){
        this.identificador = id;
        this.valorTotal = valor;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    
}