/*
 * Classe referente à compra de produtos
 */
package salaovirtual;

import exceptions.ChaveNulaException;
import exceptions.ObjetoNaoInseridoException;
import exceptions.QuantidadeInvalidaException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Date data;
    private float valorTotal;
    private List<Integer> produtos; // Cada produto
    private List<Float> valores; // Valor de cada produto
    private List<Integer> quantidade; // Quantidade de cada produto
    private Fornecedor fornecedor;
    
    /**
     * Método para atualizar o valor total com base na lista de produtos existentes
     */
    public void atualizarValorTotal() {
        valorTotal = 0;
        for (int i = 0; i < valores.size(); i++) {
            valorTotal += valores.get(i);
        }
    }
    
    /**
     * Adiciona produto à compra com base no objeto, valor e quantidade passada como parâmetro
     * Polimorfismo: Sobrescrita
     * @param p
     * @param valor
     * @param quantidade
     */
    public void addProduto(Produto p, float valor, int quantidade) {
        try {
            if (quantidade <= 0) {
                throw new QuantidadeInvalidaException();
            }
            this.produtos.add(p.getCodigo());
            this.valores.add(valor);
            this.quantidade.add(quantidade);
        }
        catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    /**
     * Adiciona produto à compra com base no código, valor e quantidade passada como parâmetro
     * Polimorfismo: Sobrescrita
     * @param codigo
     * @param valor
     * @param quantidade
     */
    public void addProduto(int codigo, float valor, int quantidade) {
        try {
            if (quantidade <= 0) {
                throw new QuantidadeInvalidaException();
            }
            this.produtos.add(codigo);
            this.valores.add(valor);
            this.quantidade.add(quantidade);
        }
        catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (this.data == null) {
            return this.codigo + ";" + this.valorTotal + ";" + this.data + ";" + this.fornecedor.getCodigo(); 
        }
        else {
            return this.codigo + ";" + this.valorTotal + ";" + formato.format(this.data) + ";" + this.fornecedor.getCodigo(); 
        }
    }
    
    /* Métodos Construtores, Setters & Getters */
    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    public Compra() {
        this.produtos = new ArrayList(); // Cada produto
        this.valores = new ArrayList(); // Valor de cada produto
        this.quantidade = new ArrayList(); // Quantidade de cada produto
    }

    /**
     * Retorna o valor total da compra
     * @return Valor total
     */
    public float getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor total da compra
     * @param valorTotal 
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Define o código da compra
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna uma lista dos produtos possuindo o código
     * @return Lista de código dos produtos
     */
    public List<Integer> getProdutos() {
        return produtos;
    }

    /**
     * Define uma lista dos produtos possuindo o codigo
     * @param produtos 
     */
    public void setProdutos(List<Integer> produtos) {
        this.produtos = produtos;
    }

    /**
     * Retorna uma lista dos valores dos produtos copmprados
     * @return Lista de valores dos produtos
     */
    public List<Float> getValores() {
        return valores;
    }

    /**
     * Define uma lista dos produtos possuindo o valor
     * @param valor 
     */
    public void setValores(List<Float> valor) {
        this.valores = valor;
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
     * Polimorfismo: Sobrecarga
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Define a data da compra caso tenha sido no dia em que foi criada a classe
     * Polimorfismo: Sobrecarga
     */
    public void setData() {
        this.data = new Date();
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