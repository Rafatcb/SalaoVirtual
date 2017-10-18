/*
 * Classe referente à compra de produtos
 */
package salaovirtual;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Classe referente à compra de produtos
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>produtos: É um map onde a Key é o código, e Value é seu valor (preço)
 * <p>quantidade: É uma list que possui a mesma quantidade de elementos que o map, onde cada posição da lista é referente à
 * mesma posição do mapa (ao mesmo produto)
 * 
 * @author Rafael Tavares
 */
public class Compra {
    private int codigo;
    private float valor;
    private Date data;
    private Map<Integer, Float> produtos; // Código do produto, Valor dele
    private List<Integer> quantidade; // Quantidade de cada produto
    private Fornecedor fornecedor;
    
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if (this.data == null) {
            return this.codigo + ";" + this.valor + ";" + this.data + ";" + this.fornecedor.getCodigo(); 
        }
        else {
            return this.codigo + ";" + this.valor + ";" + formato.format(this.data) + ";" + this.fornecedor.getCodigo(); 
        }
    }
    
    /* Métodos Construtores, Setters & Getters */
    /**
     * Método construtor
     */
    public Compra() {
               
    }

    /**
     * Define o código da compra
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna o valor da compra
     * @return Valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * Define o valor
     * @param valor 
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * Retorna um Map dos produtos possuindo como chave o código do produto e como valor o valor que o 
     * produto foi comprado
     * @return Map de Produtos
     */
    public Map<Integer, Float> getProdutos() {
        return produtos;
    }

    /**
     * Define um Map dos produtos possuindo como chave o código do produto e como valor o valor que o
     * produto foi comprado
     * @param produtos 
     */
    public void setProdutos(Map<Integer, Float> produtos) {
        this.produtos = produtos;
    }

    /**
     * Retorna o código da compra
     * @return Código
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Retorna a quantidade 
     * @return Lista de quantidade dos produtos comprados
     */
    public List<Integer> getQuantidade() {
        return quantidade;
    }

    /**
     * Define a lista de quantidade dos produtos comprados
     * @param quantidade 
     */
    public void setQuantidade(List<Integer> quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna a data da compra
     * @return Data
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data da compra
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }
    
    /**
     * Retorna o fornecedor da compra
     * @return Fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * Define o fornecedor da compra
     * @param fornecedor 
     */
    public void setFornecedor(Fornecedor fornecedor) throws ChaveNulaException {
        this.fornecedor = fornecedor;
    }
}