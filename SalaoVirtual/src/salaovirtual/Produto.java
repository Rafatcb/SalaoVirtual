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
    private final int codigo;
    private String nome;
    private String marca;
    private String unidade;
    private double qtdUnitaria;
    // Colocar valor monetário aqui 
    private int qtdEstoque;
    private int qtdEstoqueMin;

    public void encomendar() {
        
    }
    
    /* Construtores, Getters & Setters */
    public Produto(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getQtdUnitaria() {
        return qtdUnitaria;
    }

    public void setQtdUnitaria(double qtdUnitaria) {
        this.qtdUnitaria = qtdUnitaria;
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
