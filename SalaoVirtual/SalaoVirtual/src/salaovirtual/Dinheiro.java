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
    private double valorRecebido;
    private double troco;

    public Dinheiro(double valor, int id) {
        super(valor, id);
    }
    
    public void Dinheiro(double valorRecebido){
        this.valorRecebido = valorRecebido;
        calcularTroco(valorRecebido, troco);
    }
    
    public void calcularTroco(double valorRecebido, double troco){
        this.setTroco(valorRecebido - this.getValorTotal());
    }

    public double getTroco() {
        return troco;
    }
    
    private void setTroco(double t) {
        this.troco = t;
    }
}