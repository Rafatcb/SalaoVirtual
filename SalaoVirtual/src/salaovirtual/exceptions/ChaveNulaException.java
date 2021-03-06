/*
 * Exception Checked referente à nulidade da chave de um objeto
 */
package salaovirtual.exceptions;

/*
 * Exception Checked referente à nulidade da chave de um objeto
 *
 * @author Rafael Tavares
 */
public class ChaveNulaException extends MinhaCheckedException {
      
    /**
     * Construtor que envia como mensagem "A chave deste Objeto não pode ser nula."
     */
    public ChaveNulaException() {
        super("A chave deste Objeto não pode ser nula.");
    }
}
