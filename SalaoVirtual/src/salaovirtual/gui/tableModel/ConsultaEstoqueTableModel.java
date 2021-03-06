/*
 * Classe modelo da tabela de produtos na consulta de estoque
 */
package salaovirtual.gui.tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Produto;
import salaovirtual.acesso.Consulta;

/**
 * Classe modelo da tabela de produtos na consulta de estoque
 *
 * @author Rafael Tavares
 */
public class ConsultaEstoqueTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "Nome", "Unidade", "Marca", "Valor", "Estoque", "Estoque Min."};
    private List<Produto> produtos; // quantidade de compra
    
    /**
     * Método construtor
     * @param opcao - 0 = vazia, 1 = estoque, 2 = estoque critico, 3 = estoque zerado
     */
    public ConsultaEstoqueTableModel(int opcao) {
        Consulta con = new Consulta();
        switch (opcao) {
            case 0:
                produtos = new ArrayList();
                break;
            case 1:
                produtos = con.encontrarProdutoEstoque();
                break;
            case 2:
                produtos = con.encontrarProdutoEstoqueCritico();
                break;
            case 3:
                produtos = con.encontrarProdutoEstoqueZerado();
                break;
            default:
                produtos = new ArrayList();
                break;
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
                    return this.produtos.get(indiceLinha).getCodigo();
                case 1: // Nome
                    return this.produtos.get(indiceLinha).getNome();
                case 2: // Unidade
                    return this.produtos.get(indiceLinha).getQtdUnitaria() + " " + this.produtos.get(indiceLinha).getUnidade();
                case 3: // Marca
                    return this.produtos.get(indiceLinha).getMarca();
                case 4: // Valor
                    return String.format("%.2f", this.produtos.get(indiceLinha).getValor());
                case 5: // Qtd. Estoque
                    return this.produtos.get(indiceLinha).getQtdEstoque();
                case 6: // Qtd Estoque Min
                    return this.produtos.get(indiceLinha).getQtdEstoqueMin();
                default:
                    throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            produtos = new ArrayList();
            return null;
        }
    }
}
