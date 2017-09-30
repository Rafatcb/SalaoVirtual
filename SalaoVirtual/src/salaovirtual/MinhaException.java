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
public class MinhaException extends Exception{
private String msg;
    public MinhaException(String msg){
      super(msg);
      this.msg = msg;
    }
    public String getMessage(){
      return msg;
    }
}
