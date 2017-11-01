/*
 * Interface contendo os métodos necessários para a implementação de uma Compra
 */
package salaovirtual.interfaces;

import salaovirtual.Produto;

/**
 * Interface contendo os métodos necessários para a implementação de uma Compra
 *
 * @author Rafael Tavares
 */
public interface InterfaceCompra {

    /**
     * Método para atualizar o valor total da compra com base em todos os produtos existentes nela
     */
    public void atualizarValorTotal();
    
    /**
     * Método para adicionar um produto à compra
     * @param produto
     * @param valor
     * @param quantidade
     */
    public void addProduto(Produto produto, float valor, int quantidade);

    /**
     * Método para adicionar um produto à compra
     * @param codigo
     * @param valor
     * @param quantidade
     */
    public void addProduto(int codigo, float valor, int quantidade);
}
