/*
 * Exception Checked referente à uma data inválida para o agendamento de serviços
 */
package salaovirtual;

/**
 * Exception Checked referente à uma data inválida para o agendamento de serviços
 *
 * @author Rafael Tavares
 */
public class DataInvalidaException extends MinhaException {

    /**
     * Construtor que envia como mensagem "A data inserida não é válida para o agendamento do serviços."
     */
    public DataInvalidaException() {
        super("A data inserida não é válida para o agendamento do serviços.");
    }
}
