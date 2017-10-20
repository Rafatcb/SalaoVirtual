/*
 * Interface referente à adição de produtos e serviços
 */
package salaovirtual;

/**
 * Interface referente à adição de produtos e serviços
 * 
 * @author Fábio Augusto
 */
public interface Adicionar {
    public void addProduto(Produto p, int quantidade);
    public void addServico(Servico s, int quantidade);
}
