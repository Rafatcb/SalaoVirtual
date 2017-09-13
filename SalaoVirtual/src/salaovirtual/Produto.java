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
public class Produto {
    // private int codigo; Depois precisará colocar o código por causa do banco de dados
    private String nome;
    private int qtdEstoque;
    private int qtdEstoqueMin;

    public Produto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getQtdEstoqueMin() {
        return qtdEstoqueMin;
    }

    public void setQtdEstoqueMin(int qtdEstoqueMin) {
        this.qtdEstoqueMin = qtdEstoqueMin;
    }
    
}
