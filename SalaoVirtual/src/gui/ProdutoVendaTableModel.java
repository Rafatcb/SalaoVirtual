/*
 * Classe modelo da tabela de produtos
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Produto;

/**
 * Classe modelo da tabela de produtos
 *
 * @author Rafael Tavares
 */
public class ProdutoVendaTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "Nome", "Marca", "Qtd Unitária", "Valor", "Quantidade", "Valor Total"};
    private List<Produto> produtos;
    private List<Integer> quantidades;
    
    /**
     * Método construtor para adicionar produtos à lista
     * @param p - lista de produtos que aparecerão na tabela
     * @param q - lista identificando a quantidade de cada produto
     */
    public ProdutoVendaTableModel(List<Produto> p, List<Integer> q){
        if (p == null) {
            produtos = new ArrayList();
            quantidades = new ArrayList();
        }
        else {
            produtos = p;
            quantidades = q;
        }
    }
    
    
    /**
     * Retorna o nome da coluna - também conhecido como Header
     * Polimorfismo: Sobrescrita
     * @param col
     * @return Nome da coluna - Header
     */
    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    /**
     * Retorna a quantidade de linhas na lista
     * Polimorfismo: Sobrescrita
     * @return Quantidade de linhas
     */
    @Override
    public int getRowCount() {
        return produtos.size();
    }

    /**
     * Retorna a quantidade de colunas da lista
     * Polimorfismo: Sobrescrita
     * @return Quantidade de colunas
     */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    /**
     * Retorna o valor contido na célula especificada por linha e coluna
     * Polimorfismo: Sobrescrita
     * @param indiceLinha
     * @param indiceColuna
     * @return O valor contido na célula especificada
     */
    @Override
    public Object getValueAt(int indiceLinha, int indiceColuna) {
        try {
            switch (indiceColuna) {
            case 0: // Código
                return produtos.get(indiceLinha).getCodigo();
            case 1: // Nome
                return produtos.get(indiceLinha).getNome();
            case 2: // Marca
                return produtos.get(indiceLinha).getMarca();
            case 3: // Qtd Unitária
                return Float.toString(produtos.get(indiceLinha).getQtdUnitaria()) + " " + produtos.get(indiceLinha).getUnidade();
            case 4: // Valor
                return produtos.get(indiceLinha).getValor();
            case 5: // Quantidade
                return quantidades.get(indiceLinha);
            case 6: // Valor Total
                return produtos.get(indiceLinha).getValor() * quantidades.get(indiceLinha);
            default:
                throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
}
