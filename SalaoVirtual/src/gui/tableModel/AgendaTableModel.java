/*
 * Classe modelo da tabela agenda
 */
package gui.tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Servico;
import salaovirtual.access.Consulta;

/**
 * Classe modelo da tabela agenda
 *
 * @author Rafael Tavares
 */
public class AgendaTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "Data", "Nome do Serviço", "Valor", "Cliente", "Funcionário"};
    private List<Servico> servicos;
    
    /**
     * Método construtor
     */
    public AgendaTableModel() {
        try {
            servicos = new ArrayList();
            Consulta con = new Consulta();
            servicos = con.encontrarServicoEstado("Agendado");
            Collections.sort(servicos);
        } catch (NullPointerException ex) {
            servicos = new ArrayList();
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
        return servicos.size();
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
            return servicos.get(indiceLinha).getCodigo();
        case 1: // Data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return sdf.format(servicos.get(indiceLinha).getData());
        case 2: // Nome do Serviço
            return servicos.get(indiceLinha).getNome();
        case 3: // Valor
            return "R$ " + String.format("%.2f", servicos.get(indiceLinha).getValor());
        case 4: // Nome do Cliente
            return servicos.get(indiceLinha).getCliente().getNome();
        case 5: // Nome do Funcionário
            return servicos.get(indiceLinha).getFuncionario().getNome();
        default:
            throw new IndexOutOfBoundsException();
        }
    }
}
