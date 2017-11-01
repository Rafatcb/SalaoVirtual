/*
 * Interface contendo os métodos necessários para o funcionário
 */
package salaovirtual.interfaces;

/**
 * Interface contendo os métodos necessários para o funcionário
 *
 * @author Rafael Tavares
 */
public interface InterfaceFuncionario {

    /**
     * Método para validar o login e senha do usuário
     * @param senha
     * @return True se compatível, false se inválido
     */
    public boolean validarLoginSenha(String senha);
}
