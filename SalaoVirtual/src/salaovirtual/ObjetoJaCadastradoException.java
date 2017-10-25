/*
 * Exception Checked referente à um objeto já cadastrado
 */
package salaovirtual;

/*
 * Exception Checked referente à um objeto já cadastrado
 *
 * @author Rafael Tavares
 */
public class ObjetoJaCadastradoException extends MinhaException {
    
    /**
     * Construtor que envia como mensagem "O Objeto com a Key indicada já está cadastrado no sistema."
     */
    public ObjetoJaCadastradoException() {
        super("O Objeto com a Key indicada já está cadastrado no sistema.");
    }
}
