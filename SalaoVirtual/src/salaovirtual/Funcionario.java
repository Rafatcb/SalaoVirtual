/*
 * Classe referente ao funcionário
 */
package salaovirtual;


/**
 * Classe referente ao funcionário
 * @author Rafael Tavares
 */
public class Funcionario {
    private String login;
    private String senha;
    private String cpf;
    private String nome;
    private String telefone;
    private String rua;
    private int numero;
    private String complemento;
    private String cidade;
    private String estado;
    
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        return this.login + ";" + this.senha + ";" + this.cpf + ";" + this.nome + ";" + this.telefone + 
                ";" + this.rua + ";" + this.numero + ";" + this.complemento + ";" + this.cidade + ";" + this.estado;
    }
    
    /* Métodos Construtores + Getters & Setters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param login
     * @param senha
     * @param nome
     */
    public Funcionario(String login, String senha, String nome) {
        this.setLogin(login);
        this.setSenha(senha);
        this.setNome(nome);
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */    
    public Funcionario() {
        
    }

    /**
     * Define o login do funcionário
     * @param login 
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Define o CPF do funcionário
     * @param cpf 
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    /**
     * Retorna o login do funcionário
     * @return Login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retorna o CPF do funcionário
     * @return 
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna a senha criptografada do funcionário
     * @return Senha criptografada
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do funcionário.
     * @param senha não criptografada
     */
    public void setSenha(String senha) {
        this.senha = Criptografar.criptografarMD5(senha);
    }

    /**
     * Retorna o nome do funcionário
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do funcionário
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o telefone do funcionário
     * @return Telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do funcionário
     * @param telefone 
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna a rua da residência do funcionário
     * @return Rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * Define a rua da residência do funcionário
     * @param rua 
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Retorna o número da residência do funcionário em relação à rua
     * @return Número
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Define o número da residência do funcionário
     * @param numero 
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Retorna a cidade da residência do funcionário
     * @return Cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Retorna a cidade da residência do funcionário
     * @param cidade 
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Define o estado da residência do funcionário
     * @return Estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado da residência do funcionário
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Retorna o complemento da residência do funcionário em relação ao número
     * @return 
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Define o complemento da residência do funcionário em relação ao número
     * @param complemento 
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}