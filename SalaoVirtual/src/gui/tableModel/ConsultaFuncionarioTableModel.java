/*
 * Classe modelo da tabela de produtos na consulta de funcionário
 */
package gui.tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Funcionario;

/**
 * Classe modelo da tabela de produtos na consulta de funcionário
 *
 * @author Rafael Tavares
 */
public class ConsultaFuncionarioTableModel extends AbstractTableModel {
    private final String[] colunas = {"Login", "CPF", "Nome", "Telefone", "Endereço", "Cidade", "Estado"};
    private List<Funcionario> funcionarios; // quantidade de compra
    
    /**
     * Método construtor
     * @param f
     */
    public ConsultaFuncionarioTableModel(List<Funcionario> f) {
        try {
            if (f.isEmpty()) {
                funcionarios = new ArrayList();
            }
            else {
                funcionarios = f;
            }
        } catch (NullPointerException e) {
            funcionarios = new ArrayList();
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
        return funcionarios.size();
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
                case 0: // Login
                    return this.funcionarios.get(indiceLinha).getLogin();
                case 1: // CPF
                    return this.funcionarios.get(indiceLinha).getCpf();
                case 2: // Nome
                    return this.funcionarios.get(indiceLinha).getNome();
                case 3: // Telefone
                    return this.funcionarios.get(indiceLinha).getTelefone();
                case 4: // Endereco
                    return this.funcionarios.get(indiceLinha).getRua() + ", " + Integer.toString(this.funcionarios.get(indiceLinha).getNumero()) 
                            + ", " + this.funcionarios.get(indiceLinha).getComplemento();
                case 5: // Cidade
                    return this.funcionarios.get(indiceLinha).getCidade();
                case 6: // Estado
                    return this.funcionarios.get(indiceLinha).getEstado();
                default:
                    throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            funcionarios = new ArrayList();
            return null;
        }
    }
}
