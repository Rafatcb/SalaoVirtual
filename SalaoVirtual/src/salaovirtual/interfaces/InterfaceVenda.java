/*
 * Interface contendo os métodos necessários para a implementação de uma Venda
 */
package salaovirtual.interfaces;

import salaovirtual.Produto;
import salaovirtual.Servico;

/**
 * Interface contendo os métodos necessários para a implementação de uma Venda
 *
 * @author Rafael Tavares
 */
public interface InterfaceVenda {

    /**
     * Método para adicionar o produto à venda
     * @param codigo
     * @param valor
     * @param quantidade
     */
    public void addProduto(int codigo, float valor, int quantidade);

    /**
     * Método para adicionar o produto à venda
     * @param produto
     * @param quantidade
     */
    public void addProduto(Produto produto, int quantidade);

    /**
     * Método para adicionar o serviço à venda
     * @param servico
     */
    public void addServico(Servico servico);

    /**
     * Método para adicionar o serviço à venda
     * @param codigo
     */
    public void addServico(int codigo);
}
