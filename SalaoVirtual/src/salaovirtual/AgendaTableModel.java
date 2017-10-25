/*
 * Classe modelo da tabela agenda
 */
package salaovirtual;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Classe modelo da tabela agenda
 *
 * @author Rafael Tavares
 */
public class AgendaTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "Data", "Nome do Serviço", "Valor", "Cliente", "Funcionário"};
    private List<Integer> codigos;
    private List<Date> datas;
    private List<String> nomeServicos;
    private List<Float> valores;
    private List<Cliente> clientes;
    private List<Funcionario> funcionarios;
    
    public AgendaTableModel() {
        //lista = new ArrayList();
    }
    
    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    @Override
    public int getRowCount() {
        return codigos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int indiceLinha, int indiceColuna) {
        switch (indiceColuna) {
        case 0: // Código
            //return randAccess.get(indiceLinha).getKey().getfiles();
        case 1: // Data
            //return randAccess.get(indiceLinha).getKey().getduration();
        case 2: // Nome do Serviço
            //return randAccess.get(indiceLinha).getKey().getstatus();
        case 3: // Valor
            //return randAccess.get(indiceLinha).getKey().getstatus();
        case 4: // Nome do Cliente
            //return randAccess.get(indiceLinha).getKey().getstatus();
        case 5: // Nome do Funcionário
            //return randAccess.get(indiceLinha).getKey().getstatus();
        default:
            throw new IndexOutOfBoundsException();
        }
    }
}
