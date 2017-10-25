/*
 * Exception Checked responsável pelo envio das mensagens das exceções criadas neste projeto
 */
package exceptions;

/*
 * Exception Checked responsável pelo envio das mensagens das exceções criadas neste projeto
 * 
 * @author Rafael Tavares
 */
public class MinhaException extends Exception{
private String msg;

    /**
     * Construtor que envia a mensagem recebida pela exceção que o herda
     */
    public MinhaException(String msg){
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
