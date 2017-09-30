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
public class Cartao extends FormaDePagamento {
    private String tipo; // Crédito ou Débito
    private static final double taxaDebito = 2.4;
    private static final double taxaCredito = 3.2;
    private int qtdParcelas = 1;

    /* Métodos Construtores + Getters & Setters */
    public Cartao(int codigo, double valorVenda, int id) {
        super(codigo, valorVenda, id);
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) throws TipoDeCartaoInvalidoException {
        if (tipo.equals("Crédito") || tipo.equals("Débito")) {
            this.tipo = tipo;
        }
        else
            throw new TipoDeCartaoInvalidoException();
    }

    public double getTaxa() throws TipoDeCartaoInvalidoException {
        switch (tipo) {
            case "Crédito":
                return Cartao.taxaCredito;
            case "Débito":
                return Cartao.taxaDebito;
            default:            
                throw new TipoDeCartaoInvalidoException();
        }
    }

    public int getQtdParcelas() {
        return this.qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }
    
    
}
