/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author r176257
 */
public class Cliente {
    private final int codigo;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private Date dataAniversario;

    public Cliente(int codigo, String nome) {
        this.setNome(nome);
        this.codigo = codigo;
        try {
            ConjuntoClientes.inserirCliente(this);
        } catch (ObjetoJaCadastradoException ex) {
            // Aqui foi erro do programador, pois o código será calculado internamente no programa
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
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
}
