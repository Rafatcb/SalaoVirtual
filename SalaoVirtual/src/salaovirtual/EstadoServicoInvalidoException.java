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
public class EstadoServicoInvalidoException extends MinhaException {

    public EstadoServicoInvalidoException() {
        super("Estado inválido. O estado do serviço deve ser 'Agendado', 'Realizado' ou 'Cancelado'.");
    }
}
