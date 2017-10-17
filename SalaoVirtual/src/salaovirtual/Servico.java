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
public class Servico {
    private int codigo;
    private String nome;
    private float valor;
    private Date data;
    private String estado;
    private Funcionario funcionario;
    private Cliente cliente;
    
    // Criar método para listar todos os serviços de um mês
    // Criar método para listar todos os serviços de um ano
    // Criar método para listar todos os serviços que um cliente fez
    // Criar método para listar todos os serviços que um funcionário fez
    // A data está armazenando apena dd/MM/yyyy , precisa fazer armazenar horário também!
    

    public void agendarServico(Cliente cli, Funcionario fun, Date data) throws DataInvalidaException {
        Date agora = new Date();
        if (data.after(agora)) {   // Se for depois de agora, então é um agendamento válido
            this.setData(data);
            this.setFuncionario(fun);
            this.setCliente(cli);
            try {
                this.setEstado("Agendado");
            } catch (EstadoServicoInvalidoException ex) {
                Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            throw new DataInvalidaException();
        }
    }
    
    public void efetuarServico() {
        try {
            if (this.getEstado().equals("Agendado")){
                try {
                    this.setEstado("Realizado");
                    gravarServico();
                }
                catch (EstadoServicoInvalidoException e) {
                    // Mandar mensagem de "O serviço não pôde ser realizado, favor contatar o desenvolvedor do sistema"
                }
            }
            else {
                // Mandar mensagem de "O serviço não pôde ser realizado pois seu estado atual é this.getEstado()"
            }
        } catch (NullPointerException e) {
            try {
                Date data = new Date();
                this.setEstado("Realizado");
                this.setData(data);
                gravarServico();
            }
            catch (EstadoServicoInvalidoException ex) {
                // Mandar mensagem de "O serviço não pôde ser realizado, favor contatar o desenvolvedor do sistema"
            }
        }
    }
    
    public void cancelarServico() {
        try {
            this.setEstado("Cancelado");
            // alterarServico();
        }
        catch (EstadoServicoInvalidoException e) {
            // Mandar mensagem de "O serviço não pôde ser cancelado, favor contatar o desenvolvedor do sistema"
        }
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return this.codigo + ";" + this.nome + ";" + this.valor + ";" + formato.format(this.data) + ";" + 
                    this.estado + ";" + this.funcionario.getLogin() + ";" + this.cliente.getCodigo();
    }
    
    private void gravarServico() {
        try {
            FileWriter arq = new FileWriter("Servico.csv", true);
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
    
    public Servico encontrarServico(int codigo) {
        try {
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            Servico s = new Servico();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                linha = entrada.readLine();
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Funcionario f = new Funcionario();
                    s.setFuncionario(f.encontrarFuncionarioLogin(valor[5]));
                    
                    Cliente c = new Cliente();
                    s.setCliente(c.encontrarCliente(parseInt(valor[6])));
                    
                    entrada.close();
                    arq.close();
                    return s;
                }
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            return null;
        }catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Servico> encontrarServicoNome(String nome) {
        try {
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                Servico s = new Servico();
                String[] valor = linha.split(";");
                if (valor[1].equals(nome)) {
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Funcionario f = new Funcionario();
                    s.setFuncionario(f.encontrarFuncionarioLogin(valor[5]));
                    
                    Cliente c = new Cliente();
                    s.setCliente(c.encontrarCliente(parseInt(valor[6])));
                    servicos.add(s);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Servico> encontrarServicoEstado(String estado) {
        try {
            Servico stemp = new Servico();
            stemp.setEstado(estado);
        } catch (EstadoServicoInvalidoException ex) {
            return null;
        }
        
        try {
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                Servico s = new Servico();
                String[] valor = linha.split(";");
                if (valor[4].equals(estado)) {
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Funcionario f = new Funcionario();
                    s.setFuncionario(f.encontrarFuncionarioLogin(valor[5]));
                    
                    Cliente c = new Cliente();
                    s.setCliente(c.encontrarCliente(parseInt(valor[6])));
                    servicos.add(s);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getProxCodigo() {
        int cod = 1;
        try {
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            while (linha != null) {
                cod++;
                linha = entrada.readLine();
            }
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            try {
                FileWriter arq = new FileWriter("Servico.csv");
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
    
    /* Métodos Construtores, Getters & Setters */
    public Servico(String nome) {
        this.nome = nome;
    }

    public Servico() {
        
    }

    private void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }

    private void setData(Date data) {
        this.data = data;
    }

    public String getEstado() {
        return estado;
    }

    private void setEstado(String estado) throws EstadoServicoInvalidoException {
        if ((estado.equals("Agendado")) || (estado.equals("Realizado")) || (estado.equals("Cancelado")))
            this.estado = estado;
        else
            throw new EstadoServicoInvalidoException();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
