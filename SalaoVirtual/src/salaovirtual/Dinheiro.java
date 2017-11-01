/*
 * Classe referente à forma de pagamento em dinheiro
 */
package salaovirtual;

import salaovirtual.interfaces.InterfacePagamentoDinheiro;

/**
 * Classe referente à forma de pagamento em dinheiro
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>id: identificador da forma de pagamento: 0 = Cartao, 1 = Dinheiro
 * 
 * @author Rafael Tavares
 * @author Fábio Augusto
 * 
 */
public class Dinheiro extends FormaDePagamento implements InterfacePagamentoDinheiro {
    private float valorRecebido;
    
    /**
     * Cálculo do troco
     * Polimorfismo: Sobrescrita
     * @return Troco
     */
    @Override
    public float calcularTroco() {
        return (this.valorRecebido - this.getValorTotal());
    }

    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        return this.getCodigo() + ";" + this.getValorTotal() + ";" + this.getIdentificador() + ";" + 
                this.getValorRecebido();
    }
    
    /* Métodos Construtores, Setters & Getters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param valorVenda
     * @param valorRecebido
     */
    public Dinheiro(float valorVenda, float valorRecebido) {
        super(valorVenda, 1);
        this.valorRecebido = valorRecebido;
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    public Dinheiro() {
        super();
    }

    /**
     * Retorna o valor recebido em dinheiro
     * @return Valor recebido
     */
    public float getValorRecebido() {
        return valorRecebido;
    }

    /**
     * Define o valor recebido em dinheiro
     * @param valorRecebido 
     */
    public void setValorRecebido(float valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
    
}