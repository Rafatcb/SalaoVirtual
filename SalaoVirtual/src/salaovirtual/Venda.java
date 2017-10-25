/*
 * Classe referente à venda de produtos e serviços
 */
package salaovirtual;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe referente à venda de produtos  e serviços
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>produtos: mapa de produtos vendidos contendo como chave o código do produto e como valor a quantidade
 * <p>servicos: mapa de serviços vendidos contendo como chave o código do serviço e como valor a quantidade
 * 
 * @author Rafael Tavares
 */
public class Venda implements Adicionar{
    private int codigo;
    private Date data;
    private Cliente cliente;
    private Funcionario funcionario;
    private FormaDePagamento formaPagamento;
    private Map<Integer, Integer> produtos; // Código do produto, Quantidade dele
    private Map<Integer, Integer> servicos; // Código do serviço, Quantidade dele

    /**
     * Adiciona produto à venda
     * Polimorfismo: Sobrescrita
     * @param p
     * @param quantidade
     */
    @Override
    public void addProduto(Produto p, int quantidade) {
        // Falta verificar se é um produto válido baseado no arquivo de produtos
        this.produtos.put(p.getCodigo(), quantidade);
    }
    
    /**
     * Adiciona produto à venda com base diretamente no código dele
     * Polimorfismo: Sobrecarga
     * @param codigo
     * @param quantidade
     */
    public void addProduto(int codigo, int quantidade) {
        // Falta verificar se é um produto válido baseado no arquivo de produtos
        this.produtos.put(codigo, quantidade);
    }
    
    /**
     * Adiciona serviço à venda
     * Polimorfismo: Sobrescrita
     * @param s
     * @param quantidade
     */
    @Override
    public void addServico(Servico s, int quantidade) {
        // Falta verificar se é um servico válido baseado no arquivo de servicos
        this.servicos.put(s.getCodigo(), quantidade);
    }
    
    /**
     * Adiciona produto à venda com base diretamente no código dele
     * Polimorfismo: Sobrecarga
     * @param codigo
     * @param quantidade
     */
    public void addServico(int codigo, int quantidade) {
        // Falta verificar se é um servico válido baseado no arquivo de servicos
        this.servicos.put(codigo, quantidade);
    }
    
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if (this.data == null) {
            return this.codigo + ";" + this.data + ";" + this.cliente.getCodigo() + ";" + this.funcionario.getLogin() + ";" +
                    this.formaPagamento.getCodigo(); 
        }
        else {
            return this.codigo + ";" + formato.format(this.data) + ";" + this.cliente.getCodigo() + ";" + this.funcionario.getLogin() + ";" +
                    this.formaPagamento.getCodigo();  
        }
    }
    
    /* Métodos Construtores, Getters & Setters */
    /**
     * Método construtor para facilitar a criação de um objeto que será cadastrado no sistema
     * @param cliente
     * @param funcionario
     */
    public Venda(Cliente cliente, Funcionario funcionario) {
        this.produtos = new HashMap<>();
        this.servicos = new HashMap<>();
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.setData();
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */    
    public Venda(){
        this.produtos = new HashMap<>();
        this.servicos = new HashMap<>();
    }
    
    /**
     * Retorna o mapa de produtos vendidos com (Código do produto, Quantidade)
     * @return Mapa de Produto
     */
    public Map<Integer, Integer> getProdutos() {
        return produtos;
    }
    
    /**
     * Retorna o mapa de serviços vendidos com (Código do serviço, Quantidade)
     * @return Mapa de Serviço
     */
    public Map<Integer, Integer> getServicos() {
        return servicos;
    }

    /**
     * Define o código da venda
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Define a data da venda
     * Polimorfismo: Sobrecarga
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Retorna o código da venda
     * @return Código
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna a data da venda
     * @return Data
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data da venda caso tenha sido no dia em que foi criada a classe
     * Polimorfismo: Sobrecarga
     */
    public void setData() {
        this.data = new Date();
    }

    /**
     * Retorna o cliente da venda
     * @return Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente da venda
     * @param cliente 
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna o funcionário da venda
     * @return Funcionário
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário da venda
     * @param funcionario 
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Retorna a forma de pagamento da venda 
     * @return Forma de Pagamento
     */
    public FormaDePagamento getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Define a forma de pagamento da venda
     * @param formaPagamento 
     */
    public void setFormaPagamento(FormaDePagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
}
