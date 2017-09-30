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
public class TipoDeCartaoInvalidoException extends MinhaException {

    public TipoDeCartaoInvalidoException() {
        super("O tipo de cartão é inválido. Indique um valor entre Crédito ou Débito.");
    }
    
}
