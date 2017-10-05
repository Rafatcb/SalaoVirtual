package salaovirtual;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author faad2
 */
public class  FormaDePagamento {
    private final int codigo;
    private double valorTotal;  // Arrumar valor monetário
    private final int identificador;  // 0 - Cartão; 1 - Dinheiro

    /* Métodos Construtores + Getters & Setters */
    protected FormaDePagamento(int codigo, double valor, int id) {
        this.identificador = id;
        setValorTotal(valor);
        this.codigo = codigo;
        try {
            ConjuntoFormaDePagamentos.inserirPagamento(this);
        } catch (ObjetoJaCadastradoException ex) {
            // Aqui foi erro do programador, pois o código será calculado internamente no programa
            // Logger.getLogger(FormaDePagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdentificador() {
        return identificador;
    }
}