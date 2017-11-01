/*
 * Classe referente à venda de produtos e serviços
 */
package salaovirtual;

import salaovirtual.exceptions.ObjetoNaoInseridoException;
import salaovirtual.exceptions.QuantidadeInvalidaException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import salaovirtual.interfaces.InterfaceVenda;

/**
 * Classe referente à venda de produtos  e serviços
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>produtos: mapa de produtos vendidos contendo como chave o código do produto e como valor o valor
 * <p>servicos: lista com o código dos serviços
 * <p>quantidadesProdutos: lista de inteiros contendo a quantidade de cada produto vendido
 * <p>pagamentoCartao: se null, foi pago em dinheiro, senão contém as informações do cartão
 * <p>pagamentoDinheiro: se null, foi pago em cartão, senão contém as informações do dinheiro
 * 
 * @author Rafael Tavares
 */
public class Venda implements InterfaceVenda {
    private int codigo;
    private Date data;
    private Cliente cliente;
    private Funcionario funcionario;
    private Cartao pagamentoCartao;
    private Dinheiro pagamentoDinheiro;
    private Map<Integer, Float> produtos; // Código do produto, valor dele
    private List<Integer> quantidadesProdutos; // Quantidade de cada produto
    private List<Integer> servicos; // Código do serviço

    /**
     * Adiciona produto à venda
     * Polimorfismo: Sobrescrita
     * @param p
     * @param quantidade
     */
    @Override
    public void addProduto(Produto p, int quantidade) {
        try {
            if (quantidade <= 0) {
                throw new QuantidadeInvalidaException();
            }
            System.out.println(p.toString());
            this.produtos.put(p.getCodigo(), p.getValor());
            this.quantidadesProdutos.add(quantidade);
        }
        catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    
    /**
     * Adiciona produto à venda com base diretamente no código dele
     * Polimorfismo: Sobrescrita
     * @param codigo
     * @param valor
     * @param quantidade
     */
    @Override
    public void addProduto(int codigo, float valor, int quantidade) {
        try {
            if (quantidade <= 0) {
                throw new QuantidadeInvalidaException();
            }
            this.produtos.put(codigo, valor);
            this.quantidadesProdutos.add(quantidade);
        } catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    
    /**
     * Adiciona serviço à venda
     * Polimorfismo: Sobrescrita
     * @param s
     */
    @Override
    public void addServico(Servico s) {
        try {
            this.servicos.add(s.getCodigo());
        } catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    
    /**
     * Adiciona produto à venda com base diretamente no código dele
     * Polimorfismo: Sobrescrita
     * @param codigo
     */
    @Override
    public void addServico(int codigo) {
        try {
            this.servicos.add(codigo);
        } catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    
    /**
     * Método para facilitar a escrita do objeto em um arquivo CSV
     * Polimorfismo: Sobrescrita
     * @return Atributos do objeto separados por ;
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int cod, codCli;
        String login;
        if (this.pagamentoCartao == null) {
            cod = this.pagamentoDinheiro.getCodigo();
        }
        else {
            cod = this.pagamentoCartao.getCodigo();
        }
        if (this.cliente == null) {
            codCli = 0;
        }
        else {
            codCli = cliente.getCodigo();
        }
        if (this.funcionario == null) {
            login = "";
        }
        else {
            login = funcionario.getLogin();
        }
        if (this.data == null) {
            return this.codigo + ";" + this.data + ";" + codCli + ";" +login + ";" + cod; 
        }
        else {
            return this.codigo + ";" + formato.format(this.data) + ";" + codCli + ";" + login + ";" + cod;  
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
        this.servicos = new ArrayList<>();
        this.quantidadesProdutos = new ArrayList();
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.setData();
        this.pagamentoCartao = null;
        this.pagamentoDinheiro = null;
    }

    /**
     * Método construtor para facilitar a criação de um objeto que será utilizado para consulta ao invés
     * de cadastro
     */    
    public Venda(){
        this.produtos = new HashMap<>();
        this.servicos = new ArrayList<>();
        this.quantidadesProdutos = new ArrayList();
        this.pagamentoCartao = null;
        this.pagamentoDinheiro = null;
    }
    
    /**
     * Define o mapa de produtos vendidos com Código do produto, Quantidade
     * @param produtos
     */
    public void setProdutos(Map<Integer, Float> produtos) {
        this.produtos = produtos;
    }
    
    /**
     * Define a lista de serviços vendidos com Código do serviço
     * @param servicos
     */
    public void setServicos(List<Integer> servicos) {
        this.servicos = servicos;
    }
    
    /**
     * Retorna o mapa de produtos vendidos com Código do produto, Quantidade
     * @return Mapa de Produto
     */
    public Map<Integer, Float> getProdutos() {
        return produtos;
    }
    
    /**
     * Retorna a lista de serviços vendidos com código
     * @return Mapa de Serviço
     */
    public List<Integer> getServicos() {
        return servicos;
    }

    /**
     * Retorna uma lista da quantidade de produtos comprados
     * @return Lista com quantidade de produtos comprados
     */
    public List<Integer> getQuantidadesProdutos() {
        return quantidadesProdutos;
    }

    /**
     * Define a lista com quantidade de produtos comprados
     * @param quantidadesProdutos 
     */
    public void setQuantidadesProdutos(List<Integer> quantidadesProdutos) {
        this.quantidadesProdutos = quantidadesProdutos;
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
     * Sobrecarga
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
     * Sobrecarga
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
     * Retorna a forma de pagamento da venda  - dinheiro
     * @return Forma de Pagamento em dinheiro
     */
    public Dinheiro getFormaPagamentoDinheiro() {
        return pagamentoDinheiro;
    }

    /**
     * Retorna a forma de pagamento da venda - cartao
     * @return Forma de Pagamento em cartao
     */
    public Cartao getFormaPagamentoCartao() {
        return pagamentoCartao;
    }

    /**
     * Define a forma de pagamento da venda
     * Sobrecarga
     * @param d - dinheiro 
     */
    public void setFormaPagamento(Dinheiro d) {
        this.pagamentoDinheiro = d;
    }

    /**
     * Define a forma de pagamento da venda
     * Sobrecarga
     * @param c - cartão 
     */
    public void setFormaPagamento(Cartao c) {
        this.pagamentoCartao = c;
    }
    
    
}
