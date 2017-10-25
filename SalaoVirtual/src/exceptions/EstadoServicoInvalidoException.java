/*
 * Exception Checked referente à um estado inválido de um serviço
 */
package exceptions;

/*
 * Exception Checked referente à um estado inválido de um serviço
 *
 * @author Rafael Tavares
 */
public class EstadoServicoInvalidoException extends MinhaException {

    /**
     * Construtor que envia como mensagem "Estado inválido. O estado do serviço deve ser 'Agendado', 'Realizado' ou 'Cancelado'."
     */
    public EstadoServicoInvalidoException() {
        super("Estado inválido. O estado do serviço deve ser 'Agendado', 'Realizado' ou 'Cancelado'.");
    }
}
