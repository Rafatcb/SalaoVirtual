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
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author r176257
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
    
    @Override
    public String toString() {
        return this.login + ";" + this.senha + ";" + this.cpf + ";" + this.nome + ";" + this.telefone + 
                ";" + this.rua + ";" + this.numero + ";" + this.complemento + ";" + this.cidade + ";" + this.estado;
    }
    
    public void gravarFuncionario() throws ObjetoJaCadastradoException, ChaveNulaException { // PRECISA VERIFICAR SE ESTE LOGIN/CPF JÁ ESTÁ CADASTRADO
        /* O funcionário deve possuir um login */
        if (this.getLogin() == null) {
            throw new ChaveNulaException();
        }
        
        try {   
            /* Verifica se o arquivo existe */
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            entrada.close();
            arq.close();    
            
            /* Não pode existir um funcionário com este login cadastrado */
            try {
                Funcionario ftemp = new Funcionario();
                ftemp = ftemp.encontrarFuncionarioLogin(this.getLogin());
                throw new ObjetoJaCadastradoException();
            } catch (NullPointerException e) {
                /* O funcionário pode ter CPF nulo, mas não pode existir um funcionário com o mesmo cpf cadastrado */
                if (this.getCpf() != null) {
                    try {
                        Funcionario ftemp2 = new Funcionario();
                        ftemp2 = ftemp2.encontrarFuncionarioCpf(this.getCpf());
                        throw new ObjetoJaCadastradoException();
                    } catch (NullPointerException ex) {
                    }
                }
            }
            
        } catch (FileNotFoundException ex) {
            /* Se o arquivo não existe, eu crio ele */
            try {
                FileWriter arq = new FileWriter("Funcionario.csv");
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            FileWriter arq = new FileWriter("Funcionario.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            saida.write(this.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    public Funcionario encontrarFuncionarioLogin(String login) {
        try {
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            Funcionario f = new Funcionario();
            do {
                linha = entrada.readLine();
                String[] valor = linha.split(";");
                if (valor[0].equals(login)) {
                    f.setLogin(valor[0]);
                    f.setSenha(valor[1]);
                    f.setCpf(valor[2]);
                    f.setNome(valor[3]);
                    f.setTelefone(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setComplemento(valor[7]);
                    f.setCidade(valor[8]);
                    f.setEstado(valor[9]);
                    
                    entrada.close();
                    arq.close();
                    return f;
                }
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            return null;
        }catch (IOException ex2) {
            return null;
        }
        return null;
    }
    
    public List<Funcionario> encontrarFuncionarioNome(String nome) {
        try {
            List<Funcionario> funcionarios = new ArrayList();
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                Funcionario f = new Funcionario();
                String[] valor = linha.split(";");
                if (valor[3].equals(nome)) {
                    f.setLogin(valor[0]);
                    f.setSenha(valor[1]);
                    f.setCpf(valor[2]);
                    f.setNome(valor[3]);
                    f.setTelefone(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setComplemento(valor[7]);
                    f.setCidade(valor[8]);
                    f.setEstado(valor[9]);
                    funcionarios.add(f);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return funcionarios;
        } catch (FileNotFoundException e) {
            //log de erro
        } catch (IOException ex2) {
            // log
        } 
        return null;
    }
    
    public Funcionario encontrarFuncionarioCpf(String cpf) {
        try {
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            Funcionario f = new Funcionario();
            do {
                linha = entrada.readLine();
                String[] valor = linha.split(";");
                if (valor[2].equals(cpf)) {
                    f.setLogin(valor[0]);
                    f.setSenha(valor[1]);
                    f.setCpf(valor[2]);
                    f.setNome(valor[3]);
                    f.setTelefone(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setComplemento(valor[7]);
                    f.setCidade(valor[8]);
                    f.setEstado(valor[9]);
                    entrada.close();
                    arq.close();
                    return f;
                }
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            //
        }catch (IOException ex2) {
            // log
        }
        return null;
    }
    
    /* Métodos Construtores + Getters & Setters */
    public Funcionario(String login, String senha, String nome) {
        this.setLogin(login);
        this.setSenha(senha);
        this.setNome(nome);
    }
    
    public Funcionario() {
        
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getLogin() {
        return login;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
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
