/*
 * Classe modelo da tabela de produtos na consulta de cliente
 */
package gui.tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Cliente;

/**
 * Classe modelo da tabela de produtos na consulta de cliente
 *
 * @author Rafael Tavares
 */
public class ConsultaClienteTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "CPF", "Nome", "Telefone", "E-mail", "Data Nascimento"};
    private List<Cliente> clientes; // quantidade de compra
    
    /**
     * Método construtor
     * @param c
     */
    public ConsultaClienteTableModel(List<Cliente> c) {
        try {
            if (c.isEmpty()) {
                clientes = new ArrayList();
            }
            else {
                clientes = c;
            }
        } catch (NullPointerException e) {
            clientes = new ArrayList();
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
        return clientes.size();
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
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            switch (indiceColuna) {
                case 0: // Código
                    return this.clientes.get(indiceLinha).getCodigo();
                case 1: // CPF
                    return this.clientes.get(indiceLinha).getCpf();
                case 2: // Nome
                    return this.clientes.get(indiceLinha).getNome();
                case 3: // Telefone
                    return this.clientes.get(indiceLinha).getTelefone();
                case 4: // E-mail
                    return this.clientes.get(indiceLinha).getEmail();
                case 5: // Data
                    return df.format(this.clientes.get(indiceLinha).getDataAniversario());
                default:
                    throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            clientes = new ArrayList();
            return null;
        }
    }
}
