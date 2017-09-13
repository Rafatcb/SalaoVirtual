/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

/**
 *
 * @author r176257
 */
public class Fornecedor {
    // private int codigo; Depois precisará colocar o código por causa do banco de dados
    private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String rua;
    private int numero;
    private String cidade;
    private String estado;
    // Aqui precisará ter uma lista de produtos que este fornecedor fornece

    public Fornecedor(String c, String n, String t) {
        this.cnpj = c;
        this.setNome(n);
        this.setTelefone(t);
    }

    public String getCnpj() {
        return cnpj;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
