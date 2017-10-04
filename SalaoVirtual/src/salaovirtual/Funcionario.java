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
public class Funcionario {
    private final String login;
    private String senha;
    private String cpf;
    private String nome;
    private String telefone;
    private String rua;
    private int numero;
    private String complemento;
    private String cidade;
    private String estado;

    public void validarLoginSenha(String login, String senha) throws FuncionarioInvalidoException {
        if ((!this.login.equals(login)) || (!this.senha.equals(Criptografar.criptografarMD5(senha)))) { // Se inválido
            throw new FuncionarioInvalidoException();
        }
    }
    
    /* Métodos Construtores + Getters & Setters */
    public Funcionario(String login, String senha, String nome) {
        this.login = login;
        this.setSenha(senha);
        this.setNome(nome);
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
        this.senha = Criptografar.criptografarMD5(senha);
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
