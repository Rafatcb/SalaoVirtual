/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

/**
 *
 * @author rafae
 */
public class FuncionarioInvalidoException extends MinhaException {

    public FuncionarioInvalidoException() {
        super("Login e/ou senha incorretos.");
    }
}
