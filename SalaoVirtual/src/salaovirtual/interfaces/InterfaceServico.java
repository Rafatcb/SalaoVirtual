/*
 * Interface contendo os métodos necessários para a implementação do Serviço
 */
package salaovirtual.interfaces;

import java.util.Date;
import salaovirtual.Cliente;
import salaovirtual.Funcionario;
import salaovirtual.exceptions.DataInvalidaException;

/**
 * Interface contendo os métodos necessários para a implementação do Serviço
 *
 * @author Rafael Tavares
 */
public interface InterfaceServico {

    /**
     * Método para o agendamento de um serviço, tornando-o "Agendado"
     * @param cliente
     * @param funcionário
     * @param data
     * @throws salaovirtual.exceptions.DataInvalidaException
     */
    public void agendarServico(Cliente cliente, Funcionario funcionário, Date data) throws DataInvalidaException;
    
    /**
     * Método para efetuar o serviço, tornando-o "Realizado"
     */
    public void efetuarServico();
    
    /**
     * Método para cancelar o serviço, tornando-o "Cancelado"
     */
    public void cancelarServico();
    
}
