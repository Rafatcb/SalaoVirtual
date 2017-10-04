/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salaovirtual;

import java.util.HashMap;

/**
 *
 * @author rafae
 */
public class CadastroFuncionario {
    private HashMap<String, Funcionario> funcionarios;
    
    public CadastroFuncionario() {
        funcionarios = new HashMap<>();
    }
    
    public void inserirFuncionario(Funcionario f) {
        funcionarios.put(f.getLogin(), f);
    }
}
