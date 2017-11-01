/*
 * Exception Unchecked responsável pelo envio das mensagens das exceções criadas neste projeto
 */
package salaovirtual.exceptions;

/*
 * Exception Unchecked responsável pelo envio das mensagens das exceções criadas neste projeto
 * 
 * @author Rafael Tavares
 */
public class MinhaUncheckedException extends RuntimeException {

    private String msg;
    
    /**
     * Construtor que envia a mensagem recebida pela exceção que o herda
     */
    public MinhaUncheckedException(String msg){
        super(msg);
        this.msg = msg;
    }
    
    /**
     * Retorna a mensagem da exceção
     * @return Mensagem
     */
    public String getMessage(){
        return msg;
    }
}
