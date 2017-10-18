/*
 * Classe referente ao serviço
 */
package salaovirtual;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Classe referente ao fornecedor
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>estado: É o estado do serviço, podendo ser apenas "Agendado", "Cancelado" ou "Realizado"
 * 
 * @author Rafael Tavares
 */
public class Servico {
    private int codigo;
    private String nome;
    private float valor;
    private Date data;
    private String estado;
    private Funcionario funcionario;
    private Cliente cliente;
    
    /**
     * Agenda o serviço que chamou este método para (respectivamente aos parâmetros) um cliente e 
     * um funcionário numa data especificada. 
     * A data deve ser posteiror ao momento atual, caso seja anterior ocorrerá a DataInvalidaException.
     * @param cli
     * @param fun
     * @param data
     * @throws DataInvalidaException 
     */
    public void agendarServico(Cliente cli, Funcionario fun, Date data) throws DataInvalidaException {
        Date agora = new Date();
        if (data.after(agora)) {   // Se for depois de agora, então é um agendamento válido
            this.setData(data);
            this.setFuncionario(fun);
            this.setCliente(cli);
            try {
                this.setEstado("Agendado");
            } catch (EstadoServicoInvalidoException ex) {
                //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            throw new DataInvalidaException();
        }
    }
    
    /**
     * Efetua o serviço que chamou este método apenas se seu estado for Agendado ou nulo (o que significa que
     * o serviço foi efetuado sem um agendamento prévio)
     */
    public void efetuarServico() {
        try {
            if (this.getEstado().equals("Agendado")){
                try {
                    this.setEstado("Realizado");
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
            }
            catch (EstadoServicoInvalidoException ex) {
                // Mandar mensagem de "O serviço não pôde ser realizado, favor contatar o desenvolvedor do sistema"
            }
        }
    }
    
    /**
     * Modifica o estado do serviço que chamou este método para Cancelado.
     */
    public void cancelarServico() {
        try {
            this.setEstado("Cancelado");
            // alterarServico();
        }
        catch (EstadoServicoInvalidoException e) {
            // Mandar mensagem de "O serviço não pôde ser cancelado, favor contatar o desenvolvedor do sistema"
        }
    }
    
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return this.codigo + ";" + this.nome + ";" + this.valor + ";" + formato.format(this.data) + ";" + 
                    this.estado + ";" + this.funcionario.getLogin() + ";" + this.cliente.getCodigo();
    }
    
    /* Métodos Construtores, Getters & Setters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param nome 
     */
    public Servico(String nome) {
        this.nome = nome;
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */
    public Servico() {
        
    }

    /**
     * Define o código do serviço
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Define o nome do serviço
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o valor do serviço
     * @return Valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * Define o valor do serviço
     * @param valor 
     */
    public void setValor(float valor) {
        this.valor = valor;
    }
    
    /**
     * Retorna o nome do serviço
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o código do serviço
     * @return Código
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna a data do serviço
     * @return Data
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data do serviço
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Retorna o estado atual do serviço
     * @return Estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado atual do serviço. Gera EstadoServicoInvalidoException caso não seja um estado válido
     * @param estado
     * @throws EstadoServicoInvalidoException 
     */
    public void setEstado(String estado) throws EstadoServicoInvalidoException {
        if ((estado.equals("Agendado")) || (estado.equals("Realizado")) || (estado.equals("Cancelado")))
            this.estado = estado;
        else
            throw new EstadoServicoInvalidoException();
    }

    /**
     * Retorna o funcionário responsável por este serviço
     * @return Funcionário
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário responsável por este serviço
     * @param funcionario 
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Retorna o cliente que recebeu este serviço
     * @return 
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente que recebeu este serviço
     * @param cliente 
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
