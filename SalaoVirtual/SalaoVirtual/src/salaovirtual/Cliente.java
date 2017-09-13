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
public class Cliente {
    // private int codigo; Depois precisará colocar o código por causa do banco de dados
    private final String cpf;
    private String nome;
    private String telefone;
    private String email;
    private int diaNascimento;  // Na próxima atualização do código, dia mês e ano serão um único atributo
    private int mesNascimento;
    private int anoNascimento; 

    public Cliente(String c, String n, String t) {
        this.cpf = c;
        this.setNome(n);
        this.setTelefone(t);
    }

    public String getCpf() {
        return cpf;
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

    public int getDiaNascimento() {
        return diaNascimento;
    }

    public void setDiaNascimento(int dia) {
        this.diaNascimento = dia;
    }

    public int getMesNascimento() {
        return mesNascimento;
    }

    public void setMesNascimento(int mes) {
        this.mesNascimento = mes;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int ano) {
        this.anoNascimento = ano;
    }
}
