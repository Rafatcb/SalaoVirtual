/*
 * Classe modelo da tabela agenda
 */
package gui;

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
    
    public AgendaTableModel() {
        servicos = new ArrayList();
        Consulta con = new Consulta();
        servicos = con.encontrarServicoEstado("Agendado");
        Collections.sort(servicos);
    }
    
    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    @Override
    public int getRowCount() {
        return servicos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int indiceLinha, int indiceColuna) {
        switch (indiceColuna) {
        case 0: // Código
            return servicos.get(indiceLinha).getCodigo();
        case 1: // Data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(servicos.get(indiceLinha).getData());
        case 2: // Nome do Serviço
            return servicos.get(indiceLinha).getNome();
        case 3: // Valor
            return servicos.get(indiceLinha).getValor();
        case 4: // Nome do Cliente
            return servicos.get(indiceLinha).getCliente().getNome();
        case 5: // Nome do Funcionário
            return servicos.get(indiceLinha).getFuncionario().getNome();
        default:
            throw new IndexOutOfBoundsException();
        }
    }
}
