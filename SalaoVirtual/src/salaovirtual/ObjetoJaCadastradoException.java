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
public class ObjetoJaCadastradoException extends MinhaException {
    
    public ObjetoJaCadastradoException() {
        super("O Objeto com a Key indicada já está cadastrado no sistema.");
    }
}
