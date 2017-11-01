/*
 * Classe modelo da tabela de produtos na consulta de fornecedor
 */
package gui.tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Compra;
import salaovirtual.Fornecedor;
import salaovirtual.access.Consulta;

/**
 * Classe modelo da tabela de produtos na consulta de fornecedor
 *
 * @author Rafael Tavares
 */
public class ConsultaFornecimentoTableModel extends AbstractTableModel {
    private final String[] colunas = {"Codigo", "Data", "Valor Total"};
    private List<Compra> fornecimentos; // quantidade de compra
    
    /**
     * Método construtor
     * @param f
     */
    public ConsultaFornecimentoTableModel(Fornecedor f) {
        try {
            if (f == null) {
                fornecimentos = new ArrayList();
            }
            else {
                Consulta con = new Consulta();
                fornecimentos = con.encontrarCompra(f);
            }
        } catch (NullPointerException e) {
            fornecimentos = new ArrayList();
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
        return fornecimentos.size();
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
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            switch (indiceColuna) {
                case 0: // Código
                    return this.fornecimentos.get(indiceLinha).getCodigo();
                case 1: // Data
                    return df.format(this.fornecimentos.get(indiceLinha).getData());
                case 2: // Valor total
                    return "R$ " + String.format("%.2f", this.fornecimentos.get(indiceLinha).getValorTotal());
                default:
                    throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            fornecimentos = new ArrayList();
            return null;
        }
    }
}
