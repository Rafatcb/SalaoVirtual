/*
 * Classe referente à consulta de objetos em arquivo
 */
package salaovirtual.acesso;

import salaovirtual.exceptions.TipoDeCartaoInvalidoException;
import salaovirtual.exceptions.ChaveNulaException;
import salaovirtual.exceptions.EstadoServicoInvalidoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import salaovirtual.Cartao;
import salaovirtual.Cliente;
import salaovirtual.Compra;
import salaovirtual.Dinheiro;
import salaovirtual.FormaDePagamento;
import salaovirtual.Fornecedor;
import salaovirtual.Funcionario;
import salaovirtual.Produto;
import salaovirtual.Servico;
import salaovirtual.Venda;

/**
 * Classe referente à consulta de objetos em arquivo
 * 
 * @author Rafael Tavares
 */
public class Consulta {  
    /**
     * Retorna o código do próximo objeto a ser inserido. O Objeto é identificado através do nome do arquivo
     * CSV passado como parâmetro
     * @param arquivo
     * @return Código
     */
    public int getProxCodigo(String arquivo) {
        int cod = 1;
        try {
            FileReader arq = new FileReader(arquivo);
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            while (linha != null) {
                cod++;
                linha = entrada.readLine();
            }
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            try {
                FileWriter arq = new FileWriter(arquivo);
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex2) {
            // log
        }
        return cod;
    }
    
    /**
     * Retorna o fornecedor identificado pelo código passado por parâmetro
     * @param codigo
     * @return Fornecedor
     */
    public Fornecedor encontrarFornecedor(int codigo) {
        try {
            FileReader arq = new FileReader("Fornecedor.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Fornecedor f = new Fornecedor();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    f.setCodigo(parseInt(valor[0]));
                    f.setCnpj(valor[1]);
                    f.setNome(valor[2]);
                    f.setTelefone(valor[3]);
                    f.setEmail(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setCidade(valor[7]);
                    f.setEstado(valor[8]);
                    try {
                        f.setComplemento(valor[9]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        f.setComplemento("");
                    }
                    arq.close();
                    entrada.close();
                    return f;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            return null;
        }catch (IOException ex2) {
            return null;
        }
        return null;
    }
    
    /**
     * Retorna uma lista dos fornecedores que possuem o nome passado como parâmetro
     * @param nome
     * @return Lista de Fornecedor
     */
    public List<Fornecedor> encontrarFornecedorNome(String nome) {
        try {
            List<Fornecedor> fornecedores = new ArrayList();
            FileReader arq = new FileReader("Fornecedor.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                Fornecedor f = new Fornecedor();
                String[] valor = linha.split(";");
                if (valor[2].contains(nome)) {
                    f.setCodigo(parseInt(valor[0]));
                    f.setCnpj(valor[1]);
                    f.setNome(valor[2]);
                    f.setTelefone(valor[3]);
                    f.setEmail(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setCidade(valor[7]);
                    f.setEstado(valor[8]);
                    try {
                        f.setComplemento(valor[9]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        f.setComplemento("");
                    }
                    fornecedores.add(f);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return fornecedores;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        }
    }
    
    /**
     * Retorna o fornecedor identificado pelo CNPJ passado por parâmetro
     * @param cnpj
     * @return Fornecedor
     */
    public Fornecedor encontrarFornecedorCnpj(String cnpj) {
        try {
            Fornecedor f = new Fornecedor();
            FileReader arq = new FileReader("Fornecedor.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (valor[1].equals(cnpj)) {
                    f.setCodigo(parseInt(valor[0]));
                    f.setCnpj(valor[1]);
                    f.setNome(valor[2]);
                    f.setTelefone(valor[3]);
                    f.setEmail(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setCidade(valor[7]);
                    f.setEstado(valor[8]);
                    try {
                        f.setComplemento(valor[9]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        f.setComplemento("");
                    }
                    arq.close();
                    entrada.close();
                    return f;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return null;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        }
    }
    
    /**
     * Retorna o cliente identificado pelo código passado por parâmetro
     * @param codigo
     * @return Cliente
     */    
    public Cliente encontrarCliente(int codigo) {
        try {
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Cliente c = new Cliente();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    c.setCodigo(parseInt(valor[0]));
                    c.setCpf(valor[1]);
                    c.setNome(valor[2]);
                    c.setTelefone(valor[3]);
                    c.setEmail(valor[4]);
                    if (!valor[5].equals("null")) {
                        c.setDataAniversario(formato.parse(valor[5]));
                    }
                    else {
                        c.setDataAniversario(null);
                    }
                    entrada.close();
                    arq.close();
                    return c;
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna o cliente identificado pelo código passado por parâmetro
     * @param cpf
     * @return Cliente
     */    
    public Cliente encontrarClienteCpf(String cpf) {
        try {
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Cliente c = new Cliente();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                String[] valor = linha.split(";");
                if (valor[1].equals(cpf)) {
                    c.setCodigo(parseInt(valor[0]));
                    c.setCpf(valor[1]);
                    c.setNome(valor[2]);
                    c.setTelefone(valor[3]);
                    c.setEmail(valor[4]);
                    if (!valor[5].equals("null")) {
                        c.setDataAniversario(formato.parse(valor[5]));
                    }
                    else {
                        c.setDataAniversario(null);
                    }
                    entrada.close();
                    arq.close();
                    return c;
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna uma lista dos clientes que possuem o nome passado como parâmetro
     * @param nome
     * @return Lista de Cliente
     */
    public List<Cliente> encontrarClienteNome(String nome) {
        try {
            List<Cliente> clientes = new ArrayList();
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                String[] valor = linha.split(";");
                if (valor[2].contains(nome)) {
                    Cliente c = new Cliente();
                    c.setCodigo(parseInt(valor[0]));
                    c.setCpf(valor[1]);
                    c.setNome(valor[2]);
                    c.setTelefone(valor[3]);
                    c.setEmail(valor[4]);
                    if (!valor[5].equals("null")){
                        c.setDataAniversario(formato.parse(valor[5]));
                    }
                    else {
                        c.setDataAniversario(null);
                    }
                    clientes.add(c);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return clientes;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna o funcionário identificado pelo login passado por parâmetro
     * @param login
     * @return Funcionário
     */    
    public Funcionario encontrarFuncionarioLogin(String login) {
        try {
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Funcionario f = new Funcionario();
            do {
                String[] valor = linha.split(";");

                if (login.equals(valor[0])) {
                    f.setLogin(valor[0]);
                    f.setSenhaCriptografada(valor[1]);
                    f.setCpf(valor[2]);
                    f.setNome(valor[3]);
                    f.setTelefone(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setComplemento(valor[7]);
                    f.setCidade(valor[8]);
                    f.setEstado(valor[9]);
                    
                    entrada.close();
                    arq.close();
                    return f;
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            return null;
        }catch (IOException ex2) {
            return null;
        }
        return null;
    }
    
    /**
     * Retorna uma lista dos funcionários que possuem o nome passado como parâmetro
     * @param nome
     * @return Lista de Funcionário
     */
    public List<Funcionario> encontrarFuncionarioNome(String nome) {
        try {
            List<Funcionario> funcionarios = new ArrayList();
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (valor[3].contains(nome)) {
                    Funcionario f = new Funcionario();
                    f.setLogin(valor[0]);
                    f.setSenhaCriptografada(valor[1]);
                    f.setCpf(valor[2]);
                    f.setNome(valor[3]);
                    f.setTelefone(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setComplemento(valor[7]);
                    f.setCidade(valor[8]);
                    f.setEstado(valor[9]);
                    funcionarios.add(f);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return funcionarios;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        }
    }
    
    /**
     * Retorna o funcionário identificado pelo CPF passado por parâmetro
     * @param cpf
     * @return Funcionário
     */    
    public Funcionario encontrarFuncionarioCpf(String cpf) {
        try {
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Funcionario f = new Funcionario();
            do {
                String[] valor = linha.split(";");
                if (valor[2].equals(cpf)) {
                    f.setLogin(valor[0]);
                    f.setSenha(valor[1]);
                    f.setCpf(valor[2]);
                    f.setNome(valor[3]);
                    f.setTelefone(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setComplemento(valor[7]);
                    f.setCidade(valor[8]);
                    f.setEstado(valor[9]);
                    entrada.close();
                    arq.close();
                    return f;
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            return null;
        }catch (IOException ex2) {
            return null;
        }
        return null;
    }
    
    /**
     * Retorna o produto identificado pelo código passado por parâmetro
     * @param codigo
     * @return Produto
     */   
    public Produto encontrarProduto(int codigo) {
        try {
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Produto p = new Produto();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    p.setCodigo(parseInt(valor[0]));
                    p.setNome(valor[1]);
                    p.setMarca(valor[2]);
                    p.setUnidade(valor[3]);
                    p.setQtdUnitaria(parseFloat(valor[4]));
                    p.setValor(parseFloat(valor[5]));
                    p.setQtdEstoque(parseInt(valor[6]));
                    p.setQtdEstoqueMin(parseInt(valor[7]));
                    arq.close();
                    entrada.close();
                    return p;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            return null;
        }catch (IOException ex2) {
            return null;
        }
        return null;
    }
    
    /**
     * Retorna uma lista dos produtos que possuem o nome passado como parâmetro
     * @param nome
     * @return Lista de Produto
     */
    public List<Produto> encontrarProdutoNome(String nome) {
        try {
            List<Produto> produtos = new ArrayList();
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (valor[1].contains(nome)) {
                    Produto p = new Produto();
                    p.setCodigo(parseInt(valor[0]));
                    p.setNome(valor[1]);
                    p.setMarca(valor[2]);
                    p.setUnidade(valor[3]);
                    p.setQtdUnitaria(parseFloat(valor[4]));
                    p.setValor(parseFloat(valor[5]));
                    p.setQtdEstoque(parseInt(valor[6]));
                    p.setQtdEstoqueMin(parseInt(valor[7]));
                    produtos.add(p);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return produtos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } 
    }
    
    /**
     * Retorna uma lista dos produtos que possuem a marca passada como parâmetro
     * Sobrecarga
     * @param marca
     * @return Lista de Produto
     */
    public List<Produto> encontrarProdutoMarca(String marca) {
        try {
            List<Produto> produtos = new ArrayList();
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (valor[2].contains(marca)) {
                    Produto p = new Produto();
                    p.setCodigo(parseInt(valor[0]));
                    p.setNome(valor[1]);
                    p.setMarca(valor[2]);
                    p.setUnidade(valor[3]);
                    p.setQtdUnitaria(parseFloat(valor[4]));
                    p.setValor(parseFloat(valor[5]));
                    p.setQtdEstoque(parseInt(valor[6]));
                    p.setQtdEstoqueMin(parseInt(valor[7]));
                    produtos.add(p);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return produtos;
        } catch (FileNotFoundException e) {
            //log de erro
        } catch (IOException ex2) {
            // log
        } 
        return null;
    }
    
    /**
     * Retorna uma lista dos serviços que estão entre as datas passadas como parâmetro
     * Sobrecarga
     * @param dataInicio
     * @param dataFim
     * @return Lista de Serviço
     */
    public List<Servico> encontrarServico(Date dataInicio, Date dataFim) {
        try {
            Consulta consulta = new Consulta();
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                try{
                    if ((formato.parse(valor[3]).after(dataInicio)) && ((formato.parse(valor[3]).before(dataFim)))) {
                        Servico s = new Servico();
                        s.setCodigo(parseInt(valor[0]));
                        s.setNome(valor[1]);
                        s.setValor(parseFloat(valor[2]));
                        s.setData(formato.parse(valor[3]));
                        s.setEstado(valor[4]);

                        s.setFuncionario(consulta.encontrarFuncionarioLogin(valor[5]));

                        s.setCliente(consulta.encontrarCliente(parseInt(valor[6])));
                        servicos.add(s);
                    }
                } catch (EstadoServicoInvalidoException ex) {
                    //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    /**
     * Retorna o serviço identificado pelo código passado por parâmetro
     * @param codigo
     * @return Serviço
     */   
    public Servico encontrarServico(int codigo) {
        try {
            Consulta consulta = new Consulta();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Servico s = new Servico();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    s.setFuncionario(consulta.encontrarFuncionarioLogin(valor[5]));
                    
                    s.setCliente(consulta.encontrarCliente(parseInt(valor[6])));
                    
                    entrada.close();
                    arq.close();
                    return s;
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            return null;
        }catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    /**
     * Retorna uma lista dos serviços que possuem valor entre os passados como parâmetro
     * @param valorInicio
     * @param valorFim
     * @return Lista de Serviço
     */   
    public List<Servico> encontrarServico(float valorInicio, float valorFim) {
        try {
            Consulta consulta = new Consulta();
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                float preco = parseFloat(valor[2]);
                if ((preco >= valorInicio) && (preco <= valorFim)) {
                    Servico s = new Servico();
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(preco);
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    s.setFuncionario(consulta.encontrarFuncionarioLogin(valor[5]));
                    
                    s.setCliente(consulta.encontrarCliente(parseInt(valor[6])));
                    servicos.add(s);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    /**
     * Retorna uma lista dos serviços que foram realizados para o cliente cujo código foi passado como parâmetro
     * @param codigoCliente
     * @return Lista de Serviço
     */   
    public List<Servico> encontrarServicoCliente(int codigoCliente) {
        try {
            Consulta consulta = new Consulta();
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[6]) == codigoCliente) {
                    Servico s = new Servico();
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    s.setFuncionario(consulta.encontrarFuncionarioLogin(valor[5]));
                    
                    s.setCliente(consulta.encontrarCliente(parseInt(valor[6])));
                    servicos.add(s);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna uma lista dos serviços que possuem o nome passado como parâmetro
     * @param nome
     * @return Lista de Serviço
     */
    public List<Servico> encontrarServicoNome(String nome) {
        try {
            Consulta consulta = new Consulta();
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (valor[1].contains(nome)) {
                    Servico s = new Servico();
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    s.setFuncionario(consulta.encontrarFuncionarioLogin(valor[5]));
                    
                    s.setCliente(consulta.encontrarCliente(parseInt(valor[6])));
                    servicos.add(s);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna uma lista dos serviços realizados pelo funcionário com o login passado como parâmetro
     * @param login
     * @return Lista de Serviço
     */
    public List<Servico> encontrarServicoFuncionario(String login) {
        try {
            Consulta consulta = new Consulta();
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (valor[5].equals(login)) {
                    Servico s = new Servico();
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    s.setFuncionario(consulta.encontrarFuncionarioLogin(valor[5]));
                    
                    s.setCliente(consulta.encontrarCliente(parseInt(valor[6])));
                    servicos.add(s);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna uma lista dos serviços que estão no estado passado como parâmetro
     * @param estado
     * @return Lista de Serviço
     */
    public List<Servico> encontrarServicoEstado(String estado) {
        try {
            Servico stemp = new Servico();
            stemp.setEstado(estado);
        } catch (EstadoServicoInvalidoException ex) {
            return null;
        }
        
        try {
            Consulta consulta = new Consulta();
            List<Servico> servicos = new ArrayList();
            FileReader arq = new FileReader("Servico.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (valor[4].contains(estado)) {
                    Servico s = new Servico();
                    s.setCodigo(parseInt(valor[0]));
                    s.setNome(valor[1]);
                    s.setValor(parseFloat(valor[2]));
                    s.setData(formato.parse(valor[3]));
                    try {
                        s.setEstado(valor[4]);
                    } catch (EstadoServicoInvalidoException ex) {
                        //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    s.setFuncionario(consulta.encontrarFuncionarioLogin(valor[5]));
                    
                    s.setCliente(consulta.encontrarCliente(parseInt(valor[6])));
                    servicos.add(s);
                }
                linha = entrada.readLine();
            } while (linha != null);
            entrada.close();
            arq.close();
            return servicos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex) {
            //Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna a compra identificada pelo código passado por parâmetro
     * Sobrecarga
     * @param codigo
     * @return Compra
     */   
    public Compra encontrarCompra(int codigo) {
        try {
            FileReader arq = new FileReader("Compra.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Compra c = new Compra();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    c.setCodigo(parseInt(valor[0]));
                    c.setValorTotal(parseFloat(valor[1]));
                    c.setData(formato.parse(valor[2]));
                    
                    try {
                        c.setFornecedor(this.encontrarFornecedor(parseInt(valor[3])));
                    } catch (ChaveNulaException ex) {
                        //Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    this.encontrarCompraProdutos(c);
                    
                    arq.close();
                    entrada.close();
                    return c;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (ParseException ex3) {
            return null;
        }
        return null;
    }
    
    /**
     * Retorna a compra identificada pelo fornecedor passado por parâmetro
     * Sobrecarga
     * @param f - Fornecedor
     * @return Lista de Compras
     */   
    public List<Compra> encontrarCompra(Fornecedor f) {
        try {
            FileReader arq = new FileReader("Compra.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            List<Compra> lista = new ArrayList();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[3]) == f.getCodigo()) {
                    Compra c = new Compra();
                    c.setCodigo(parseInt(valor[0]));
                    c.setValorTotal(parseFloat(valor[1]));
                    c.setData(formato.parse(valor[2]));
                    try {
                        c.setFornecedor(this.encontrarFornecedor(parseInt(valor[3])));
                    } catch (ChaveNulaException ex) {
                        //Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.encontrarCompraProdutos(c);
                    lista.add(c);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return lista;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ParseException ex2) {
            return null;
        }
    }
    
    /**
     * Adiciona ao atributo "produtos" da compra que foi passado como parâmetro um Map dos produtos
     * cadastrados que foram comprados, junto do valor e quantidade, com base no arquivo CSV
     * @param c
     */
    private void encontrarCompraProdutos(Compra c) {
        try {
            FileReader arq = new FileReader("CompraProdutos.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (valor[0].equals(Integer.toString(c.getCodigo()))) {
                    c.addProduto(Integer.parseInt(valor[1]), Float.parseFloat(valor[2]), Integer.parseInt(valor[3]));
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            //
        }catch (IOException ex2) {
            //
        }
    }
    
    /**
     * Retorna a forma de pagamento identificado pelo código passado por parâmetro
     * @param codigo
     * @return Forma de Pagamento
     */   
    public FormaDePagamento encontrarFormaDePagamento(int codigo) {
        try {
            FileReader arq = new FileReader("FormaDePagamento.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            FormaDePagamento f = new FormaDePagamento();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    if(!"1".equals(valor[2])) {
                        return null;
                    }
                    f.setCodigo(parseInt(valor[0]));
                    f.setValorTotal(parseFloat(valor[1]));
                    f.setIdentificador(parseInt(valor[2]));
                    
                    arq.close();
                    entrada.close();
                    return f;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        }
        return null;
    }    
    
    /**
     * Retorna o cartao identificado pelo código passado por parâmetro
     * @param codigo
     * @return Cartao
     */   
    public Cartao encontrarFormaDePagamentoCartao(int codigo) {
        try {
            FileReader arq = new FileReader("FormaDePagamento.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Cartao c = new Cartao();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    if(!"0".equals(valor[2])) {
                        return null;
                    }
                    c.setCodigo(parseInt(valor[0]));
                    c.setValorTotal(parseFloat(valor[1]));
                    c.setIdentificador(parseInt(valor[2]));
                    c.setTipo(valor[3]);
                    c.setQtdParcelas(parseInt(valor[4]));
                    
                    arq.close();
                    entrada.close();
                    return c;
                }
                if (parseInt(valor[0]) > codigo) { // já passou
                    break;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (TipoDeCartaoInvalidoException ex3) {
            return null;
        }
        return null;
    }
    
    /**
     * Retorna o dinheiro identificado pelo código passado por parâmetro
     * @param codigo
     * @return Dinheiro
     */   
    public Dinheiro encontrarFormaDePagamentoDinheiro(int codigo) {
        try {
            FileReader arq = new FileReader("FormaDePagamento.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Dinheiro d = new Dinheiro();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    if(!"1".equals(valor[2])) {
                        return null;
                    }
                    d.setCodigo(parseInt(valor[0]));
                    d.setValorTotal(parseFloat(valor[1]));
                    d.setIdentificador(parseInt(valor[2]));
                    d.setValorRecebido(parseFloat(valor[3]));
                    
                    arq.close();
                    entrada.close();
                    return d;
                }
                if (parseInt(valor[0]) > codigo) { // já passou
                    break;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        }
        return null;
    }    
    
    /**
     * Retorna uma lista de todos os pagamentos feitos em cartão
     * @return Lista de Cartão
     */   
    public List<Cartao> encontrarFormasDePagamentoCartao() {
        try {
            FileReader arq = new FileReader("FormaDePagamento.csv");
            BufferedReader entrada = new BufferedReader(arq);
            List<Cartao> pagamentos = new ArrayList();
            String linha;
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if("0".equals(valor[2])) {
                    Cartao c = new Cartao();
                    c.setCodigo(parseInt(valor[0]));
                    c.setValorTotal(parseFloat(valor[1]));
                    c.setIdentificador(parseInt(valor[2]));
                    c.setTipo(valor[3]);
                    c.setQtdParcelas(parseInt(valor[4]));

                    pagamentos.add(c);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return pagamentos;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        } catch (TipoDeCartaoInvalidoException ex) {
            return null;
        }
    }
    
    /**
     * Retorna uma lista de todos os pagamentos feitos em dinheiro
     * @return Lista de Dinheiro
     */   
    public List<Dinheiro> encontrarFormasDePagamentoDinheiro() {
        try {
            FileReader arq = new FileReader("FormaDePagamento.csv");
            BufferedReader entrada = new BufferedReader(arq);
            List<Dinheiro> pagamentos = new ArrayList();
            String linha;
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if("1".equals(valor[2])) {
                    Dinheiro d = new Dinheiro();
                    d.setCodigo(parseInt(valor[0]));
                    d.setValorTotal(parseFloat(valor[1]));
                    d.setIdentificador(parseInt(valor[2]));
                    d.setValorRecebido(parseFloat(valor[3]));

                    pagamentos.add(d);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return pagamentos;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex2) {
            return null;
        }
    }
    
    /**
     * Retorna duas listsa de todos os pagamentos feitos em dinheiro e cartão, as listas são
     * recebidas como parâmetros
     * @param dinheiro
     * @param cartao
     */   
    public void encontrarFormasDePagamento(List<Dinheiro> dinheiro, List<Cartao> cartao) {
        try {
            FileReader arq = new FileReader("FormaDePagamento.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if("1".equals(valor[2])) {
                    Dinheiro d = new Dinheiro();
                    d.setCodigo(parseInt(valor[0]));
                    d.setValorTotal(parseFloat(valor[1]));
                    d.setIdentificador(parseInt(valor[2]));
                    d.setValorRecebido(parseFloat(valor[3]));

                    dinheiro.add(d);
                } 
                else if("0".equals(valor[2])) {
                    Cartao c = new Cartao();
                    c.setCodigo(parseInt(valor[0]));
                    c.setValorTotal(parseFloat(valor[1]));
                    c.setIdentificador(parseInt(valor[2]));
                    c.setTipo(valor[3]);
                    c.setQtdParcelas(parseInt(valor[4]));

                    cartao.add(c);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            //
        } catch (IOException ex2) {
            //
        } catch (TipoDeCartaoInvalidoException ex) {
            //
        }
    }
    
    /**
     * Retorna a venda identificada pelo código passado por parâmetro
     * @param codigo
     * @return Venda
     */   
    public Venda encontrarVenda(int codigo) {
        try {
            FileReader arq = new FileReader("Venda.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Venda v = new Venda();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == codigo) {
                    v.setCodigo(parseInt(valor[0]));
                    v.setData(formato.parse(valor[1]));
                    v.setCliente(this.encontrarCliente(parseInt(valor[2])));
                    v.setFuncionario(this.encontrarFuncionarioLogin(valor[3]));
                    v.setFormaPagamento(this.encontrarFormaDePagamentoCartao(Integer.parseInt(valor[4])));
                    v.setFormaPagamento(this.encontrarFormaDePagamentoDinheiro(Integer.parseInt(valor[4])));
                    this.encontrarVendaProdutos(v);
                    this.encontrarVendaServicos(v);
                    
                    arq.close();
                    entrada.close();
                    return v;
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ParseException ex2) {
            return null;
        }
        return null;
    }
    
    /**
     * Adiciona ao atributo "produtos" da venda que foi passado como parâmetro um Map dos produtos
     * cadastrados que foram vendidos, com base no arquivo CSV
     * @param v 
     */
    private void encontrarVendaProdutos(Venda v) {
        try {
            FileReader arq = new FileReader("VendaProdutos.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[0]) == v.getCodigo()) {
                    v.addProduto(Integer.parseInt(valor[1]), Float.parseFloat(valor[2]), Integer.parseInt(valor[3]));
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            //
        }catch (IOException ex2) {
            //
        }
    }
    
    /**
     * Adiciona ao atributo "servicos" da venda que foi passado como parâmetro um Map dos servicos
     * cadastrados que foram vendidos, com base no arquivo CSV
     * @param v 
     */
    private void encontrarVendaServicos(Venda v) {
        try {
            FileReader arq = new FileReader("VendaServicos.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (valor[0].equals(Integer.toString(v.getCodigo()))) {
                    v.addServico(parseInt(valor[1]));
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            //
        }catch (IOException ex2) {
            //
        }
    }
    
    /**
     * Retorna uma lista dos produtos que estão em estoque
     * @return Lista de Produto
     */
    public List<Produto> encontrarProdutoEstoque() {
        try {
            List<Produto> produtos = new ArrayList();
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[6]) > 0) {
                    Produto p = new Produto();
                    p.setCodigo(parseInt(valor[0]));
                    p.setNome(valor[1]);
                    p.setMarca(valor[2]);
                    p.setUnidade(valor[3]);
                    p.setQtdUnitaria(parseFloat(valor[4]));
                    p.setValor(parseFloat(valor[5]));
                    p.setQtdEstoque(parseInt(valor[6]));
                    p.setQtdEstoqueMin(parseInt(valor[7]));
                    produtos.add(p);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return produtos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } 
    }
    
    /**
     * Retorna uma lista dos produtos que estão em estoque crítico
     * @return Lista de Produto
     */
    public List<Produto> encontrarProdutoEstoqueCritico() {
        try {
            List<Produto> produtos = new ArrayList();
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if (parseInt(valor[6]) < parseInt(valor[7])) {
                    Produto p = new Produto();
                    p.setCodigo(parseInt(valor[0]));
                    p.setNome(valor[1]);
                    p.setMarca(valor[2]);
                    p.setUnidade(valor[3]);
                    p.setQtdUnitaria(parseFloat(valor[4]));
                    p.setValor(parseFloat(valor[5]));
                    p.setQtdEstoque(parseInt(valor[6]));
                    p.setQtdEstoqueMin(parseInt(valor[7]));
                    produtos.add(p);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return produtos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } 
    }
    
    /**
     * Retorna uma lista dos produtos que não estão em estoque
     * @return Lista de Produto
     */
    public List<Produto> encontrarProdutoEstoqueZerado() {
        try {
            List<Produto> produtos = new ArrayList();
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                String[] valor = linha.split(";");
                if ("0".equals(valor[6])) {
                    Produto p = new Produto();
                    p.setCodigo(parseInt(valor[0]));
                    p.setNome(valor[1]);
                    p.setMarca(valor[2]);
                    p.setUnidade(valor[3]);
                    p.setQtdUnitaria(parseFloat(valor[4]));
                    p.setValor(parseFloat(valor[5]));
                    p.setQtdEstoque(parseInt(valor[6]));
                    p.setQtdEstoqueMin(parseInt(valor[7]));
                    produtos.add(p);
                }
                linha = entrada.readLine();
            } while (linha != null);
            arq.close();
            entrada.close();
            return produtos;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ex2) {
            return null;
        } 
    }
}
