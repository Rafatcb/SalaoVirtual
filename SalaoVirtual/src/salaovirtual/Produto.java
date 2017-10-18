/*
 * Classe referente ao produto
 */
package salaovirtual;


/**
 * Classe referente ao produto
 * 
 * Atributos que merecem destaque para explicação:
 * unidade: é a unidade do produto (como por exemplo ml, litro, kg)
 * qtdUnitaria: é o quanto o produto possui
 * qtdEstoque: é a quantidade atual do produto em estoque
 * qtdEstoqueMin: é a quantidade mínima do produto que deve existir em estoque
 * 
 * @author Rafael Tavares
 */
public class Produto {
    private int codigo;
    private String nome;
    private String marca;
    private String unidade;
    private float qtdUnitaria;
    private float valor;
    private int qtdEstoque;
    private int qtdEstoqueMin;

    // Fazer o método encomendar()
    
    public void encomendar(Fornecedor f) {
        
    }

    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        return this.codigo + ";" + this.nome + ";" + this.marca + ";" + this.unidade + ";" + 
                this.qtdUnitaria + ";" + this.valor + ";" + this.qtdEstoque + ";" + this.qtdEstoqueMin;
    }
    
    /* Métodos Construtores, Getters & Setters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param nome 
     */
    public Produto(String nome) {
        this.nome = nome;
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    public Produto() {
        
    }

    /**
     * Retorna o valor do produto
     * @return Valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * Define o valor do produto
     * @param valor 
     */
    public void setValor(float valor) {
        this.valor = valor;
    }
    
    /**
     * Retorna a marca do produto
     * @return Marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Retorna o código do produto
     * @return Código
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código do produto
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Define a marca do produto
     * @param marca 
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Retorna a unidade do produto. Por exemplo: ml, litros, kg
     * @return Unidade
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * Define a unidade do produto. Por exemplo: ml, litro, kg
     * @param unidade 
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Retorna a quantidade unitária do produto. Por exemplo: se ele possui 500 ml, sua quantidade unitária
     * é 500
     * @return Quantidade unitária
     */
    public float getQtdUnitaria() {
        return qtdUnitaria;
    }

    /**
     * Define a quantidade unitária do produto. Por exemplo: se ele possui 500 ml, sua quantidade unitária
     * é 500
     * @param qtdUnitaria 
     */
    public void setQtdUnitaria(float qtdUnitaria) {
        this.qtdUnitaria = qtdUnitaria;
    }

    /**
     * Retorna o nome do produto
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a quantidade atual em estoque do produto
     * @return Quantidade em estoque
     */
    public int getQtdEstoque() {
        return qtdEstoque;
    }

    /**
     * Define a quantidade atual em estoque do produto
     * @param qtdEstoque 
     */
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    /**
     * Retorna a quantidade mínima que o produto deve possuir em estoque
     * @return Quantidade mínima de estoque
     */
    public int getQtdEstoqueMin() {
        return qtdEstoqueMin;
    }

    /**
     * Define a quantidade mínima que o produto deve possuir em estoque
     * @param qtdEstoqueMin 
     */
    public void setQtdEstoqueMin(int qtdEstoqueMin) {
        this.qtdEstoqueMin = qtdEstoqueMin;
    }
    
}
