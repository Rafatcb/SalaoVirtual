/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.Date;

/**
 *
 * @author rafae
 */
public class Compra {
    private final int codigo;
    private int quantidade;
    // Colocar valor monetário
    private Date data;
    // Aqui precisará ter uma lista de produtos que foram comprados
    
    public void adicionarProduto() {
        
    }
    
    /* Métodos Construtores, Getters & Setters */
    public Compra(int codigo) {
        this.codigo = codigo;
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
