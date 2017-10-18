/*
 * Classe referente ao cadastro dos objetos em arquivo
 */
package salaovirtual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author Rafael Tavares
 */
public class Cadastro {
    // Tornar o cadastro do Fornecedor mais restrito em relação à repetição de CNPJ
        // É possível se apoiar no cadastro do Funcionário para fazer algo assim
    // O mesmo para cadastro do Cliente em relação ao CPF
    
    
    
    /**
     * Método para gravar o fornecedor passado como parâmetro em um arquivo CSV
     * @param f
     */
    public void gravarFornecedor(Fornecedor f) {
        try {
            Consulta consulta = new Consulta();
            FileWriter arq = new FileWriter("Fornecedor.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            f.setCodigo(consulta.getProxCodigo("Fornecedor.csv"));
            saida.write(f.toString());
            saida.newLine();
            saida.close();
            arq.close();
            this.gravarFornecedorProdutos(f);
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o mapa de produtos fornecidos pelo fornecedor passado como parâmetro em um arquivo CSV
     * @param f
     */
    private void gravarFornecedorProdutos(Fornecedor f) {
        try {
            FileWriter arq = new FileWriter("FornecedorProdutos.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            for (Map.Entry<Integer, Float> pair : f.getProdutos().entrySet()){
                saida.write(f.getCodigo() + ";" + pair.getKey() + ";" + pair.getValue());
                saida.newLine();
            }
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
    /**
     * Método para gravar o cliente passado como parâmetro em um arquivo CSV
     * @param c
     */
    public void gravarCliente(Cliente c) {
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
                throw new ObjetoJaCadastradoException();
            } catch (NullPointerException e) {
                /* O funcionário pode ter CPF nulo, mas não pode existir um funcionário com o mesmo cpf cadastrado */
                if (f.getCpf() != null) {
                    try {
                        Funcionario ftemp2 = new Funcionario();
                        ftemp2 = consulta.encontrarFuncionarioCpf(f.getCpf());
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
     */
    private void gravarServico(Servico s) {
        try {
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
}
