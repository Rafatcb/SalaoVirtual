/*
 * Exception Checked referente à um tipo de cartão inválido
 */
package salaovirtual;

/*
 * Exception Checked referente à um tipo de cartão inválido
 *
 * @author Rafael Tavares
 */
public class TipoDeCartaoInvalidoException extends MinhaException {

    /**
     * Construtor que envia como mensagem "O tipo de cartão é inválido. Indique um valor entre 'Crédito' ou 'Débito'."
     */
    public TipoDeCartaoInvalidoException() {
        super("O tipo de cartão é inválido. Indique um valor entre 'Crédito' ou 'Débito'.");
    }
    
}
