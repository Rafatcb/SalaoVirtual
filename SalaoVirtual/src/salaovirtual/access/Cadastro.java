/*
 * Classe referente ao cadastro dos objetos em arquivo
 */
package salaovirtual.access;

import exceptions.ObjetoJaCadastradoException;
import exceptions.ChaveNulaException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import salaovirtual.Cartao;
import salaovirtual.Cliente;
import salaovirtual.Compra;
import salaovirtual.Dinheiro;
import salaovirtual.Fornecedor;
import salaovirtual.Funcionario;
import salaovirtual.Produto;
import salaovirtual.Servico;
import salaovirtual.Venda;

/**
 * Classe referente ao cadastro dos objetos em arquivo
 * 
 * @author Rafael Tavares
 */
public class Cadastro {    
    /**
     * Método para gravar o fornecedor passado como parâmetro em um arquivo CSV
     * @param f
     * @throws exceptions.ObjetoJaCadastradoException
     */
    public void gravarFornecedor(Fornecedor f) throws ObjetoJaCadastradoException {
        try {   
            /* Verifica se o arquivo existe */
            FileReader arq = new FileReader("Fornecedor.csv");
            BufferedReader entrada = new BufferedReader(arq);
            entrada.close();
            arq.close();    
            
            /* Não pode existir um fornecedor com este cnpj cadastrado */
            if (f.getCnpj() != null) {
                Consulta consulta = new Consulta();
                try {
                    Fornecedor ftemp = new Fornecedor();
                    ftemp = consulta.encontrarFornecedorCnpj(f.getCnpj());
                    ftemp.getCodigo();
                    throw new ObjetoJaCadastradoException();
                } catch (NullPointerException e) {
                    //
                }
            }
        } catch (FileNotFoundException ex) {
            /* Se o arquivo não existe, eu crio ele */
            try {
                FileWriter arq = new FileWriter("Fornecedor.csv");
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("Fornecedor.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            f.setCodigo(consulta.getProxCodigo("Fornecedor.csv"));
            saida.write(f.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o cliente passado como parâmetro em um arquivo CSV
     * @param c
     * @throws exceptions.ObjetoJaCadastradoException
     */
    public void gravarCliente(Cliente c) throws ObjetoJaCadastradoException {
        try {   
            /* Verifica se o arquivo existe */
            FileReader arq = new FileReader("Cliente.csv");
            BufferedReader entrada = new BufferedReader(arq);
            entrada.close();
            arq.close();    
            
            /* Não pode existir um fornecedor com este cnpj cadastrado */
            if (c.getCpf()!= null) {
                Consulta consulta = new Consulta();
                try {
                    Cliente ctemp = new Cliente();
                    ctemp = consulta.encontrarClienteCpf(c.getCpf());
                    ctemp.getCodigo();
                    throw new ObjetoJaCadastradoException();
                } catch (NullPointerException e) {
                    //
                }
            }
            
        } catch (FileNotFoundException ex) {
            /* Se o arquivo não existe, eu crio ele */
            try {
                FileWriter arq = new FileWriter("Cliente.csv");
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("Cliente.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            c.setCodigo(consulta.getProxCodigo("Cliente.csv"));
            saida.write(c.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o funcionário passado como parâmetro em um arquivo CSV
     * @param f
     * @throws exceptions.ObjetoJaCadastradoException
     * @throws exceptions.ChaveNulaException
     */
    public void gravarFuncionario(Funcionario f) throws ObjetoJaCadastradoException, ChaveNulaException { // PRECISA VERIFICAR SE ESTE LOGIN/CPF JÁ ESTÁ CADASTRADO
        /* O funcionário deve possuir um login */
        if (f.getLogin() == null) {
            throw new ChaveNulaException();
        }
        
        try {   
            /* Verifica se o arquivo existe */
            FileReader arq = new FileReader("Funcionario.csv");
            BufferedReader entrada = new BufferedReader(arq);
            entrada.close();
            arq.close();    
            
            /* Não pode existir um funcionário com este login cadastrado */
            Consulta consulta = new Consulta();
            try {
                Funcionario ftemp = new Funcionario();
                ftemp = consulta.encontrarFuncionarioLogin(f.getLogin());
                ftemp.getCidade();
                throw new ObjetoJaCadastradoException();
            } catch (NullPointerException e) {
                /* O funcionário pode ter CPF nulo, mas não pode existir um funcionário com o mesmo cpf cadastrado */
                if (f.getCpf() != null) {
                    try {
                        Funcionario ftemp2 = new Funcionario();
                        ftemp2 = consulta.encontrarFuncionarioCpf(f.getCpf());
                        ftemp2.getCidade();
                        throw new ObjetoJaCadastradoException();
                    } catch (NullPointerException ex) {
                    }
                }
            }
            
        } catch (FileNotFoundException ex) {
            /* Se o arquivo não existe, eu crio ele */
            try {
                FileWriter arq = new FileWriter("Funcionario.csv");
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            //Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            FileWriter arq = new FileWriter("Funcionario.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            saida.write(f.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o produto passado como parâmetro em um arquivo CSV
     * @param p
     */
    public void gravarProduto(Produto p) {
        try {
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("Produto.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            p.setCodigo(consulta.getProxCodigo("Produto.csv"));
            saida.write(p.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o serviço passado como parâmetro em um arquivo CSV
     * @param s
     * @throws exceptions.ChaveNulaException
     */
    public void gravarServico(Servico s) throws ChaveNulaException {
        try {
            if (s.getFuncionario() == null) {
                throw new ChaveNulaException();
            }
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("Servico.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            s.setCodigo(consulta.getProxCodigo("Servico.csv"));
            saida.write(s.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar a compra passada como parâmetro em um arquivo CSV
     * @param c
     * @throws exceptions.ChaveNulaException
     */
    public void gravarCompra(Compra c) throws ChaveNulaException {
        try {
            if (c.getFornecedor() == null) {
                throw new ChaveNulaException();
            }
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("Compra.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            c.setCodigo(consulta.getProxCodigo("Compra.csv"));
            saida.write(c.toString());
            saida.newLine();
            this.gravarCompraProdutos(c);
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o mapa de produtos fornecidos pelo fornecedor passado como parâmetro em um arquivo CSV
     * @param c
     */
    private void gravarCompraProdutos(Compra c) {
        try {
            FileWriter arq = new FileWriter("CompraProdutos.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            for (int i = 0; i < c.getQuantidade().size(); i++) {
                saida.write(c.getCodigo() + ";" + c.getProdutos().get(i) + ";" + c.getValores().get(i) + ";" + c.getQuantidade().get(i));
                saida.newLine();
            }
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar a forma de pagamento do cartão passado como parâmetro em um arquivo CSV
     * Polimorfismo: Sobrecarga
     * @param c
     */
    public void gravarFormaDePagamento(Cartao c) {
        try {
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("FormaDePagamento.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            c.setCodigo(consulta.getProxCodigo("FormaDePagamento.csv"));
            saida.write(c.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar a forma de pagamento do dinheiro passado como parâmetro em um arquivo CSV
     * Polimorfismo: Sobrecarga
     * @param d
     */
    public void gravarFormaDePagamento(Dinheiro d) {
        try {
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("FormaDePagamento.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            d.setCodigo(consulta.getProxCodigo("FormaDePagamento.csv"));
            saida.write(d.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar a compra passada como parâmetro em um arquivo CSV
     * @param v
     */
    public void gravarVenda(Venda v) {
        try {
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("Venda.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            v.setCodigo(consulta.getProxCodigo("Venda.csv"));
            saida.write(v.toString());
            saida.newLine();
            this.gravarVendaProdutos(v);
            this.gravarVendaServicos(v);
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o mapa de produtos vendidos (Código do produto, Quantidade) passado como parâmetro em um arquivo CSV
     * @param v
     */
    private void gravarVendaProdutos(Venda v) {
        try {
            FileWriter arq = new FileWriter("VendaProdutos.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            int pos = 0;
            List<Integer> quantidades = v.getQuantidadesProdutos();
            for (Map.Entry<Integer, Float> pair : v.getProdutos().entrySet()){
                saida.write(v.getCodigo() + ";" + pair.getKey() + ";" + pair.getValue() + ";" + quantidades.get(pos));
                saida.newLine();
                pos++;
            }
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o mapa de serviços vendidos (Código do serviço, Quantidade) passado como parâmetro em um arquivo CSV
     * @param v
     */
    private void gravarVendaServicos(Venda v) {
        try {
            FileWriter arq = new FileWriter("VendaServicos.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            for (int sCod : v.getServicos()) {
                saida.write(v.getCodigo() + ";" + sCod);
                saida.newLine();
            }
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
}
