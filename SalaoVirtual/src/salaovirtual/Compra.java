/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class Compra {
    private final int codigo;
    private int quantidade;
    private float valor;
    private Date data;
    private List<Produto> produtos;
    
    public void adicionarProduto() {
        
    }
    
    /* Métodos Construtores, Getters & Setters */
    public Compra(int codigo) {
        this.codigo = codigo;
        
    }

    public int getCodigo() {
        return codigo;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
