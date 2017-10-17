/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author r176257
 */
public class Cliente implements java.io.Serializable {
    private int codigo;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private Date dataAniversario;

    // Criar método para listar clientes que fazem aniversário em tal mês
    
    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        if (this.dataAniversario == null)
            return this.codigo + ";" + this.cpf + ";" + this.nome + ";" + this.telefone + ";" + this.email + ";" + this.dataAniversario;
        else{
            return this.codigo + ";" + this.cpf + ";" + this.nome + ";" + this.telefone + ";" + this.email + ";" + formato.format(this.dataAniversario);
        }
    }
    
    public void gravarCliente() {
        try {
            FileWriter arq = new FileWriter("Cliente.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            this.setCodigo(this.getProxCodigo());
            saida.write(this.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    public Cliente encontrarCliente(int codigo) {
        try {
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            Cliente c = new Cliente();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                linha = entrada.readLine();
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    c.setCodigo(parseInt(valor[0]));
                    c.setCpf(valor[1]);
                    c.setNome(valor[2]);
                    c.setTelefone(valor[3]);
                    c.setEmail(valor[4]);
                    if (!valor[5].equals("null")) {
                        c.setDataAniversario(formato.parse(valor[5]));
                    }
                    else {
                        c.setDataAniversario(null);
                    }
                    return c;
                }
            } while (linha != null);
        } catch (FileNotFoundException ex) {
            try {
                FileWriter arq = new FileWriter("Cliente.csv");
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex2) {
            // log
        } catch (ParseException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Cliente> encontrarCliente(String nome) {
        try {
            List<Cliente> clientes = new ArrayList();
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                Cliente c = new Cliente();
                String[] valor = linha.split(";");
                if (valor[2].equals(nome)) {
                    c.setCodigo(parseInt(valor[0]));
                    c.setCpf(valor[1]);
                    c.setNome(valor[2]);
                    c.setTelefone(valor[3]);
                    c.setEmail(valor[4]);
                    if (!valor[5].equals("null")){
                        c.setDataAniversario(formato.parse(valor[5]));
                    }
                    else {
                        c.setDataAniversario(null);
                    }
                    clientes.add(c);
                }
                linha = entrada.readLine();
            } while (linha != null);
            return clientes;
        } catch (FileNotFoundException e) {
            //log de erro
        } catch (IOException ex2) {
            // log
        } catch (ParseException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getProxCodigo() {
        int cod = 1;
        try {
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            while (linha != null) {
                cod++;
                linha = entrada.readLine();
            }
        } catch (FileNotFoundException ex) {
            try {
                FileWriter arq = new FileWriter("Cliente.csv");
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex2) {
            // log
        }
        return cod;
    }
    
    /* Métodos Construtores, Setters & Getters */
    public Cliente(String nome) {
        this.setNome(nome);
    }

    public Cliente() {
    }
    
    private void setCodigo(Integer codigo) {
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
