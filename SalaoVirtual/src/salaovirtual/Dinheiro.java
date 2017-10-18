/*
 * Classe referente à forma de pagamento em dinheiro
 */
package salaovirtual;

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
public class Dinheiro extends FormaDePagamento{
    private float valorRecebido;
    
    /**
     * Cálculo do troco
     * @param valorRecebido
     * @return Troco
     */
    public float calcularTroco(float valorRecebido){
        return (valorRecebido - this.getValorTotal());
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