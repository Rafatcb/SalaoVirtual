/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;
import java.util.ArrayList;
/**
 *
 * @author r176257
 */
public class Funcionario {
    ArrayList<Funcionario> funcionarios = new ArrayList(); 
    
    private final String login;
    private String senha;
    private final String cpf;
    private String nome;
    private String telefone;
    private String rua;
    private int numero;
    private String cidade;
    private String estado;

    public Funcionario(String l, String s, String c, String n, String t) {
        this.login = l;
        this.cpf = c;
        this.setSenha(s);
        this.setNome(n);
        this.setTelefone(telefone);
    }

    public String getLogin() {
        return login;
    }

    public String getCpf() {
        return cpf;
    }

    private String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
