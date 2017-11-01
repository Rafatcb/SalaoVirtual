/*
 * Classe modelo da tabela de produtos de fornecimento
 */
package gui.tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Compra;
import salaovirtual.Fornecedor;
import salaovirtual.Produto;
import salaovirtual.access.Consulta;

/**
 * Classe modelo da tabela de produtos de fornecimento
 *
 * @author Rafael Tavares
 */
public class ProdutoFornecimentoTableModel extends AbstractTableModel {
    private final String[] colunas = {"Cód. Prod.", "Nome do Produto", "Qtd. Unitária", "Marca", "Quantidade", "Valor Venda", "Valor Compra", "Valor Total"};
    private List<Integer> quantidade; // quantidade de compra
    private List<Produto> produtos; // produto de compra
    private List<Float> valorCompra; // valor de compra
    
    /**
     * Método construtor
     * @param p - produto
     * @param quantidade - quantidade
     * @param v - valor
     */
    public ProdutoFornecimentoTableModel(List<Produto> p, List<Integer> quantidade, List<Float> v) {
        try {
            if (p.isEmpty()) {
                produtos = new ArrayList();
                valorCompra = new ArrayList();
                this.quantidade = new ArrayList();
            }
            else {
                produtos = p;
                valorCompra = v;
                this.quantidade = quantidade;
            }
        } catch (NullPointerException e) {
            produtos = new ArrayList();
            valorCompra = new ArrayList();
            this.quantidade = new ArrayList();
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
        return valorCompra.size();
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
        switch (indiceColuna) { 
            case 0: // Código
                return produtos.get(indiceLinha).getCodigo();
            case 1: // Nome do produto
                return produtos.get(indiceLinha).getNome();
            case 2: // Qtd unitária
                return produtos.get(indiceLinha).getQtdUnitaria() + " " + produtos.get(indiceLinha).getUnidade();
            case 3: // Marca
                return produtos.get(indiceLinha).getMarca();
            case 4: // Quantidade
                return quantidade.get(indiceLinha);
            case 5: // Valor de venda
                return String.format("%.2f", produtos.get(indiceLinha).getValor());
            case 6: // Valor de compra
                return String.format("%.2f", valorCompra.get(indiceLinha));
            case 7: // Valor Total
                return String.format("%.2f", valorCompra.get(indiceLinha) * quantidade.get(indiceLinha));
            default:
                throw new IndexOutOfBoundsException();
        }
    }
}
