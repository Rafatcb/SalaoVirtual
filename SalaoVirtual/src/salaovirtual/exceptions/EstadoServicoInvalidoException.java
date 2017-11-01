/*
 * Exception Checked referente à um estado inválido de um serviço
 */
package salaovirtual.exceptions;

/*
 * Exception Checked referente à um estado inválido de um serviço
 *
 * @author Rafael Tavares
 */
public class EstadoServicoInvalidoException extends MinhaCheckedException {

    /**
     * Construtor que envia como mensagem "Estado inválido. O estado do serviço deve ser 'Agendado', 'Realizado' ou 'Cancelado'."
     */
    public EstadoServicoInvalidoException() {
        super("Estado inválido. O estado do serviço deve ser 'Agendado', 'Realizado' ou 'Cancelado'.");
    }
}
