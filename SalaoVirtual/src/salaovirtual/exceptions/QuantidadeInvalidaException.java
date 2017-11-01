/*
 * Exception Unchecked referente à uma quantidade inválida ou valor inválido
 */
package salaovirtual.exceptions;

/**
 * Exception Unchecked referente à uma quantidade inválida ou valor inválido
 *
 * @author Rafael Tavares
 */
public class QuantidadeInvalidaException extends MinhaUncheckedException {
    /**
     * Construtor que envia como mensagem "A quantidade ou o valor informado é inválido."
     */
    public QuantidadeInvalidaException() {
        super("A quantidade ou o valor informado é inválido.");
    }

}
