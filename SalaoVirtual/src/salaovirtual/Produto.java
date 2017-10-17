/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author r176257
 */
public class Produto {
    private int codigo;
    private String nome;
    private String marca;
    private String unidade;
    private float qtdUnitaria;
    private float valor;
    private int qtdEstoque;
    private int qtdEstoqueMin;

    // Criar método para listar produtos por faixa de valor
    // Criar método para listar produtos com estoque crítico
    // Fazer o método encomendar()
    
    public void encomendar() {
        
    }
    
    @Override
    public String toString() {
        return this.codigo + ";" + this.nome + ";" + this.marca + ";" + this.unidade + ";" + 
                this.qtdUnitaria + ";" + this.valor + ";" + this.qtdEstoque + ";" + this.qtdEstoqueMin;
    }
    
    public void gravarProduto() {
        try {
            FileWriter arq = new FileWriter("Produto.csv", true);
            BufferedWriter saida = new BufferedWriter(arq);
            this.setCodigo(this.getProxCodigo());
            saida.write(this.toString());
            saida.newLine();
            saida.close();
            arq.close();
        } catch (IOException e) {
            //
        }
    }
    
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
            try {
                FileWriter arq = new FileWriter("Produto.csv");
                BufferedWriter saida = new BufferedWriter(arq);
                saida.close();
                arq.close();
            } catch (IOException ex1) {
                //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }catch (IOException ex2) {
            // log
        }
        return null;
    }
    
    public List<Produto> encontrarProdutoNome(String nome) {
        try {
            List<Produto> produtos = new ArrayList();
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                Produto p = new Produto();
                String[] valor = linha.split(";");
                if (valor[1].equals(nome)) {
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
    
    public List<Produto> encontrarProdutoMarca(String marca) {
        try {
            List<Produto> produtos = new ArrayList();
            FileReader arq = new FileReader("Produto.csv");
            BufferedReader entrada = new BufferedReader(arq);
            String linha;
            
            linha = entrada.readLine();
            do {
                Produto p = new Produto();
                String[] valor = linha.split(";");
                if (valor[2].equals(marca)) {
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
    
    public int getProxCodigo() {
        int cod = 1;
        try {
            FileReader arq = new FileReader("Produto.csv");
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
                FileWriter arq = new FileWriter("Produto.csv");
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
    
    /* Construtores, Getters & Setters */
    public Produto(String nome) {
        this.nome = nome;
    }

    public Produto() {
        
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public String getMarca() {
        return marca;
    }

    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public float getQtdUnitaria() {
        return qtdUnitaria;
    }

    public void setQtdUnitaria(float qtdUnitaria) {
        this.qtdUnitaria = qtdUnitaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getQtdEstoqueMin() {
        return qtdEstoqueMin;
    }

    public void setQtdEstoqueMin(int qtdEstoqueMin) {
        this.qtdEstoqueMin = qtdEstoqueMin;
    }
    
}
