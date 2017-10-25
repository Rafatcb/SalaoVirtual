/*
 * Interface referente à adição de produtos e serviços
 */
package salaovirtual.interfaces;

import salaovirtual.Produto;
import salaovirtual.Servico;

/**
 * Interface referente à adição de produtos e serviços
 * 
 * @author Fábio Augusto
 */
public interface Adicionar {
    public void addProduto(Produto p, int quantidade);
    public void addServico(Servico s, int quantidade);
}
