/*
 * Interface contendo os métodos necessários para uma compra/venda em dinheiro
 */
package salaovirtual.interfaces;

/**
 * Interface contendo os métodos necessários para uma compra/venda em dinheiro
 *
 * @author Rafael Tavares
 */
public interface InterfacePagamentoDinheiro {

    /**
     * Método para calcular o troco com base no valor recebido
     * @return Troco
     */
    public float calcularTroco();
}
