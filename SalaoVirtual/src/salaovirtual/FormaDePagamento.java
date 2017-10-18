/*
 * Classe referente à forma de pagamento
 */

package salaovirtual;

/**
 * Classe referente à forma de pagamento
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>id: identificador da forma de pagamento: 0 = Cartao, 1 = Dinheiro
 * 
 * @author Rafael Tavares
 * @author Fábio Augusto
 * 
 */
public class  FormaDePagamento {
    private int codigo;
    private float valorTotal;  // Arrumar valor monetário
    private int identificador;  // 0 - Cartão; 1 - Dinheiro

    /* Métodos Construtores, Setters & Getters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param valor
     * @param id
     */
    protected FormaDePagamento(float valor, int id) {
        this.identificador = id;
        setValorTotal(valor);
    }
    
    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    protected FormaDePagamento() {
        
    }

    /**
     * Define o código da forma de pagamento
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Define o identificador da forma de pagamento
     * @param identificador 
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    /**
     * Retorna o código da forma de pagamento
     * @return Código
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Retorna o valor total da forma de pagamento
     * @return Valor Total
     */
    public float getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor total da forma de pagamento
     * @param valorTotal 
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Retorna o identificador da forma de pagamento
     * @return 
     */
    public int getIdentificador() {
        return identificador;
    }
}