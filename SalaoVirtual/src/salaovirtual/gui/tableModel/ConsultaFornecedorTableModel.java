/*
 * Classe modelo da tabela de produtos na consulta de fornecedor
 */
package salaovirtual.gui.tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Fornecedor;

/**
 * Classe modelo da tabela de produtos na consulta de fornecedor
 *
 * @author Rafael Tavares
 */
public class ConsultaFornecedorTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "CNPJ", "Nome", "Telefone", "Email","Endereço", "Cidade", "Estado"};
    private List<Fornecedor> fornecedores; // quantidade de compra
    
    /**
     * Método construtor
     * @param f
     */
    public ConsultaFornecedorTableModel(List<Fornecedor> f) {
        try {
            if (f.isEmpty()) {
                fornecedores = new ArrayList();
            }
            else {
                fornecedores = f;
            }
        } catch (NullPointerException e) {
            fornecedores = new ArrayList();
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
        return fornecedores.size();
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
                    return this.fornecedores.get(indiceLinha).getCodigo();
                case 1: // CPF
                    return this.fornecedores.get(indiceLinha).getCnpj();
                case 2: // Nome
                    return this.fornecedores.get(indiceLinha).getNome();
                case 3: // Telefone
                    return this.fornecedores.get(indiceLinha).getTelefone();
                case 4: // Email
                    return this.fornecedores.get(indiceLinha).getEmail();
                case 5: // Endereco
                    return this.fornecedores.get(indiceLinha).getRua() + ", " + Integer.toString(this.fornecedores.get(indiceLinha).getNumero()) 
                            + ", " + this.fornecedores.get(indiceLinha).getComplemento();
                case 6: // Cidade
                    return this.fornecedores.get(indiceLinha).getCidade();
                case 7: // Estado
                    return this.fornecedores.get(indiceLinha).getEstado();
                default:
                    throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            fornecedores = new ArrayList();
            return null;
        }
    }
}
