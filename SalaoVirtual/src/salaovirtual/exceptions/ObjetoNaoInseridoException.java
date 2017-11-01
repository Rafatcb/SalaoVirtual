/*
 * Exception Unchecked referente à um objeto que não foi inserido na coleção
 */
package salaovirtual.exceptions;

/*
 * Exception Unchecked referente à um objeto que não foi inserido na coleção
 *
 * @author Rafael Tavares
 */
public class ObjetoNaoInseridoException  extends MinhaUncheckedException {
    /**
     * Construtor que envia como mensagem "O não foi inserido na coleção."
     */
    public ObjetoNaoInseridoException() {
        super("O não foi inserido na coleção.");
    }
}
