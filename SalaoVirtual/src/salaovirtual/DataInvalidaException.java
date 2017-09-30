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
public class DataInvalidaException extends MinhaException {

    public DataInvalidaException() {
        super("A data inserida não é válida para o agendamento do serviços.");
    }
}
