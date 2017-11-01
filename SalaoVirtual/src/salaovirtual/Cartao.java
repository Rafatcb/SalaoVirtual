/*
 * Classe referente à forma de pagamento em cartão
 */
package salaovirtual;

import salaovirtual.exceptions.TipoDeCartaoInvalidoException;

/**
 * Classe referente à forma de pagamento em cartão
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>id: identificador da forma de pagamento: 0 = Cartao, 1 = Dinheiro
 * <p>tipo: tipo do cartão, podendo ser "Crédito" ou "Débito"
 * 
 * @author Rafael Tavares
 * 
 */
public class Cartao extends FormaDePagamento {
    private String tipo; // Crédito ou Débito
    private static final float TAXA_DEBITO = (float) 1.024;  // 2,4%
    private static final float TAXA_CREDITO = (float) 1.032;  // 3,2%
    private int qtdParcelas = 1;


    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        return this.getCodigo() + ";" + this.getValorTotal() + ";" + this.getIdentificador() + ";" + 
                this.getTipo() + ";" + this.getQtdParcelas();
    }

    /* Métodos Construtores, Setters & Getters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param valorVenda
     * @param parcelas
     */
    public Cartao(float valorVenda, int parcelas) {
        super(valorVenda, 0);
        this.setQtdParcelas(parcelas);
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    public Cartao() {
        super();
    }
    
    /**
     * Retorna o tipo do cartão ("Crédito ou "Débito")
     * @return Tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo do cartão. Caso seja diferente de "Crédito" ou "Débito", é gerada a exceção TipoDeCartaoInvalidoException
     * @param tipo
     * @throws TipoDeCartaoInvalidoException 
     */
    public void setTipo(String tipo) throws TipoDeCartaoInvalidoException {
        if (tipo.equals("Crédito") || tipo.equals("Débito")) {
            this.tipo = tipo;
        }
        else
            throw new TipoDeCartaoInvalidoException();
    }

    /**
     * Retorna a taxa do cartão de acordo com o tipo. Caso não possua um tipo definido, 
     * é gerada a exceção TipoDeCartaoInvalidoException
     * @return Taxa
     * @throws TipoDeCartaoInvalidoException 
     */
    public float getTaxa() throws TipoDeCartaoInvalidoException {
        switch (tipo) {
            case "Crédito":
                return Cartao.TAXA_CREDITO;
            case "Débito":
                return Cartao.TAXA_DEBITO;
            default:            
                throw new TipoDeCartaoInvalidoException();
        }
    }

    /**
     * Retorna a quantidade de parcelas do pagamento em cartão
     */
    public int getQtdParcelas() {
        return this.qtdParcelas;
    }

    /**
     * Define a quantidade de parcelas do pagamento em cartão
     * @param qtdParcelas 
     */
    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }
}