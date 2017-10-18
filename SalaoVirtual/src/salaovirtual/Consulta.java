/*
 * Classe referente à consulta de objetos em arquivo
 */
package salaovirtual;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe referente à consulta de objetos em arquivo
 * @author Rafael Tavares
 */
public class Consulta {
    // Criar método para listar clientes que fazem aniversário em tal mês   
    // Criar método que encontra o fornecedor com base no código de um produto fornecido
    // Criar método para listar produtos por faixa de valor
    // Criar método para listar produtos com estoque crítico
    // Criar método para listar todos os serviços de um mês
    // Criar método para listar todos os serviços de um ano
    // Criar método para listar todos os serviços que um cliente fez
    // Criar método para listar todos os serviços que um funcionário fez
    
    
    
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
            Fornecedor f = new Fornecedor();
            do {
                linha = entrada.readLine();
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
                    f.setComplemento(valor[9]);
                    this.encontrarFornecedorProdutos(f);
                    arq.close();
                    entrada.close();
                    return f;
                }
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
                if (valor[2].equals(nome)) {
                    f.setCodigo(parseInt(valor[0]));
                    f.setCnpj(valor[1]);
                    f.setNome(valor[2]);
                    f.setTelefone(valor[3]);
                    f.setEmail(valor[4]);
                    f.setRua(valor[5]);
                    f.setNumero(parseInt(valor[6]));
                    f.setCidade(valor[7]);
                    f.setEstado(valor[8]);
                    f.setComplemento(valor[9]);
                    this.encontrarFornecedorProdutos(f);
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
     * Retorna uma lista dos fornecedores que possuem o CNPJ passado como parâmetro
     * @param cnpj
     * @return Lista de Fornecedor
     */
    public List<Fornecedor> encontrarFornecedorCnpj(String cnpj) {
        try {
            List<Fornecedor> fornecedores = new ArrayList();
            FileReader arq = new FileReader("Fornecedor.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                Fornecedor f = new Fornecedor();
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
                    f.setComplemento(valor[9]);
                    this.encontrarFornecedorProdutos(f);
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
     * Adiciona ao atributo "produtos" do fornecedor que hcamou esta função um Map dos produtos
     * cadastrados que ele fornece, com base no arquivo CSV
     * @param f 
     */
    private void encontrarFornecedorProdutos(Fornecedor f) {
        try {
            FileReader arq = new FileReader("FornecedorProdutos.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            linha = entrada.readLine();
            Map mapa = new HashMap<>();
            do {
                String[] valor = linha.split(";");
                if (valor[0].equals(Integer.toString(f.getCodigo()))) {
                    mapa.put(parseInt(valor[1]), parseFloat(valor[2]));
                }
                linha = entrada.readLine();
            } while (linha != null);
            f.setProdutos(mapa);
            arq.close();
            entrada.close();
        } catch (FileNotFoundException ex) {
            //
        }catch (IOException ex2) {
            //
        }
    }
    
    /**
     * Retorna o cliente identificado pelo código passado por parâmetro
     * Polimorfismo: Sobrecarga
     * @param codigo
     * @return Cliente
     */    
    public Cliente encontrarCliente(int codigo) {
        try {
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            Cliente c = new Cliente();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                linha = entrada.readLine();
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
     * Polimorfismo: Sobrecarga
     * @param nome
     * @return Lista de Cliente
     */
    public List<Cliente> encontrarCliente(String nome) {
        try {
            List<Cliente> clientes = new ArrayList();
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                String[] valor = linha.split(";");
                if (valor[2].equals(nome)) {
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
            Funcionario f = new Funcionario();
            do {
                linha = entrada.readLine();
                String[] valor = linha.split(";");
                if (valor[0].equals(login)) {
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
                if (valor[3].equals(nome)) {
                    Funcionario f = new Funcionario();
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
            Funcionario f = new Funcionario();
            do {
                linha = entrada.readLine();
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
            Produto p = new Produto();
            do {
                linha = entrada.readLine();
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
                if (valor[1].equals(nome)) {
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
                if (valor[2].equals(marca)) {
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
            Servico s = new Servico();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                linha = entrada.readLine();
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
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                String[] valor = linha.split(";");
                if (valor[1].equals(nome)) {
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
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            do {
                String[] valor = linha.split(";");
                if (valor[4].equals(estado)) {
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
}
