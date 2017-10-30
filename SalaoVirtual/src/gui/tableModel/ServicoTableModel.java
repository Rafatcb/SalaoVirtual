/*
 * Classe modelo da tabela de serviços
 */
package gui.tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import salaovirtual.Servico;
import salaovirtual.access.Consulta;

/**
 * Classe modelo da tabela de serviços
 *
 * @author Rafael Tavares
 */
public class ServicoTableModel extends AbstractTableModel {
    private final String[] colunas = {"Código", "Estado", "Data", "Nome do Serviço", "Valor", "Cliente", "Funcionário"};
    private List<Servico> servicos;
    
    /**
     * Método construtor, recebe como parâmetro todos os meios de busca possíveis. Caso não deseje este filtro,
     * deixe null ou um número negativo para números.
     * 
     * @param codigo
     * @param estado
     * @param dataInicio
     * @param dataFim
     * @param nomeServico
     * @param valorInicio
     * @param valorFim
     * @param codigoCliente
     * @param loginFuncionario
     */
    public ServicoTableModel(int codigo, String estado, Date dataInicio, Date dataFim, String nomeServico, float valorInicio, 
            float valorFim, int codigoCliente, String loginFuncionario) {
        try {
            servicos = new ArrayList();
            List<Servico> servicosTemp = new ArrayList();
            Consulta con = new Consulta();
            
            if (codigo > 0) {
                Servico s = con.encontrarServico(codigo);
                servicos.add(s);
            }
            else if (nomeServico != null) {
                servicosTemp = con.encontrarServicoNome(nomeServico);
                for (Servico s : servicosTemp) {
                    if (estado != null) {
                        if (s.getEstado().equals(estado)) {
                            if (dataInicio != null) {
                                if ((s.getData().after(dataInicio)) && (s.getData().before(dataFim))) {
                                    if (valorInicio >= 0) {
                                        if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                                            if (codigoCliente > 0) {
                                                if (s.getCliente().getCodigo() == codigoCliente) {
                                                    if (loginFuncionario != null) {
                                                        if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                            servicos.add(s);
                                                        }
                                                    }
                                                    else {
                                                        servicos.add(s);
                                                    }
                                                }
                                            }
                                            else {
                                                if (loginFuncionario != null) {
                                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                        servicos.add(s);
                                                    }
                                                }
                                                else {
                                                    servicos.add(s);
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        if (codigoCliente > 0) {
                                            if (s.getCliente().getCodigo() == codigoCliente) {
                                                if (loginFuncionario != null) {
                                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                        servicos.add(s);
                                                    }
                                                }
                                                else {
                                                    servicos.add(s);
                                                }
                                            }
                                        }
                                        else {
                                            if (loginFuncionario != null) {
                                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                    servicos.add(s);
                                                }
                                            }
                                            else {
                                                servicos.add(s);
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                if (valorInicio >= 0) {
                                    if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                                        if (codigoCliente > 0) {
                                            if (s.getCliente().getCodigo() == codigoCliente) {
                                                if (loginFuncionario != null) {
                                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                        servicos.add(s);
                                                    }
                                                }
                                                else {
                                                    servicos.add(s);
                                                }
                                            }
                                        }
                                        else {
                                            if (loginFuncionario != null) {
                                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                    servicos.add(s);
                                                }
                                            }
                                            else {
                                                servicos.add(s);
                                            }
                                        }
                                    }
                                }
                                else {
                                    if (codigoCliente > 0) {
                                        if (s.getCliente().getCodigo() == codigoCliente) {
                                            if (loginFuncionario != null) {
                                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                    servicos.add(s);
                                                }
                                            }
                                            else {
                                                servicos.add(s);
                                            }
                                        }
                                    }
                                    else {
                                        if (loginFuncionario != null) {
                                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                servicos.add(s);
                                            }
                                        }
                                        else {
                                            servicos.add(s);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (dataInicio != null) {
                            if ((s.getData().after(dataInicio)) && (s.getData().before(dataFim))) {
                                if (valorInicio >= 0) {
                                    if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                                        if (codigoCliente > 0) {
                                            if (s.getCliente().getCodigo() == codigoCliente) {
                                                if (loginFuncionario != null) {
                                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                        servicos.add(s);
                                                    }
                                                }
                                                else {
                                                    servicos.add(s);
                                                }
                                            }
                                        }
                                        else {
                                            if (loginFuncionario != null) {
                                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                    servicos.add(s);
                                                }
                                            }
                                            else {
                                                servicos.add(s);
                                            }
                                        }
                                    }
                                }
                                else {
                                    if (codigoCliente > 0) {
                                        if (s.getCliente().getCodigo() == codigoCliente) {
                                            if (loginFuncionario != null) {
                                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                    servicos.add(s);
                                                }
                                            }
                                            else {
                                                servicos.add(s);
                                            }
                                        }
                                    }
                                    else {
                                        if (loginFuncionario != null) {
                                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                servicos.add(s);
                                            }
                                        }
                                        else {
                                            servicos.add(s);
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            if (valorInicio >= 0) {
                                if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                                    if (codigoCliente > 0) {
                                        if (s.getCliente().getCodigo() == codigoCliente) {
                                            if (loginFuncionario != null) {
                                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                    servicos.add(s);
                                                }
                                            }
                                            else {
                                                servicos.add(s);
                                            }
                                        }
                                    }
                                    else {
                                        if (loginFuncionario != null) {
                                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                servicos.add(s);
                                            }
                                        }
                                        else {
                                            servicos.add(s);
                                        }
                                    }
                                }
                            }
                            else {
                                if (codigoCliente > 0) {
                                    if (s.getCliente().getCodigo() == codigoCliente) {
                                        if (loginFuncionario != null) {
                                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                servicos.add(s);
                                            }
                                        }
                                        else {
                                            servicos.add(s);
                                        }
                                    }
                                }
                                else {
                                    if (loginFuncionario != null) {
                                        if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                            servicos.add(s);
                                        }
                                    }
                                    else {
                                        servicos.add(s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (estado != null) {
                servicosTemp = con.encontrarServicoEstado(estado);
                for (Servico s : servicosTemp) {
                    if (dataInicio != null) {
                        if ((s.getData().after(dataInicio)) && (s.getData().before(dataFim))) {
                            if (valorInicio >= 0) {
                                if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                                    if (codigoCliente > 0) {
                                        if (s.getCliente().getCodigo() == codigoCliente) {
                                            if (loginFuncionario != null) {
                                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                    servicos.add(s);
                                                }
                                            }
                                            else {
                                                servicos.add(s);
                                            }
                                        }
                                    }
                                    else {
                                        if (loginFuncionario != null) {
                                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                servicos.add(s);
                                            }
                                        }
                                        else {
                                            servicos.add(s);
                                        }
                                    }
                                }
                            }
                            else {
                                if (codigoCliente > 0) {
                                    if (s.getCliente().getCodigo() == codigoCliente) {
                                        if (loginFuncionario != null) {
                                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                servicos.add(s);
                                            }
                                        }
                                        else {
                                            servicos.add(s);
                                        }
                                    }
                                }
                                else {
                                    if (loginFuncionario != null) {
                                        if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                            servicos.add(s);
                                        }
                                    }
                                    else {
                                        servicos.add(s);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (valorInicio >= 0) {
                            if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                                if (codigoCliente > 0) {
                                    if (s.getCliente().getCodigo() == codigoCliente) {
                                        if (loginFuncionario != null) {
                                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                                servicos.add(s);
                                            }
                                        }
                                        else {
                                            servicos.add(s);
                                        }
                                    }
                                }
                                else {
                                    if (loginFuncionario != null) {
                                        if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                            servicos.add(s);
                                        }
                                    }
                                    else {
                                        servicos.add(s);
                                    }
                                }
                            }
                        }
                        else {
                            if (codigoCliente > 0) {
                                if (s.getCliente().getCodigo() == codigoCliente) {
                                    if (loginFuncionario != null) {
                                        if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                            servicos.add(s);
                                        }
                                    }
                                    else {
                                        servicos.add(s);
                                    }
                                }
                            }
                            else {
                                if (loginFuncionario != null) {
                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                        servicos.add(s);
                                    }
                                }
                                else {
                                    servicos.add(s);
                                }
                            }
                        }
                    }
                }
            }
            else if (dataInicio != null) {
                servicosTemp = con.encontrarServico(dataInicio, dataFim);
                for (Servico s : servicosTemp) {
                    if (valorInicio >= 0) {
                        if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                            if (codigoCliente > 0) {
                                if (s.getCliente().getCodigo() == codigoCliente) {
                                    if (loginFuncionario != null) {
                                        if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                            servicos.add(s);
                                        }
                                    }
                                    else {
                                        servicos.add(s);
                                    }
                                }
                            }
                            else {
                                if (loginFuncionario != null) {
                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                        servicos.add(s);
                                    }
                                }
                                else {
                                    servicos.add(s);
                                }
                            }
                        }
                    }
                    else {
                        if (codigoCliente > 0) {
                            if (s.getCliente().getCodigo() == codigoCliente) {
                                if (loginFuncionario != null) {
                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                        servicos.add(s);
                                    }
                                }
                                else {
                                    servicos.add(s);
                                }
                            }
                        }
                        else {
                            if (loginFuncionario != null) {
                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                    servicos.add(s);
                                }
                            }
                            else {
                                servicos.add(s);
                            }
                        }
                    }
                }
            }
            else if (valorInicio >= 0) {
                servicosTemp = con.encontrarServico(dataInicio, dataFim);
                for (Servico s : servicosTemp) {
                    if ((s.getValor() >= valorInicio) && (s.getValor() <= valorFim)) {
                        if (codigoCliente > 0) {
                            if (s.getCliente().getCodigo() == codigoCliente) {
                                if (loginFuncionario != null) {
                                    if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                        servicos.add(s);
                                    }
                                }
                                else {
                                    servicos.add(s);
                                }
                            }
                        }
                        else {
                            if (loginFuncionario != null) {
                                if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                    servicos.add(s);
                                }
                            }
                            else {
                                servicos.add(s);
                            }
                        }
                    }
                }
            }
            else if (codigoCliente > 0) {
                servicosTemp = con.encontrarServicoCliente(codigoCliente);
                for (Servico s : servicosTemp) {
                    if (s.getCliente().getCodigo() == codigoCliente) {
                        if (loginFuncionario != null) {
                            if (s.getFuncionario().getLogin().equals(loginFuncionario)) {
                                servicos.add(s);
                            }
                        }
                        else {
                            servicos.add(s);
                        }
                    }
                }
            }
            else if (loginFuncionario != null) {
                servicos = con.encontrarServicoFuncionario(loginFuncionario);
            }
            
            Collections.sort(servicos);
        } catch (NullPointerException ex) {
            servicos = new ArrayList();
        }
    }
    
    /**
     * Método construtor que lista todos os serviços realizados nos 15 dias anteriores
     * ou marcados para os próximos 30 dias
     */
    public ServicoTableModel() {
        try {
            servicos = new ArrayList();
            Consulta con = new Consulta();
            
            Date hoje = new Date();
            Calendar cal = new GregorianCalendar();
            cal.setTime(hoje);
            cal.add(Calendar.DAY_OF_MONTH, -15);
            Date ultimos15Dias = cal.getTime();
            
            List<Servico> servicosTemp = con.encontrarServico(hoje, ultimos15Dias);
            
            cal.setTime(hoje);
            cal.add(Calendar.MONTH, 1);
            Date proximos30Dias = cal.getTime();
            
            servicos = con.encontrarServico(hoje, proximos30Dias);
            servicos.addAll(servicosTemp);
            Collections.sort(servicos);
        } catch (NullPointerException ex) {
            servicos = new ArrayList();
        }
    }
    
    /**
     * Método construtor para adicionar serviços à lista
     * @param s - lista de serviços que aparecerão na tabela
     */
    public ServicoTableModel(List<Servico> s){
        if (s == null) {
            servicos = new ArrayList();
        }
        else {
            servicos = s;
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
        try {
            switch (indiceColuna) {
            case 0: // Código
                return servicos.get(indiceLinha).getCodigo();
            case 1: // Estado
                return servicos.get(indiceLinha).getEstado();
            case 2: // Data
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                return sdf.format(servicos.get(indiceLinha).getData());
            case 3: // Nome do Serviço
                return servicos.get(indiceLinha).getNome();
            case 4: // Valor
                return "R$ " + String.format("%.2f", servicos.get(indiceLinha).getValor());
            case 5: // Nome do Cliente
                return servicos.get(indiceLinha).getCliente().getNome();
            case 6: // Nome do Funcionário
                return servicos.get(indiceLinha).getFuncionario().getNome();
            default:
                throw new IndexOutOfBoundsException();
            }
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
