/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author r176257
 */
public class Cliente implements java.io.Serializable {
    private Integer codigo;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private Date dataAniversario;

    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codigo + ", cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", dataAniversario=" + dataAniversario + '}';
    }
    
    private void serializar() {
        try {
            FileOutputStream arq = new FileOutputStream("Cliente.dat", true);
            ObjectOutputStream saida = new ObjectOutputStream(arq);
            saida.writeObject(this);
            saida.close();
            arq.close();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }

    private void deserializar() {
        try {
            Cliente c;
            FileInputStream arq = new FileInputStream("Cliente.dat");
            ObjectInputStream entrada = new ObjectInputStream(arq);
            c = (Cliente) entrada.readObject();
            
            this.setCodigo(c.getCodigo());
            this.setCpf(c.getCpf());
            this.setDataAniversario(c.getDataAniversario());
            this.setEmail(c.getEmail());
            this.setNome(c.getNome());
            this.setTelefone(c.getTelefone());
            
            entrada.close();
            arq.close();
        }
        catch(IOException i) {
            i.printStackTrace();
            return;
        }
        catch(ClassNotFoundException ce) {
            // Classe não encontrada
            ce.printStackTrace();
            return;
        }
    }
    
    public void gravarCliente() {
        //this.setCodigo(this.getProxCodigo());
        this.serializar();
    }
    
    public int getProxCodigo() {
        int cod = 0;
        try {
            FileInputStream arq = new FileInputStream("Cliente.dat");
            ObjectInputStream entrada = new ObjectInputStream(arq);
            while (true) {
                try {
                    Cliente c = (Cliente) entrada.readObject();
                    cod = c.getCodigo();
                } catch (EOFException e) {
                    break;
                }
            }
            entrada.close();
            arq.close();
        } catch (Exception e) {
            e.printStackTrace(); // handle this appropriately
        }
        return (cod+1);
    }
    
    /* Métodos Construtores, Setters & Getters */
    public Cliente(Integer Codigo, String nome) {
        this.setNome(nome);
        this.codigo = Codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
