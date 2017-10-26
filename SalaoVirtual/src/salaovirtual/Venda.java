/*
 * Classe referente à venda de produtos e serviços
 */
package salaovirtual;

import exceptions.ObjetoNaoInseridoException;
import exceptions.QuantidadeInvalidaException;
import salaovirtual.interfaces.Adicionar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe referente à venda de produtos  e serviços
 * 
 * <p>Atributos que merecem destaque para explicação:
 * <p>produtos: mapa de produtos vendidos contendo como chave o código do produto e como valor o valor
 * <p>servicos: mapa de serviços vendidos contendo como chave o código do serviço e como valor a quantidade
 * <p>quantidadesProdutos: lista de inteiros contendo a quantidade de cada produto vendido
 * 
 * @author Rafael Tavares
 */
public class Venda implements Adicionar{
    private int codigo;
    private Date data;
    private Cliente cliente;
    private Funcionario funcionario;
    private FormaDePagamento formaPagamento;
    private Map<Integer, Float> produtos; // Código do produto, valor dele
    private List<Integer> quantidadesProdutos; // Quantidade de cada produto
    private Map<Integer, Integer> servicos; // Código do serviço, Quantidade dele

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
            this.produtos.put(p.getCodigo(), p.getValor());
            this.quantidadesProdutos.add(quantidade);
        }
        catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    
    /**
     * Adiciona produto à venda com base diretamente no código dele
     * Polimorfismo: Sobrecarga
     * @param codigo
     * @param valor
     * @param quantidade
     */
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
     * @param quantidade
     */
    @Override
    public void addServico(Servico s, int quantidade) {
        try {
            if (quantidade <= 0) {
                throw new QuantidadeInvalidaException();
            }
            this.servicos.put(s.getCodigo(), quantidade);
        } catch (NullPointerException ex) {
            throw new ObjetoNaoInseridoException();
        }
    }
    
    /**
     * Adiciona produto à venda com base diretamente no código dele
     * Polimorfismo: Sobrecarga
     * @param codigo
     * @param quantidade
     */
    public void addServico(int codigo, int quantidade) {
        try {
            if (quantidade <= 0) {
                throw new QuantidadeInvalidaException();
            }
            this.servicos.put(codigo, quantidade);
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
     * Define o mapa de produtos vendidos com (Código do produto, Quantidade)
     * @param produtos
     */
    public void setProdutos(Map<Integer, Float> produtos) {
        this.produtos = produtos;
    }
    
    /**
     * Define o mapa de serviços vendidos com (Código do serviço, Quantidade)
     * @param servicos
     */
    public void setServicos(Map<Integer, Integer> servicos) {
        this.servicos = servicos;
    }
    
    /**
     * Retorna o mapa de produtos vendidos com (Código do produto, Quantidade)
     * @return Mapa de Produto
     */
    public Map<Integer, Float> getProdutos() {
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
