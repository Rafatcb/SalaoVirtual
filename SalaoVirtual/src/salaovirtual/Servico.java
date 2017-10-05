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
public class Servico {
    
    private final int codigo;
    private final String nome;
    /* Colocar valor monetário aqui */
    private Date data;
    private String estado;
    private Funcionario funcionario;
    private Cliente cliente;
    // Aqui precisará ter uma lista de produtos que foram utilizados no serviço

    public void agendarServico(Cliente cli, Funcionario fun, Date data) throws DataInvalidaException {
        Date agora = new Date();
        if (data.after(agora)) {   // Se for depois de agora, então é um agendamento válido
            this.setData(data);
            try {
                ConjuntoServicos.inserirServico(this);
            } catch (ObjetoJaCadastradoException ex) {
                // Aqui foi erro do programador, pois o código será calculado internamente no programa
                // Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            throw new DataInvalidaException();
        }
    }
    
    public void efetuarServico() { // Precisa do valor monetário
        
    }
    
    public void cancelarServico() {
        try {
            this.setEstado("Cancelado");
        }
        catch (EstadoServicoInvalidoException e) {
            // Mandar mensagem de "O serviço não pôde ser cancelado, favor contatar o desenvolvedor do sistema"
        }
    }
    /* Métodos Construtores, Getters & Setters */
    public Servico(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
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

    private void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
