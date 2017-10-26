/*
 * Exception Checked referente à uma combinação de login e senha inválidos
 */
package exceptions;

/*
 * Exception Checked referente à uma combinação de login e senha inválidos
 *
 * @author Rafael Tavares
 */
public class LoginOuSenhaInvalidosException extends MinhaCheckedException {

    /**
     * Construtor que envia como mensagem "Login e/ou senha inválidos."
     */
    public LoginOuSenhaInvalidosException() {
        super("Login e/ou senha inválidos.");
    }
}
