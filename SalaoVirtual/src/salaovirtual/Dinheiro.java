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
public class Dinheiro extends FormaDePagamento{
    private final double valorRecebido;

    public Dinheiro(int codigo, double valorVenda, int id, double valorRecebido) {
        super(codigo, valorVenda, id);
        this.valorRecebido = valorRecebido;
    }
    
    public double calcularTroco(double valorRecebido){
        return (valorRecebido - this.getValorTotal());
    }
}