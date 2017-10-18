/*
 * Classe referente ao fornecedor
 */
package salaovirtual;

import java.util.Map;

/**
 * Classe referente ao fornecedor
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>produtos: É um map onde a Key é o código, e Value é seu valor (preço)
 * 
 * @author Rafael Tavares
 */
public class Fornecedor {
    private int codigo; 
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String rua;
    private int numero;
    private String cidade;
    private String estado;
    private String complemento;
    private Map<Integer, Float> produtos;  // Código do produto e seu valor

    
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        return this.codigo + ";" + this.cnpj + ";" + this.nome + ";" + this.telefone + ";" + this.email + 
                ";" + this.rua + ";" + this.numero + ";" + this.cidade + ";" + this.estado + ";" + this.complemento;                
    }
    
    /* Métodos Construtores, Getters & Setters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param cnpj 
     */
    public Fornecedor(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    public Fornecedor () {
        
    }

    /**
     * Define um Map dos produtos possuindo como chave o código do produto e como valor o valor que o
     * fornecedor fornece este produto
     * @param produtos 
     */
    public void setProdutos(Map<Integer, Float> produtos) {
        this.produtos = produtos;
    }

    /**
     * Retorna um Map dos produtos possuindo como chave o código do produto e como valor o valor que o 
     * fornecedor fornece este produto
     * @return Map de Produtos
     */
    public Map<Integer, Float> getProdutos() {
        return produtos;
    }

    /**
     * Define o código do fornecedor
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Define o CNPJ do fornecedor
     * @param cnpj 
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    /**
     * Retorna o complemento de onde o fornecedor está estabelecido em relação ao número
     * @return Complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Define o complemento de onde o fornecedor está estabelecido em relação ao número
     * @param complemento 
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Retorna o código do fornecedor no sistema
     * @return Código
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna o CNPJ do fornecedor
     * @return  CNPJ
     */
    public String getCnpj() {
        return cnpj;
    }
    
    /**
     * Retorna o nome do fornecedor
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do fornecedor
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o telefone do fornecedor
     * @return Telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do fornecedor
     * @param telefone 
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o e-mail do fornecedor
     * @return E-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do fornecedor
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a rua na qual o fornecedor está estabelecido
     * @return Rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * Define a rua na qual o fornecedor está estabelecido
     * @param rua 
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Retorna o número do estabelecimento do fornecedor em relação à rua
     * @return Numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Define o número do estabelecimento do fornecedor em relação à rua
     * @param numero 
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Retorna a cidade na qual o fornecedor está estabelecido
     * @return Cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Define a cidade na qual o fornecedor está estabelecido
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Retorna o estado no qual o fornecedor está estabelecido
     * @return Estado 
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado no qual o fornecedor está estabelecido
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }   
}