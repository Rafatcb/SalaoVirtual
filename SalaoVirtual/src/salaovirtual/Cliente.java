/*
 * Classe referente ao cliente
 */
package salaovirtual;


import salaovirtual.interfaces.InformacaoPessoa;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Classe referente ao cliente
 * @author Rafael Tavares
 */
public class Cliente implements java.io.Serializable, InformacaoPessoa {
    private int codigo;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private Date dataAniversario;
 
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if (this.dataAniversario == null) {
            return this.codigo + ";" + this.cpf + ";" + this.nome + ";" + this.telefone + ";" + this.email + ";" + this.dataAniversario;
        }
        else{
            return this.codigo + ";" + this.cpf + ";" + this.nome + ";" + this.telefone + ";" + this.email + ";" + formato.format(this.dataAniversario);
        }
    }
    
    /* Métodos Construtores, Setters & Getters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param nome 
     */
    public Cliente(String nome) {
        this.setNome(nome);
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    public Cliente() {
    }
    
    /**
     * Define o código do cliente
     * @param codigo 
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Define o CPF do cliente
     * @param cpf 
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    /**
     * Retorna o código do cliente
     * @return Código
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna a data de aniversário do cliente
     * @return Data de Aniversário
     */
    public Date getDataAniversario() {
        return dataAniversario;
    }

    /**
     * Define a data de aniversário do cliente
     * @param dataAniversario 
     */
    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    /**
     * Retorna o CPF do cliente
     * @return CPF
     */
    @Override
    public String getCpf() {
        return cpf;
    }
    
    /**
     * Retorna o noe do cliente
     * @return Nome
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o telefone do cliente
     * @return 
     */
    @Override
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente
     * @param telefone 
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o e-mail do cliente
     * @return E-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do cliente
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
}