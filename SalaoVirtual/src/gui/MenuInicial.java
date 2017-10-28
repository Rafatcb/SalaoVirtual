/*
 * Classe referente ao Menu Inicial
 */
package gui;

import exceptions.ChaveNulaException;
import exceptions.DataInvalidaException;
import exceptions.EstadoServicoInvalidoException;
import exceptions.TipoDeCartaoInvalidoException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import salaovirtual.Cartao;
import salaovirtual.Cliente;
import salaovirtual.Dinheiro;
import salaovirtual.Funcionario;
import salaovirtual.Produto;
import salaovirtual.Servico;
import salaovirtual.Venda;
import salaovirtual.access.Cadastro;
import salaovirtual.access.Consulta;

/**
 * Classe referente ao Menu Inicial
 *
 * @author Rafael Tavares
 */
public class MenuInicial extends javax.swing.JFrame {

    private List<Servico> listaServicoVenda;
    private List<Produto> listaProdutoVenda;
    private List<Integer> listaProdutoVendaQuantidade;
    private float valorTotalVenda;
    private Servico servicoVenda;
    private Produto produtoVenda;
    private boolean cadastro = false;
    private String mensagemDialog;
    private Funcionario funcionario;
    
    /**
     * Cria um novo JFrame Menu Inicial
     */
    public MenuInicial() {
        LoginModal login = new LoginModal(this, true);
        login.setVisible(true);
        if (!login.isLogado()) {  // this returns true only if the user logged in
            System.exit(0);
        }
        initComponents();
        this.setLocationRelativeTo(null);
        pnlSubMenu.setVisible(false);
        pnlSubMenu.setOpaque(false);
        pnlAgenda.setOpaque(false);
        pnlServico.setOpaque(true);
        pnlServico.setVisible(false);
        pnlVenda.setOpaque(true);
        pnlVenda.setVisible(false);
        pnlAgenda.setVisible(true);
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuAgenda_Hover.png"));
        txtPadrao.setVisible(false);
        btnMenuAgenda.setIcon(i);
        tblAgenda.setSelectionModel(new ForcedListSelectionModel());
        tblAgenda.setModel(new AgendaTableModel());
        gerarTabelaAgenda();
        tblServico.setSelectionModel(new ForcedListSelectionModel());
        tblServico.setModel(new ServicoTableModel());
        gerarTabelaServico();
        List<Servico> sl = null;
        gerarTabelaServicoVenda(sl);
        List<Produto> pl = null;
        List<Integer> il = null;
        gerarTabelaProdutoVenda(pl, il);
        jScrollPane1.setBorder(createEmptyBorder());
        jScrollPane1.setViewportBorder(null);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane2.setBorder(createEmptyBorder());
        jScrollPane2.setViewportBorder(null);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane3.setBorder(createEmptyBorder());
        jScrollPane3.setViewportBorder(null);
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane4.setBorder(createEmptyBorder());
        jScrollPane4.setViewportBorder(null);
        jScrollPane4.getViewport().setOpaque(false);
        txtProdutoVendaMarca.setDisabledTextColor(Color.black);
        txtProdutoVendaQtdUnitaria.setDisabledTextColor(Color.black);
        txtProdutoVendaValorTotal.setDisabledTextColor(Color.black);
        txtProdutoVendaValorUnitario.setDisabledTextColor(Color.black);
        txtProdutoVendaNome.setDisabledTextColor(Color.black);
        txtServicoVendaCliente.setDisabledTextColor(Color.black);
        txtServicoVendaData.setDisabledTextColor(Color.black);
        txtServicoVendaEstado.setDisabledTextColor(Color.black);
        txtServicoVendaFuncionario.setDisabledTextColor(Color.black);
        txtServicoVendaNome.setDisabledTextColor(Color.black);
        txtServicoVendaValor.setDisabledTextColor(Color.black);
        listaServicoVenda = new ArrayList();
        listaProdutoVenda = new ArrayList();
        listaProdutoVendaQuantidade = new ArrayList();
        valorTotalVenda = 0f;
        this.setFuncionario(login.getFuncionarioLogado());
        
    }
    
    /**
     * Método para facilitar a criação/formatação da tblProdutoVenda
     * @param p
     * @param q 
     */
    private void gerarTabelaProdutoVenda(List<Produto> p, List<Integer> q) {
        tblProdutoVenda.setModel(new ProdutoVendaTableModel(p, q));
        tblProdutoVenda.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
        tblProdutoVenda.getColumnModel().getColumn(0).setMaxWidth(65);
        tblProdutoVenda.getColumnModel().getColumn(3).setMaxWidth(110);
        tblProdutoVenda.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblProdutoVenda.getColumnModel().getColumn(4).setMaxWidth(110);
        tblProdutoVenda.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblProdutoVenda.getColumnModel().getColumn(5).setMaxWidth(110);
        tblProdutoVenda.getColumnModel().getColumn(5).setPreferredWidth(110);
        tblProdutoVenda.getColumnModel().getColumn(6).setMaxWidth(150);
        tblProdutoVenda.getColumnModel().getColumn(6).setPreferredWidth(150);
    }
    
    /**
     * Método para facilitar a criação/formatação da tblServicoVenda
     * @param s
     */
    private void gerarTabelaServicoVenda(List<Servico> s) {
        tblServicoVenda.setModel(new ServicoTableModel(s));
        tblServicoVenda.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
        tblServicoVenda.getColumnModel().getColumn(0).setMaxWidth(65);
        tblServicoVenda.getColumnModel().getColumn(1).setMaxWidth(130);
        tblServicoVenda.getColumnModel().getColumn(1).setPreferredWidth(130);
        tblServicoVenda.getColumnModel().getColumn(2).setMaxWidth(170);
        tblServicoVenda.getColumnModel().getColumn(2).setPreferredWidth(170);
        tblServicoVenda.getColumnModel().getColumn(4).setMaxWidth(100);
        tblServicoVenda.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
    
    /**
     * Método para facilitar a criação/formatação da tblServico
     */
    private void gerarTabelaServico() {
        tblServico.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
        tblServico.getColumnModel().getColumn(0).setMaxWidth(65);
        tblServico.getColumnModel().getColumn(1).setMaxWidth(130);
        tblServico.getColumnModel().getColumn(1).setPreferredWidth(130);
        tblServico.getColumnModel().getColumn(2).setMaxWidth(170);
        tblServico.getColumnModel().getColumn(2).setPreferredWidth(170);
        tblServico.getColumnModel().getColumn(4).setMaxWidth(100);
        tblServico.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
    
    /**
     * Método para facilitar a criação/formatação da tblAgenda
     */
    private void gerarTabelaAgenda() {
        tblAgenda.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
        tblAgenda.getColumnModel().getColumn(0).setMaxWidth(65);
        tblAgenda.getColumnModel().getColumn(1).setMaxWidth(170);
        tblAgenda.getColumnModel().getColumn(1).setPreferredWidth(170);
        tblAgenda.getColumnModel().getColumn(3).setMaxWidth(100);
        tblAgenda.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    /**
     * Retorna a mensagem para ser exibida no dialog
     * @return Mensagem para exibir no Dialog
     */
    public String getMensagemDialog() {
        return this.mensagemDialog;
    }
    
    /**
     * Define a mensagem a ser exibida no dialog
     * @param mensagem 
     */
    private void setMensagemDialog(String mensagem) {
        this.mensagemDialog = mensagem;
    }
    
    /**
     * Retorna se é cadastro ou consulta
     * @return Cadastro = true, Consulta = false
     */
    public boolean isCadastro() {
        return this.cadastro;
    }
    
    /**
     * Define se é cadastro = true, ou consulta = false
     * @param cadastro 
     */
    private void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    /**
     * Retorna o funcionário que logou no sistema
     * @return Mensagem para exibir no Dialog
     */
    public Funcionario getFuncionario() {
        return this.funcionario;
    }
    
    /**
     * Define o funcionário que logou no sistema
     * @param f 
     */
    private void setFuncionario(Funcionario f) {
        this.funcionario = f;
    }
    
    /**
     * Método para limpar todas jTextField relacionadas ao "ProdutoVenda"
     */
    private void limparProdutoVenda() {
        txtProdutoVendaCodigo.setText("");
        txtProdutoVendaMarca.setText("");
        txtProdutoVendaQtdUnitaria.setText("");
        txtProdutoVendaNome.setText("");
        txtProdutoVendaQuantidade.setText("");
        txtProdutoVendaValorTotal.setText("");
        txtProdutoVendaValorUnitario.setText("");
    }
    
    /**
     * Método para limpar todas jTextField relacionadas ao "ServicoVenda"
     */
    private void limparServicoVenda() {
        txtServicoVendaCliente.setText("");
        txtServicoVendaCodigo.setText("");
        txtServicoVendaData.setText("");
        txtServicoVendaEstado.setText("");
        txtServicoVendaFuncionario.setText("");
        txtServicoVendaNome.setText("");
        txtServicoVendaValor.setText("");
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMenuAgenda = new javax.swing.JButton();
        btnMenuConsulta = new javax.swing.JButton();
        btnMenuServico = new javax.swing.JButton();
        btnMenuVenda = new javax.swing.JButton();
        btnMenuCadastro = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pnlAgenda = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgenda = new javax.swing.JTable();
        btnAgendarServico = new javax.swing.JButton();
        btnCancelarServico = new javax.swing.JButton();
        pnlSubMenu = new javax.swing.JPanel();
        btnMenuFornecedor = new javax.swing.JButton();
        btnMenuFuncionario = new javax.swing.JButton();
        btnMenuFornecimento = new javax.swing.JButton();
        btnMenuProduto = new javax.swing.JButton();
        btnMenuCliente = new javax.swing.JButton();
        background = new javax.swing.JLabel();
        pnlServico = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblServico = new javax.swing.JTable();
        btnCadastrarServico = new javax.swing.JButton();
        btnConsultarServico = new javax.swing.JButton();
        txtNomeServico = new javax.swing.JTextField();
        chkNomeServico = new javax.swing.JCheckBox();
        chkValor = new javax.swing.JCheckBox();
        lblValorAte = new javax.swing.JLabel();
        chkData = new javax.swing.JCheckBox();
        lblDataAte = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        chkLoginFuncionario = new javax.swing.JCheckBox();
        txtLoginFuncionario = new javax.swing.JTextField();
        txtCodigoCliente = new javax.swing.JTextField();
        chkCodigoCliente = new javax.swing.JCheckBox();
        chkEstado = new javax.swing.JCheckBox();
        txtCodigoServico = new javax.swing.JTextField();
        chkCodigoServico = new javax.swing.JCheckBox();
        txtPadrao = new javax.swing.JTextField();
        ftxtDataFim = new javax.swing.JFormattedTextField();
        ftxtDataInicio = new javax.swing.JFormattedTextField();
        ftxtValorInicio = new javax.swing.JFormattedTextField();
        ftxtValorFim = new javax.swing.JFormattedTextField();
        pnlVenda = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProdutoVenda = new javax.swing.JTable();
        btnAdicionarProdutoVenda = new javax.swing.JButton();
        btnRemoverProdutoVenda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtProdutoVendaCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtProdutoVendaNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProdutoVendaMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtProdutoVendaQtdUnitaria = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtProdutoVendaValorUnitario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtProdutoVendaValorTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProdutoVendaQuantidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblServicoVenda = new javax.swing.JTable();
        txtServicoVendaCodigo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtServicoVendaNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtServicoVendaEstado = new javax.swing.JTextField();
        txtServicoVendaData = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtServicoVendaValor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtServicoVendaFuncionario = new javax.swing.JTextField();
        btnRemoverServicoVenda = new javax.swing.JButton();
        btnAdicionarSerivocVenda = new javax.swing.JButton();
        txtServicoVendaCliente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnFinalizarVenda = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lblValorTotalVenda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Salão Virtual");
        setResizable(false);

        btnMenuAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuAgenda.png"))); // NOI18N
        btnMenuAgenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuAgenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuAgendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuAgendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuAgendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuAgendaMouseReleased(evt);
            }
        });
        btnMenuAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuAgendaActionPerformed(evt);
            }
        });

        btnMenuConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuConsulta.png"))); // NOI18N
        btnMenuConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuConsultaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuConsultaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuConsultaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuConsultaMouseReleased(evt);
            }
        });
        btnMenuConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuConsultaActionPerformed(evt);
            }
        });

        btnMenuServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuServico.png"))); // NOI18N
        btnMenuServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuServicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuServicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuServicoMouseReleased(evt);
            }
        });
        btnMenuServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuServicoActionPerformed(evt);
            }
        });

        btnMenuVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuVenda.png"))); // NOI18N
        btnMenuVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuVendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuVendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuVendaMouseReleased(evt);
            }
        });
        btnMenuVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuVendaActionPerformed(evt);
            }
        });

        btnMenuCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuCadastro.png"))); // NOI18N
        btnMenuCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuCadastroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuCadastroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuCadastroMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuCadastroMouseReleased(evt);
            }
        });
        btnMenuCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuCadastroActionPerformed(evt);
            }
        });

        jSeparator1.setToolTipText("");

        pnlAgenda.setAutoscrolls(true);
        pnlAgenda.setPreferredSize(new java.awt.Dimension(1290, 645));

        jScrollPane1.setOpaque(false);

        tblAgenda.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        tblAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAgenda.setOpaque(false);
        jScrollPane1.setViewportView(tblAgenda);

        btnAgendarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agenda/botaoAgendarServico.png"))); // NOI18N
        btnAgendarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgendarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgendarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgendarServicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAgendarServicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAgendarServicoMouseReleased(evt);
            }
        });
        btnAgendarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarServicoActionPerformed(evt);
            }
        });

        btnCancelarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agenda/botaoCancelarServico.png"))); // NOI18N
        btnCancelarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarServicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelarServicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarServicoMouseReleased(evt);
            }
        });
        btnCancelarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAgendaLayout = new javax.swing.GroupLayout(pnlAgenda);
        pnlAgenda.setLayout(pnlAgendaLayout);
        pnlAgendaLayout.setHorizontalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAgendaLayout.createSequentialGroup()
                .addContainerGap(413, Short.MAX_VALUE)
                .addComponent(btnAgendarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(btnCancelarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(416, 416, 416))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAgendaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAgendaLayout.setVerticalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendaLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgendarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        pnlSubMenu.setAutoscrolls(true);
        pnlSubMenu.setPreferredSize(new java.awt.Dimension(1290, 645));

        btnMenuFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecedor.png"))); // NOI18N
        btnMenuFornecedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuFornecedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuFornecedorMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuFornecedorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuFornecedorMouseReleased(evt);
            }
        });

        btnMenuFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuFuncionario.png"))); // NOI18N
        btnMenuFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuFuncionarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuFuncionarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuFuncionarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuFuncionarioMouseReleased(evt);
            }
        });

        btnMenuFornecimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecimento.png"))); // NOI18N
        btnMenuFornecimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuFornecimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuFornecimentoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuFornecimentoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuFornecimentoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuFornecimentoMouseReleased(evt);
            }
        });

        btnMenuProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuProduto.png"))); // NOI18N
        btnMenuProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuProdutoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuProdutoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuProdutoMouseReleased(evt);
            }
        });

        btnMenuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/botaoMenuCliente.png"))); // NOI18N
        btnMenuCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMenuClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMenuClienteMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlSubMenuLayout = new javax.swing.GroupLayout(pnlSubMenu);
        pnlSubMenu.setLayout(pnlSubMenuLayout);
        pnlSubMenuLayout.setHorizontalGroup(
            pnlSubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubMenuLayout.createSequentialGroup()
                .addContainerGap(249, Short.MAX_VALUE)
                .addGroup(pnlSubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSubMenuLayout.createSequentialGroup()
                        .addComponent(btnMenuCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnMenuFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnMenuFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSubMenuLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(btnMenuProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnMenuFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        pnlSubMenuLayout.setVerticalGroup(
            pnlSubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubMenuLayout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addGroup(pnlSubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenuFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnMenuFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMenuCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(pnlSubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenuProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuFornecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background/background.jpg"))); // NOI18N

        pnlServico.setAutoscrolls(true);
        pnlServico.setPreferredSize(new java.awt.Dimension(1290, 645));

        jScrollPane2.setOpaque(false);

        tblServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        tblServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblServico.setToolTipText("");
        tblServico.setOpaque(false);
        jScrollPane2.setViewportView(tblServico);

        btnCadastrarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico.png"))); // NOI18N
        btnCadastrarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastrarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastrarServicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastrarServicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastrarServicoMouseReleased(evt);
            }
        });
        btnCadastrarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarServicoActionPerformed(evt);
            }
        });

        btnConsultarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande.png"))); // NOI18N
        btnConsultarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarServicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnConsultarServicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnConsultarServicoMouseReleased(evt);
            }
        });
        btnConsultarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarServicoActionPerformed(evt);
            }
        });

        txtNomeServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtNomeServico.setEnabled(false);

        chkNomeServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkNomeServico.setText("Nome do Serviço");
        chkNomeServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNomeServicoActionPerformed(evt);
            }
        });

        chkValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkValor.setText("Faixa de valor");
        chkValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkValorActionPerformed(evt);
            }
        });

        lblValorAte.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        lblValorAte.setText("até");
        lblValorAte.setEnabled(false);

        chkData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkData.setText("Faixa de data");
        chkData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDataActionPerformed(evt);
            }
        });

        lblDataAte.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        lblDataAte.setText("até");
        lblDataAte.setEnabled(false);

        cmbEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agendado", "Realizado", "Cancelado" }));
        cmbEstado.setEnabled(false);

        chkLoginFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkLoginFuncionario.setText("Login Funcionário");
        chkLoginFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLoginFuncionarioActionPerformed(evt);
            }
        });

        txtLoginFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtLoginFuncionario.setEnabled(false);

        txtCodigoCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCodigoCliente.setEnabled(false);

        chkCodigoCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkCodigoCliente.setText("Código Cliente");
        chkCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCodigoClienteActionPerformed(evt);
            }
        });

        chkEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkEstado.setText("Estado");
        chkEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstadoActionPerformed(evt);
            }
        });

        txtCodigoServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCodigoServico.setEnabled(false);

        chkCodigoServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkCodigoServico.setText("Código do Serviço");
        chkCodigoServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCodigoServicoActionPerformed(evt);
            }
        });

        txtPadrao.setEditable(false);

        ftxtDataFim.setEnabled(false);
        ftxtDataFim.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtDataFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtDataFimFocusGained(evt);
            }
        });

        ftxtDataInicio.setEnabled(false);
        ftxtDataInicio.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtDataInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtDataInicioFocusGained(evt);
            }
        });

        ftxtValorInicio.setEnabled(false);
        ftxtValorInicio.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtValorInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtValorInicioFocusGained(evt);
            }
        });

        ftxtValorFim.setEnabled(false);
        ftxtValorFim.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtValorFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtValorFimFocusGained(evt);
            }
        });

        javax.swing.GroupLayout pnlServicoLayout = new javax.swing.GroupLayout(pnlServico);
        pnlServico.setLayout(pnlServicoLayout);
        pnlServicoLayout.setHorizontalGroup(
            pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServicoLayout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(btnCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 390, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlServicoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2)
                .addGap(60, 60, 60))
            .addGroup(pnlServicoLayout.createSequentialGroup()
                .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlServicoLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCodigoServico)
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(txtCodigoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkLoginFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLoginFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCodigoCliente)
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlServicoLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(chkEstado)))
                        .addGap(44, 44, 44)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(chkNomeServico)))
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(ftxtValorInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValorAte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ftxtValorFim, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(chkValor)))
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(ftxtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDataAte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ftxtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlServicoLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(chkData)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlServicoLayout.setVerticalGroup(
            pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServicoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlServicoLayout.createSequentialGroup()
                        .addComponent(chkCodigoCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlServicoLayout.createSequentialGroup()
                        .addComponent(chkLoginFuncionario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoginFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlServicoLayout.createSequentialGroup()
                        .addComponent(chkCodigoServico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlServicoLayout.createSequentialGroup()
                            .addComponent(chkEstado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlServicoLayout.createSequentialGroup()
                            .addComponent(chkNomeServico)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlServicoLayout.createSequentialGroup()
                        .addComponent(chkData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDataAte)
                            .addComponent(ftxtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftxtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlServicoLayout.createSequentialGroup()
                        .addComponent(chkValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValorAte)
                            .addComponent(ftxtValorInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftxtValorFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(pnlServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadastrarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarServico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pnlVenda.setAutoscrolls(true);
        pnlVenda.setPreferredSize(new java.awt.Dimension(1290, 645));

        jScrollPane3.setOpaque(false);

        tblProdutoVenda.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        tblProdutoVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProdutoVenda.setOpaque(false);
        jScrollPane3.setViewportView(tblProdutoVenda);

        btnAdicionarProdutoVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add/botaoAddProduto.png"))); // NOI18N
        btnAdicionarProdutoVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarProdutoVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdicionarProdutoVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdicionarProdutoVendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdicionarProdutoVendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAdicionarProdutoVendaMouseReleased(evt);
            }
        });
        btnAdicionarProdutoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProdutoVendaActionPerformed(evt);
            }
        });

        btnRemoverProdutoVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto.png"))); // NOI18N
        btnRemoverProdutoVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoverProdutoVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoverProdutoVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoverProdutoVendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRemoverProdutoVendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRemoverProdutoVendaMouseReleased(evt);
            }
        });
        btnRemoverProdutoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverProdutoVendaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel1.setText("Código");

        txtProdutoVendaCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProdutoVendaCodigoFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel2.setText("Produtos");

        txtProdutoVendaNome.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaNome.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel3.setText("Nome");

        txtProdutoVendaMarca.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaMarca.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaMarca.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel4.setText("Marca");

        txtProdutoVendaQtdUnitaria.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaQtdUnitaria.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaQtdUnitaria.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel5.setText("Qtd. Unitária");

        txtProdutoVendaValorUnitario.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaValorUnitario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaValorUnitario.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel6.setText("Valor Unitário");

        txtProdutoVendaValorTotal.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaValorTotal.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaValorTotal.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel7.setText("Valor Total");

        txtProdutoVendaQuantidade.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdutoVendaQuantidadeKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel8.setText("Quantidade");

        jScrollPane4.setOpaque(false);

        tblServicoVenda.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        tblServicoVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblServicoVenda.setOpaque(false);
        jScrollPane4.setViewportView(tblServicoVenda);

        txtServicoVendaCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtServicoVendaCodigoFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel9.setText("Código");

        jLabel10.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel10.setText("Serviços");

        jLabel12.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel12.setText("Nome");

        txtServicoVendaNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaNome.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel13.setText("Estado");

        txtServicoVendaEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaEstado.setEnabled(false);

        txtServicoVendaData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaData.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel14.setText("Data");

        txtServicoVendaValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaValor.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel15.setText("Valor");

        jLabel16.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel16.setText("Funcionário");

        txtServicoVendaFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaFuncionario.setEnabled(false);

        btnRemoverServicoVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remover/botaoRemoverServico.png"))); // NOI18N
        btnRemoverServicoVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoverServicoVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoverServicoVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoverServicoVendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRemoverServicoVendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRemoverServicoVendaMouseReleased(evt);
            }
        });
        btnRemoverServicoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverServicoVendaActionPerformed(evt);
            }
        });

        btnAdicionarSerivocVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add/botaoAddServico.png"))); // NOI18N
        btnAdicionarSerivocVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarSerivocVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdicionarSerivocVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdicionarSerivocVendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdicionarSerivocVendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAdicionarSerivocVendaMouseReleased(evt);
            }
        });
        btnAdicionarSerivocVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarSerivocVendaActionPerformed(evt);
            }
        });

        txtServicoVendaCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaCliente.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel17.setText("Cliente");

        btnFinalizarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarVenda.png"))); // NOI18N
        btnFinalizarVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinalizarVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizarVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizarVendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFinalizarVendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnFinalizarVendaMouseReleased(evt);
            }
        });
        btnFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarVendaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel11.setText("Valor Total");

        lblValorTotalVenda.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        lblValorTotalVenda.setText("R$ 9999,99");

        javax.swing.GroupLayout pnlVendaLayout = new javax.swing.GroupLayout(pnlVenda);
        pnlVenda.setLayout(pnlVendaLayout);
        pnlVendaLayout.setHorizontalGroup(
            pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVendaLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVendaLayout.createSequentialGroup()
                        .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtServicoVendaCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtServicoVendaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtServicoVendaEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtServicoVendaData, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(txtServicoVendaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(txtServicoVendaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(txtServicoVendaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtProdutoVendaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtProdutoVendaQuantidade, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(35, 35, 35)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtProdutoVendaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtProdutoVendaQtdUnitaria, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(37, 37, 37)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtProdutoVendaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(pnlVendaLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txtProdutoVendaValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtProdutoVendaValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlVendaLayout.createSequentialGroup()
                        .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVendaLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addGroup(pnlVendaLayout.createSequentialGroup()
                                        .addGap(304, 304, 304)
                                        .addComponent(btnAdicionarProdutoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(124, 124, 124)
                                        .addComponent(btnRemoverProdutoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addContainerGap())
            .addGroup(pnlVendaLayout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(btnAdicionarSerivocVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(btnRemoverServicoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVendaLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(lblValorTotalVenda)))
                .addGap(30, 30, 30))
        );
        pnlVendaLayout.setVerticalGroup(
            pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVendaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProdutoVendaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVendaLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProdutoVendaQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlVendaLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtProdutoVendaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlVendaLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtProdutoVendaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlVendaLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtProdutoVendaQtdUnitaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlVendaLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(31, 31, 31))
                        .addGroup(pnlVendaLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtProdutoVendaValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProdutoVendaValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarProdutoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverProdutoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVendaLayout.createSequentialGroup()
                        .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServicoVendaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServicoVendaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServicoVendaEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServicoVendaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServicoVendaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtServicoVendaValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(29, 29, 29))
                            .addGroup(pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServicoVendaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVendaLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(5, 5, 5)))
                        .addGap(30, 30, 30)
                        .addGroup(pnlVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionarSerivocVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoverServicoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVendaLayout.createSequentialGroup()
                        .addComponent(lblValorTotalVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlSubMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 1286, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(28, Short.MAX_VALUE)
                        .addComponent(btnMenuAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMenuServico, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMenuVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMenuCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMenuConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlServico, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenuConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuServico, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(pnlSubMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(111, Short.MAX_VALUE)
                    .addComponent(pnlAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(pnlServico, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(113, Short.MAX_VALUE)
                    .addComponent(pnlVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuAgendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuAgendaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuAgenda_Hover.png"));
        btnMenuAgenda.setIcon(i);
    }//GEN-LAST:event_btnMenuAgendaMouseEntered

    private void btnMenuAgendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuAgendaMouseExited
        if (!pnlAgenda.isVisible()) {
            ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuAgenda.png"));
            btnMenuAgenda.setIcon(i);
        }
    }//GEN-LAST:event_btnMenuAgendaMouseExited

    private void btnMenuAgendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuAgendaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuAgenda_Pressed.png"));
        btnMenuAgenda.setIcon(i);
    }//GEN-LAST:event_btnMenuAgendaMousePressed

    private void btnMenuAgendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuAgendaMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuAgenda_Hover.png"));
        btnMenuAgenda.setIcon(i);
    }//GEN-LAST:event_btnMenuAgendaMouseReleased

    private void btnMenuServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuServicoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuServico_Hover.png"));
        btnMenuServico.setIcon(i);
    }//GEN-LAST:event_btnMenuServicoMouseEntered

    private void btnMenuServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuServicoMouseExited
        if (!pnlServico.isVisible()) {
            ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuServico.png"));
            btnMenuServico.setIcon(i);
        }
    }//GEN-LAST:event_btnMenuServicoMouseExited

    private void btnMenuServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuServicoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuServico_Pressed.png"));
        btnMenuServico.setIcon(i);
    }//GEN-LAST:event_btnMenuServicoMousePressed

    private void btnMenuServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuServicoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuServico_Hover.png"));
        btnMenuServico.setIcon(i);
    }//GEN-LAST:event_btnMenuServicoMouseReleased

    private void btnMenuVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuVendaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuVenda_Hover.png"));
        btnMenuVenda.setIcon(i);
    }//GEN-LAST:event_btnMenuVendaMouseEntered

    private void btnMenuVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuVendaMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuVenda.png"));
        btnMenuVenda.setIcon(i);
    }//GEN-LAST:event_btnMenuVendaMouseExited

    private void btnMenuVendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuVendaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuVenda_Pressed.png"));
        btnMenuVenda.setIcon(i);
    }//GEN-LAST:event_btnMenuVendaMousePressed

    private void btnMenuVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuVendaMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuVenda_Hover.png"));
        btnMenuVenda.setIcon(i);
    }//GEN-LAST:event_btnMenuVendaMouseReleased

    private void btnMenuCadastroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuCadastroMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCadastro_Hover.png"));
        btnMenuCadastro.setIcon(i);
    }//GEN-LAST:event_btnMenuCadastroMouseEntered

    private void btnMenuCadastroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuCadastroMouseExited
        if ((!pnlSubMenu.isVisible()) || (!this.isCadastro())) {
            ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCadastro.png"));
            btnMenuCadastro.setIcon(i);
        }
    }//GEN-LAST:event_btnMenuCadastroMouseExited

    private void btnMenuCadastroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuCadastroMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCadastro_Pressed.png"));
        btnMenuCadastro.setIcon(i);
    }//GEN-LAST:event_btnMenuCadastroMousePressed

    private void btnMenuCadastroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuCadastroMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCadastro_Hover.png"));
        btnMenuCadastro.setIcon(i);
    }//GEN-LAST:event_btnMenuCadastroMouseReleased

    private void btnMenuConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuConsultaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuConsulta_Hover.png"));
        btnMenuConsulta.setIcon(i);
    }//GEN-LAST:event_btnMenuConsultaMouseEntered

    private void btnMenuConsultaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuConsultaMouseExited
        if ((!pnlSubMenu.isVisible()) || (this.isCadastro())) {
            ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuConsulta.png"));
            btnMenuConsulta.setIcon(i);
        }
    }//GEN-LAST:event_btnMenuConsultaMouseExited

    private void btnMenuConsultaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuConsultaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuConsulta_Pressed.png"));
        btnMenuConsulta.setIcon(i);
    }//GEN-LAST:event_btnMenuConsultaMousePressed

    private void btnMenuConsultaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuConsultaMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuConsulta_Hover.png"));
        btnMenuConsulta.setIcon(i);
    }//GEN-LAST:event_btnMenuConsultaMouseReleased

    private void btnMenuFornecimentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecimentoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecimento_Hover.png"));
        btnMenuFornecimento.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecimentoMouseEntered

    private void btnMenuFornecimentoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecimentoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecimento.png"));
        btnMenuFornecimento.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecimentoMouseExited

    private void btnMenuFornecimentoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecimentoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecimento_Pressed.png"));
        btnMenuFornecimento.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecimentoMousePressed

    private void btnMenuFornecimentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecimentoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecimento_Hover.png"));
        btnMenuFornecimento.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecimentoMouseReleased

    private void btnMenuProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuProdutoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuProduto_Hover.png"));
        btnMenuProduto.setIcon(i);
    }//GEN-LAST:event_btnMenuProdutoMouseEntered

    private void btnMenuProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuProdutoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuProduto.png"));
        btnMenuProduto.setIcon(i);
    }//GEN-LAST:event_btnMenuProdutoMouseExited

    private void btnMenuProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuProdutoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuProduto_Pressed.png"));
        btnMenuProduto.setIcon(i);
    }//GEN-LAST:event_btnMenuProdutoMousePressed

    private void btnMenuProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuProdutoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuProduto_Hover.png"));
        btnMenuProduto.setIcon(i);
    }//GEN-LAST:event_btnMenuProdutoMouseReleased

    private void btnMenuFornecedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecedorMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecedor_Hover.png"));
        btnMenuFornecedor.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecedorMouseReleased

    private void btnMenuFornecedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecedorMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecedor_Pressed.png"));
        btnMenuFornecedor.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecedorMousePressed

    private void btnMenuFornecedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecedorMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecedor.png"));
        btnMenuFornecedor.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecedorMouseExited

    private void btnMenuFornecedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFornecedorMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFornecedor_Hover.png"));
        btnMenuFornecedor.setIcon(i);
    }//GEN-LAST:event_btnMenuFornecedorMouseEntered

    private void btnMenuClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuClienteMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCliente_Hover.png"));
        btnMenuCliente.setIcon(i);
    }//GEN-LAST:event_btnMenuClienteMouseReleased

    private void btnMenuClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuClienteMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCliente_Pressed.png"));
        btnMenuCliente.setIcon(i);
    }//GEN-LAST:event_btnMenuClienteMousePressed

    private void btnMenuClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuClienteMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCliente.png"));
        btnMenuCliente.setIcon(i);
    }//GEN-LAST:event_btnMenuClienteMouseExited

    private void btnMenuClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuClienteMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCliente_Hover.png"));
        btnMenuCliente.setIcon(i);
    }//GEN-LAST:event_btnMenuClienteMouseEntered

    private void btnMenuFuncionarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFuncionarioMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFuncionario_Hover.png"));
        btnMenuFuncionario.setIcon(i);
    }//GEN-LAST:event_btnMenuFuncionarioMouseReleased

    private void btnMenuFuncionarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFuncionarioMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFuncionario_Pressed.png"));
        btnMenuFuncionario.setIcon(i);
    }//GEN-LAST:event_btnMenuFuncionarioMousePressed

    private void btnMenuFuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFuncionarioMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFuncionario.png"));
        btnMenuFuncionario.setIcon(i);
    }//GEN-LAST:event_btnMenuFuncionarioMouseExited

    private void btnMenuFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuFuncionarioMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuFuncionario_Hover.png"));
        btnMenuFuncionario.setIcon(i);
    }//GEN-LAST:event_btnMenuFuncionarioMouseEntered

    private void btnMenuCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuCadastroActionPerformed
        pnlServico.setVisible(false);
        pnlAgenda.setVisible(false);
        pnlVenda.setVisible(false);
        pnlSubMenu.setVisible(true);
        this.setCadastro(true);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuAgendaMouseExited(me);
        btnMenuServicoMouseExited(me);
        btnMenuVendaMouseExited(me);
        btnMenuConsultaMouseExited(me);
    }//GEN-LAST:event_btnMenuCadastroActionPerformed

    private void btnMenuConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuConsultaActionPerformed
        pnlServico.setVisible(false);
        pnlAgenda.setVisible(false);
        txtPadrao.setVisible(false);
        pnlVenda.setVisible(false);    
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuAgendaMouseExited(me);
        btnMenuServicoMouseExited(me);
        btnMenuVendaMouseExited(me);
        btnMenuCadastroMouseExited(me);
        pnlSubMenu.setVisible(true);    
    }//GEN-LAST:event_btnMenuConsultaActionPerformed

    private void btnMenuVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuVendaActionPerformed
        pnlSubMenu.setVisible(false);
        pnlServico.setVisible(false);
        pnlAgenda.setVisible(false);
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuAgendaMouseExited(me);
        btnMenuServicoMouseExited(me);
        btnMenuConsultaMouseExited(me);
        btnMenuCadastroMouseExited(me);
        pnlVenda.setVisible(true);
    }//GEN-LAST:event_btnMenuVendaActionPerformed

    private void btnMenuServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuServicoActionPerformed
        pnlSubMenu.setVisible(false);
        pnlAgenda.setVisible(false);
        pnlVenda.setVisible(false);
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuAgendaMouseExited(me);
        btnMenuVendaMouseExited(me);
        btnMenuConsultaMouseExited(me);
        btnMenuCadastroMouseExited(me);
        pnlServico.setVisible(true);
    }//GEN-LAST:event_btnMenuServicoActionPerformed

    private void btnMenuAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAgendaActionPerformed
        pnlSubMenu.setVisible(false);
        pnlServico.setVisible(false);
        pnlVenda.setVisible(false);
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuServicoMouseExited(me);
        btnMenuVendaMouseExited(me);
        btnMenuConsultaMouseExited(me);
        btnMenuCadastroMouseExited(me);
        pnlAgenda.setVisible(true);
    }//GEN-LAST:event_btnMenuAgendaActionPerformed

    private void btnAgendarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendarServicoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAgendarServico_Hover.png"));
        btnAgendarServico.setIcon(i);
    }//GEN-LAST:event_btnAgendarServicoMouseEntered

    private void btnAgendarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendarServicoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAgendarServico.png"));
        btnAgendarServico.setIcon(i);
    }//GEN-LAST:event_btnAgendarServicoMouseExited

    private void btnAgendarServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendarServicoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAgendarServico_Pressed.png"));
        btnAgendarServico.setIcon(i);
    }//GEN-LAST:event_btnAgendarServicoMousePressed

    private void btnAgendarServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendarServicoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAgendarServico_Hover.png"));
        btnAgendarServico.setIcon(i);
    }//GEN-LAST:event_btnAgendarServicoMouseReleased

    private void btnAgendarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarServicoActionPerformed
        CadastroServicoModal cadastroServico = new CadastroServicoModal(this, true, this.getFuncionario());
        cadastroServico.setVisible(true);
        if (cadastroServico.isFinalizado()) {
            if (cadastroServico.isAgendado()) {
                this.setMensagemDialog("Serviço cadastrado como agendado");
                tblAgenda.setModel(new AgendaTableModel());
                gerarTabelaAgenda();
            }
            else {
                this.setMensagemDialog("Serviço cadastrado como realizado");
            }
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Cadastro efetuado");
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_btnAgendarServicoActionPerformed

    private void btnCancelarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarServicoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoCancelarServico_Hover.png"));
        btnCancelarServico.setIcon(i);
    }//GEN-LAST:event_btnCancelarServicoMouseEntered

    private void btnCancelarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarServicoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoCancelarServico.png"));
        btnCancelarServico.setIcon(i);
    }//GEN-LAST:event_btnCancelarServicoMouseExited

    private void btnCancelarServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarServicoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoCancelarServico_Pressed.png"));
        btnCancelarServico.setIcon(i);
    }//GEN-LAST:event_btnCancelarServicoMousePressed

    private void btnCancelarServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarServicoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoCancelarServico_Hover.png"));
        btnCancelarServico.setIcon(i);
    }//GEN-LAST:event_btnCancelarServicoMouseReleased

    private void btnCancelarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarServicoActionPerformed
        int linha = tblAgenda.getSelectedRow();
        if (linha == -1)
        {
            this.setMensagemDialog("Nenhum serviço foi selecionado");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Serviço não selecionado");
            dialog.setVisible(true);
        }
        else
        {
            // tem uma linha selecionada
        }
    }//GEN-LAST:event_btnCancelarServicoActionPerformed

    private void btnCadastrarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarServicoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Hover.png"));
        btnCadastrarServico.setIcon(i);
    }//GEN-LAST:event_btnCadastrarServicoMouseEntered

    private void btnCadastrarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarServicoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico.png"));
        btnCadastrarServico.setIcon(i);
    }//GEN-LAST:event_btnCadastrarServicoMouseExited

    private void btnCadastrarServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarServicoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Pressed.png"));
        btnCadastrarServico.setIcon(i);
    }//GEN-LAST:event_btnCadastrarServicoMousePressed

    private void btnCadastrarServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarServicoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Hover.png"));
        btnCadastrarServico.setIcon(i);
    }//GEN-LAST:event_btnCadastrarServicoMouseReleased

    private void btnCadastrarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarServicoActionPerformed
        CadastroServicoModal cadastroServico = new CadastroServicoModal(this, true, this.getFuncionario());
        cadastroServico.setVisible(true);
        if (cadastroServico.isFinalizado()) {
            if (cadastroServico.isAgendado()) {
                this.setMensagemDialog("Serviço cadastrado como agendado");
            }
            else {
                this.setMensagemDialog("Serviço cadastrado como realizado");
            }
            gerarTabelaServico();
            tblServico.setSelectionModel(new ForcedListSelectionModel());
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Cadastro efetuado");
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_btnCadastrarServicoActionPerformed

    private void btnConsultarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande_Hover.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMouseEntered

    private void btnConsultarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMouseExited

    private void btnConsultarServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande_Pressed.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMousePressed

    private void btnConsultarServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande_Hover.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMouseReleased

    private void btnConsultarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarServicoActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtPadrao.getBorder();
        int aux1 = 0, aux2 = 0;
        if (chkCodigoServico.isSelected()) {
            if (txtCodigoServico.getText().length() > 0) {  
                try {
                    txtCodigoServico.setBorder(bordaPadrao);
                    txtCodigoCliente.setBorder(bordaPadrao);
                    ftxtDataFim.setBorder(bordaPadrao);
                    ftxtDataInicio.setBorder(bordaPadrao);
                    txtLoginFuncionario.setBorder(bordaPadrao);
                    txtNomeServico.setBorder(bordaPadrao);
                    ftxtValorFim.setBorder(bordaPadrao);
                    ftxtValorInicio.setBorder(bordaPadrao);
                    int codigo = Integer.parseInt(txtCodigoServico.getText());
                    String string = null;
                    Date data = null;
                    gerarTabelaServico();
                    tblServico.setModel(new ServicoTableModel(codigo, string, data, data, string, -1f, -1f, -1, string));
                } catch (NumberFormatException ex) {
                    txtCodigoServico.setBorder(bordaVermelha);
                    this.setMensagemDialog("Insira um valor numérico válido");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Código inválido");
                    dialog.setVisible(true);
                }
            }
            else {
                txtCodigoServico.setBorder(bordaVermelha);
                this.setMensagemDialog("Preencha todos os campos");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
                dialog.setVisible(true);
            }
        }
        else {
            int codigoCliente = 0;
            String estado = null, nomeServico = null, loginFuncionario = null;
            float valorInicio = -1, valorFim = -1;
            Date dataInicio = null, dataFim = null;
            if (chkCodigoCliente.isSelected()) {
                if (txtCodigoCliente.getText().length() == 0){
                    txtCodigoCliente.setBorder(bordaVermelha);
                    aux1 = 1;
                }
                else {
                    try {
                        codigoCliente = Integer.parseInt(txtCodigoCliente.getText());
                        txtCodigoCliente.setBorder(bordaPadrao);
                    } catch (NumberFormatException e) {
                        txtCodigoCliente.setBorder(bordaVermelha);
                        aux2 = 1;
                    }
                }
            }
            if (chkData.isSelected()) {
                if (ftxtDataFim.getText().length() == 0) {
                    ftxtDataFim.setBorder(bordaVermelha);
                    aux1 = 1;
                }
                if (ftxtDataInicio.getText().length() == 0) {
                    ftxtDataInicio.setBorder(bordaVermelha);
                    aux1 = 1;
                } 
                else {
                    DateFormat format = new SimpleDateFormat("dd/MM/yy");
                    try {
                        dataInicio = format.parse(ftxtDataInicio.getText());
                        ftxtDataInicio.setBorder(bordaPadrao);
                        try {
                            dataFim = format.parse(ftxtDataFim.getText());
                            ftxtDataFim.setBorder(bordaPadrao);
                        } catch (ParseException e) {
                            aux2 = 1;
                            ftxtDataFim.setBorder(bordaVermelha);
                        }
                    } catch (ParseException ex) {
                        aux2 = 1;
                        ftxtDataInicio.setBorder(bordaVermelha);
                    }
                }
            }
            if (chkEstado.isSelected()) {
                estado = cmbEstado.getSelectedItem().toString();
            }
            if (chkLoginFuncionario.isSelected()) {
                if (txtLoginFuncionario.getText().length() == 0) {
                    txtLoginFuncionario.setBorder(bordaVermelha);
                    aux1 = 1;
                }
                else {
                    txtLoginFuncionario.setBorder(bordaPadrao);
                    loginFuncionario = txtLoginFuncionario.getText();
                }
            }
            if (chkNomeServico.isSelected()) {
                if (txtNomeServico.getText().length() == 0) {
                    txtNomeServico.setBorder(bordaVermelha);
                    aux1 = 1;
                }
                else {
                    txtNomeServico.setBorder(bordaPadrao);
                    nomeServico = txtNomeServico.getText();
                }
            }
            if (chkValor.isSelected()) {
                if (ftxtValorInicio.getText().length() == 0) {
                    ftxtValorInicio.setBorder(bordaVermelha);
                    aux1 = 1;
                }
                if (ftxtValorFim.getText().length() == 0) {
                    ftxtValorFim.setBorder(bordaVermelha);
                    aux1 = 1;
                } 
                else {
                    String texto = ftxtValorInicio.getText();
                    String[] valorTexto = texto.split(" ");
                    try{
                        valorInicio = Float.parseFloat(valorTexto[1]);
                        ftxtValorInicio.setBorder(bordaPadrao);
                        texto = ftxtValorFim.getText();
                        valorTexto = texto.split(" ");
                        try {
                            valorInicio = Float.parseFloat(valorTexto[1]);;
                            ftxtValorFim.setBorder(bordaPadrao);
                        } catch (NumberFormatException ex) {
                            aux2 = 1;
                            ftxtValorFim.setBorder(bordaVermelha);
                        }
                    } catch (NumberFormatException e) {
                        aux2 = 1;
                        ftxtValorInicio.setBorder(bordaVermelha);
                    }
                }
            }
            
            if (aux1 == 1) {
                this.setMensagemDialog("Preencha todos os campos");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
                dialog.setVisible(true);
            }
            else if (aux2 == 1) {
                this.setMensagemDialog("Insira um valor válido nos campos");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Valores inválidos");
                dialog.setVisible(true);
            }
            else {
                gerarTabelaServico();
                tblServico.setModel(new ServicoTableModel(0, estado, dataInicio, dataFim, nomeServico, valorInicio,
                        valorFim, codigoCliente, loginFuncionario));
            }
        }
        gerarTabelaServico();
        tblServico.setSelectionModel(new ForcedListSelectionModel());
    }//GEN-LAST:event_btnConsultarServicoActionPerformed

    private void chkNomeServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNomeServicoActionPerformed
        if (chkNomeServico.isSelected()) {
            txtNomeServico.setEnabled(true);
            chkCodigoServico.setSelected(false);
            txtCodigoServico.setEnabled(false);
        }
        else {
            txtNomeServico.setEnabled(false);
        }
    }//GEN-LAST:event_chkNomeServicoActionPerformed

    private void chkValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkValorActionPerformed
        if (chkValor.isSelected()) {
            ftxtValorFim.setEnabled(true);
            ftxtValorInicio.setEnabled(true);
            lblValorAte.setEnabled(true);
            chkCodigoServico.setSelected(false);
            txtCodigoServico.setEnabled(false);
        }
        else {
            ftxtValorFim.setEnabled(false);
            ftxtValorInicio.setEnabled(false);
            lblValorAte.setEnabled(false);
        }
    }//GEN-LAST:event_chkValorActionPerformed

    private void chkDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDataActionPerformed
        if (chkData.isSelected()) {
            ftxtDataFim.setEnabled(true);
            ftxtDataInicio.setEnabled(true);
            lblDataAte.setEnabled(true);
            chkCodigoServico.setSelected(false);
            txtCodigoServico.setEnabled(false);
        }
        else {
            ftxtDataFim.setEnabled(false);
            ftxtDataInicio.setEnabled(false);
            lblDataAte.setEnabled(false);
        }
    }//GEN-LAST:event_chkDataActionPerformed

    private void chkLoginFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLoginFuncionarioActionPerformed
        if (chkLoginFuncionario.isSelected()) {
            txtLoginFuncionario.setEnabled(true);
            chkCodigoServico.setSelected(false);
            txtCodigoServico.setEnabled(false);
        }
        else {
            txtLoginFuncionario.setEnabled(false);
        }
    }//GEN-LAST:event_chkLoginFuncionarioActionPerformed

    private void chkCodigoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCodigoClienteActionPerformed
        if (chkCodigoCliente.isSelected()) {
            txtCodigoCliente.setEnabled(true);
            chkCodigoServico.setSelected(false);
            txtCodigoServico.setEnabled(false);
        }
        else {
            txtCodigoCliente.setEnabled(false);
        }
    }//GEN-LAST:event_chkCodigoClienteActionPerformed

    private void chkEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEstadoActionPerformed
        if (chkEstado.isSelected()) {
            cmbEstado.setEnabled(true);
            chkCodigoServico.setSelected(false);
            txtCodigoServico.setEnabled(false);
        }
        else {
            cmbEstado.setEnabled(false);
        }
    }//GEN-LAST:event_chkEstadoActionPerformed

    private void chkCodigoServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCodigoServicoActionPerformed
        if (chkCodigoServico.isSelected()) {
            txtCodigoServico.setEnabled(true);
            chkCodigoCliente.setSelected(false);
            txtCodigoCliente.setEnabled(false);
            chkData.setSelected(false);
            ftxtDataFim.setEnabled(false);
            ftxtDataInicio.setEnabled(false);
            lblDataAte.setEnabled(false);
            chkEstado.setSelected(false);
            cmbEstado.setEnabled(false);
            chkLoginFuncionario.setSelected(false);
            txtLoginFuncionario.setEnabled(false); 
            chkNomeServico.setSelected(false);
            txtNomeServico.setEnabled(false);
            chkValor.setSelected(false);
            ftxtValorFim.setEnabled(false);
            ftxtValorInicio.setEnabled(false); 
            lblValorAte.setEnabled(false);
        }
        else {
            txtCodigoServico.setEnabled(false);
        }
    }//GEN-LAST:event_chkCodigoServicoActionPerformed

    private void ftxtDataFimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtDataFimFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##/##/##");
            mask.install(ftxtDataFim);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtDataFimFocusGained

    private void ftxtDataInicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtDataInicioFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##/##/##");
            mask.install(ftxtDataInicio);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtDataInicioFocusGained

    private void ftxtValorInicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtValorInicioFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("R$ ###.##");
            mask.install(ftxtValorInicio);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtValorInicioFocusGained

    private void ftxtValorFimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtValorFimFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("R$ ###.##");
            mask.install(ftxtValorFim);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtValorFimFocusGained

    private void btnAdicionarProdutoVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoVendaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto_Hover.png"));
        btnAdicionarProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarProdutoVendaMouseEntered

    private void btnAdicionarProdutoVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoVendaMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto.png"));
        btnAdicionarProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarProdutoVendaMouseExited

    private void btnAdicionarProdutoVendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoVendaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto_Pressed.png"));
        btnAdicionarProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarProdutoVendaMousePressed

    private void btnAdicionarProdutoVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoVendaMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto_Hover.png"));
        btnAdicionarProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarProdutoVendaMouseReleased

    private void btnAdicionarProdutoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoVendaActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtServicoVendaData.getBorder();
        int aux = 0;
        txtProdutoVendaCodigo.setBorder(bordaPadrao);
        txtProdutoVendaQuantidade.setBorder(bordaPadrao);
        if (txtProdutoVendaQuantidade.getText().length() == 0) {
            txtProdutoVendaQuantidade.setBorder(bordaVermelha);
            aux = 1;
        }
        if (txtProdutoVendaCodigo.getText().length() == 0) { 
            txtProdutoVendaCodigo.setBorder(bordaVermelha);
            aux = 1;
        }
        if (aux == 1) {
            this.setMensagemDialog("Preencha todos os campos");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
            dialog.setVisible(true);
        }
        else if (txtProdutoVendaNome.getText().length() == 0) {
            txtProdutoVendaCodigo.setBorder(bordaVermelha);
            this.setMensagemDialog("Informe um código válido para o produto");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe um código válido");
            dialog.setVisible(true);
        }
        else {
            if (txtProdutoVendaValorTotal.getText().length() == 0) {
                txtProdutoVendaQuantidade.setBorder(bordaVermelha);
                this.setMensagemDialog("Informe uma quantidade válida para o produto");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe uma quantidade válido");
                dialog.setVisible(true);
            }
            else {
                if (Integer.parseInt(txtProdutoVendaQuantidade.getText()) > produtoVenda.getQtdEstoque()) {
                    txtProdutoVendaQuantidade.setBorder(bordaVermelha);
                    this.setMensagemDialog("Quantidade informada (" + txtProdutoVendaQuantidade.getText() + 
                            ") superior ao estoque (" + produtoVenda.getQtdEstoque() + ")");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Quantidade insuficiente");
                    dialog.setVisible(true);
                }
                else {
                    aux = 0;
                    for (Produto p : listaProdutoVenda) {
                        if (p.getCodigo() == produtoVenda.getCodigo()) {
                            aux = 1;
                            txtProdutoVendaCodigo.setBorder(bordaVermelha);
                            this.setMensagemDialog("O produto já foi adicionado na venda");
                            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Produto já adicionado");
                            dialog.setVisible(true);
                            break;
                        }
                    }
                    if (aux == 0) {
                        txtProdutoVendaCodigo.setBorder(bordaPadrao);
                        txtProdutoVendaQuantidade.setBorder(bordaPadrao);
                        valorTotalVenda += Float.parseFloat(txtProdutoVendaValorTotal.getText());
                        lblValorTotalVenda.setText("R$ " + Float.toString(valorTotalVenda));
                        listaProdutoVenda.add(produtoVenda);
                        listaProdutoVendaQuantidade.add(Integer.parseInt(txtProdutoVendaQuantidade.getText()));
                        gerarTabelaProdutoVenda(listaProdutoVenda, listaProdutoVendaQuantidade);
                        limparProdutoVenda();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAdicionarProdutoVendaActionPerformed

    private void btnRemoverProdutoVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverProdutoVendaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto_Hover.png"));
        btnRemoverProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverProdutoVendaMouseEntered

    private void btnRemoverProdutoVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverProdutoVendaMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto.png"));
        btnRemoverProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverProdutoVendaMouseExited

    private void btnRemoverProdutoVendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverProdutoVendaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto_Pressed.png"));
        btnRemoverProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverProdutoVendaMousePressed

    private void btnRemoverProdutoVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverProdutoVendaMouseReleased
       ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto_Hover.png"));
        btnRemoverProdutoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverProdutoVendaMouseReleased

    private void btnRemoverProdutoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverProdutoVendaActionPerformed
        int linha = tblProdutoVenda.getSelectedRow();
        if (linha == -1) {
            this.setMensagemDialog("Nenhum produto foi selecionado");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Produto não selecionado");
            dialog.setVisible(true);
        }
        else
        {
            this.setMensagemDialog("Você deseja remover " + tblProdutoVenda.getModel().getValueAt(linha, 1) + "?");
            MensagemOkCancelModal dialog = new MensagemOkCancelModal(this, true, this.getMensagemDialog(), "Confirmar - Remover produto");
            dialog.setVisible(true);
            if (dialog.getConfirmado()) {
                float valorTotalProduto = (Float) tblProdutoVenda.getModel().getValueAt(linha, 6);
                valorTotalVenda -= valorTotalProduto;
                lblValorTotalVenda.setText("R$ " + valorTotalVenda);
                listaProdutoVenda.remove(linha);
                listaProdutoVendaQuantidade.remove(linha);
                gerarTabelaProdutoVenda(listaProdutoVenda, listaProdutoVendaQuantidade);
                this.setMensagemDialog("Produto removido com sucesso!");
                MensagemOkModal dialog2 = new MensagemOkModal(this, true, this.getMensagemDialog(), "Sucesso - Produto removido");
                dialog2.setVisible(true);
            }
        }

    }//GEN-LAST:event_btnRemoverProdutoVendaActionPerformed

    private void btnRemoverServicoVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverServicoVendaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverServico_Hover.png"));
        btnRemoverServicoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverServicoVendaMouseEntered

    private void btnRemoverServicoVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverServicoVendaMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverServico.png"));
        btnRemoverServicoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverServicoVendaMouseExited

    private void btnRemoverServicoVendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverServicoVendaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverServico_Pressed.png"));
        btnRemoverServicoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverServicoVendaMousePressed

    private void btnRemoverServicoVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverServicoVendaMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverServico_Hover.png"));
        btnRemoverServicoVenda.setIcon(i);
    }//GEN-LAST:event_btnRemoverServicoVendaMouseReleased

    private void btnRemoverServicoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverServicoVendaActionPerformed
        int linha = tblServicoVenda.getSelectedRow();
        if (linha == -1) {
            this.setMensagemDialog("Nenhum serviço foi selecionado");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Serviço não selecionado");
            dialog.setVisible(true);
        }
        else
        {
            this.setMensagemDialog("Você deseja remover " + tblServicoVenda.getModel().getValueAt(linha, 3) 
                    + " para " + tblServicoVenda.getModel().getValueAt(linha, 5) + "?");
            MensagemOkCancelModal dialog = new MensagemOkCancelModal(this, true, this.getMensagemDialog(), "Confirmar - Remover serviço");
            dialog.setVisible(true);
            if (dialog.getConfirmado()) {
                String texto = (String) tblServicoVenda.getModel().getValueAt(linha, 4);
                String[] valorTexto = texto.split(" ");
                Float valorServico = Float.parseFloat(valorTexto[1].replace(',', '.'));
                valorTotalVenda -= valorServico;
                lblValorTotalVenda.setText("R$ " + valorTotalVenda);
                listaServicoVenda.remove(linha);
                gerarTabelaServicoVenda(listaServicoVenda);
                this.setMensagemDialog("Serviço removido com sucesso!");
                MensagemOkModal dialog2 = new MensagemOkModal(this, true, this.getMensagemDialog(), "Sucesso - Produto removido");
                dialog2.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnRemoverServicoVendaActionPerformed

    private void btnAdicionarSerivocVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarSerivocVendaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddServico_Hover.png"));
        btnAdicionarSerivocVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarSerivocVendaMouseEntered

    private void btnAdicionarSerivocVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarSerivocVendaMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddServico.png"));
        btnAdicionarSerivocVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarSerivocVendaMouseExited

    private void btnAdicionarSerivocVendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarSerivocVendaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddServico_Pressed.png"));
        btnAdicionarSerivocVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarSerivocVendaMousePressed

    private void btnAdicionarSerivocVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarSerivocVendaMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddServico_Hover.png"));
        btnAdicionarSerivocVenda.setIcon(i);
    }//GEN-LAST:event_btnAdicionarSerivocVendaMouseReleased

    private void btnAdicionarSerivocVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarSerivocVendaActionPerformed
        int aux = 0;                                        
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtServicoVendaData.getBorder();
        if (txtServicoVendaNome.getText().length() > 0) { // Então pode buscar
            for (Servico s : listaServicoVenda) {
                if (s.getCodigo() == servicoVenda.getCodigo()) {
                    aux = 1;
                    txtServicoVendaCodigo.setBorder(bordaVermelha);
                    this.setMensagemDialog("O serviço já foi adicionado na venda");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Serviço já adicionado");
                    dialog.setVisible(true);
                    break;
                }
            }
            if (aux == 0) {
                txtServicoVendaCodigo.setBorder(bordaPadrao);
                listaServicoVenda.add(servicoVenda);
                gerarTabelaServicoVenda(listaServicoVenda);
                valorTotalVenda += servicoVenda.getValor();
                lblValorTotalVenda.setText("R$ " + Float.toString(valorTotalVenda));
                limparServicoVenda();
            }
        }
        else {
            txtServicoVendaCodigo.setBorder(bordaVermelha);
            if (txtServicoVendaCodigo.getText().length() > 0) { 
                this.setMensagemDialog("Informe um código válido para o serviço");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe um código válido");
                dialog.setVisible(true);
            }
            else {
                this.setMensagemDialog("Informe o código do serviço");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe o código");
                dialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnAdicionarSerivocVendaActionPerformed

    private void txtProdutoVendaCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProdutoVendaCodigoFocusLost
        Consulta con = new Consulta();
        try {
            produtoVenda = con.encontrarProduto(Integer.parseInt(txtProdutoVendaCodigo.getText()));
            txtProdutoVendaNome.setText(produtoVenda.getNome());
            txtProdutoVendaMarca.setText(produtoVenda.getMarca());
            txtProdutoVendaQtdUnitaria.setText(produtoVenda.getQtdUnitaria() + " " + produtoVenda.getUnidade());
            txtProdutoVendaValorUnitario.setText(Float.toString(produtoVenda.getValor()));
            try {
                float valorTotal;
                if (Float.parseFloat(txtProdutoVendaQuantidade.getText()) > 0) {
                    valorTotal = Float.parseFloat(txtProdutoVendaValorUnitario.getText())*Float.parseFloat(txtProdutoVendaQuantidade.getText());
                    txtProdutoVendaValorTotal.setText(Float.toString(valorTotal));
                }
                else {
                    txtProdutoVendaValorTotal.setText("");
                }
            } catch (NumberFormatException e) {
                txtProdutoVendaValorTotal.setText("");
            }
        } catch (NumberFormatException | NullPointerException e){
            limparProdutoVenda();
        }
    }//GEN-LAST:event_txtProdutoVendaCodigoFocusLost

    private void txtProdutoVendaQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdutoVendaQuantidadeKeyReleased
        try {
            float valorTotal;
            if (Float.parseFloat(txtProdutoVendaQuantidade.getText()) > 0) {
                valorTotal = Float.parseFloat(txtProdutoVendaValorUnitario.getText())*Float.parseFloat(txtProdutoVendaQuantidade.getText());
                txtProdutoVendaValorTotal.setText(Float.toString(valorTotal));
            }
            else {
                txtProdutoVendaValorTotal.setText("");
            }
        } catch (NumberFormatException e) {
            txtProdutoVendaValorTotal.setText("");
        }
    }//GEN-LAST:event_txtProdutoVendaQuantidadeKeyReleased

    private void txtServicoVendaCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtServicoVendaCodigoFocusLost
        Consulta con = new Consulta();
        try {
            servicoVenda = con.encontrarServico(Integer.parseInt(txtServicoVendaCodigo.getText()));
            txtServicoVendaNome.setText(servicoVenda.getNome());
            txtServicoVendaEstado.setText(servicoVenda.getEstado());
            txtServicoVendaCliente.setText(servicoVenda.getCliente().getNome());
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            txtServicoVendaData.setText(df.format(servicoVenda.getData()));
            txtServicoVendaFuncionario.setText(servicoVenda.getFuncionario().getNome());
            txtServicoVendaValor.setText(Float.toString(servicoVenda.getValor()));
        } catch (NumberFormatException | NullPointerException e){
            limparServicoVenda();
        }
    }//GEN-LAST:event_txtServicoVendaCodigoFocusLost

    private void btnFinalizarVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarVendaMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarVenda_Hover.png"));
        btnFinalizarVenda.setIcon(i);
    }//GEN-LAST:event_btnFinalizarVendaMouseEntered

    private void btnFinalizarVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarVendaMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarVenda.png"));
        btnFinalizarVenda.setIcon(i);
    }//GEN-LAST:event_btnFinalizarVendaMouseExited

    private void btnFinalizarVendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarVendaMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarVenda_Pressed.png"));
        btnFinalizarVenda.setIcon(i);
    }//GEN-LAST:event_btnFinalizarVendaMousePressed

    private void btnFinalizarVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarVendaMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarVenda_Hover.png"));
        btnFinalizarVenda.setIcon(i);
    }//GEN-LAST:event_btnFinalizarVendaMouseReleased

    private void btnFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVendaActionPerformed
        if (tblProdutoVenda.getRowCount() == 0) {
           if (tblServicoVenda.getRowCount() == 0) {
                this.setMensagemDialog("Nenhum produto ou serviço foi adicionado à venda");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Insira um produto ou serviço");
                dialog.setVisible(true);
           } 
        }
        else {
            FinalizarVendaModal modal = new FinalizarVendaModal(this, true, this.getFuncionario(), valorTotalVenda);
            modal.setVisible(true);
            if (modal.finalizada) {
                Venda v = new Venda(modal.getCliente(), modal.getFuncionario());
                if (modal.isPagoEmCartao()) {
                    v.setFormaPagamento(modal.getCartao());
                }
                else {
                    v.setFormaPagamento(modal.getDinheiro());
                }
                for (Servico s : listaServicoVenda) {
                    v.addServico(s);
                }
                int aux = 0, quantidade = 0;
                for (Produto p : listaProdutoVenda) {
                    quantidade = (int) tblProdutoVenda.getModel().getValueAt(aux, 5);
                    v.addProduto(p, quantidade);
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
                    // Aqui atualizaria o estoque do produto //
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
                    aux ++;
                }
                Cadastro cad = new Cadastro();
                cad.gravarVenda(v);
                limparProdutoVenda();
                limparServicoVenda();
                listaProdutoVenda = new ArrayList();
                listaProdutoVendaQuantidade = new ArrayList();
                listaServicoVenda = new ArrayList();
                gerarTabelaProdutoVenda(listaProdutoVenda, listaProdutoVendaQuantidade);
                gerarTabelaServicoVenda(listaServicoVenda);
                this.valorTotalVenda = 0f;
                lblValorTotalVenda.setText("R$ 0.0");
            }
        }
    }//GEN-LAST:event_btnFinalizarVendaActionPerformed

    /**
     * Método main do Menu Inicial
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuInicial().setVisible(true);
            }
        });
    }
    
    
    
    /**
     * Classe interna para um JDialog que exibe o Cadastro de Serviço
     * 
     * @author Rafael Tavares
     */ 
    public class CadastroServicoModal extends javax.swing.JDialog {
        private java.awt.Frame parent;
        private boolean agendado;
        private boolean finalizado;
        
        /**
         * Construtor da classe
         * @param parent
         * @param modal 
         */
        public CadastroServicoModal(java.awt.Frame parent, boolean modal, Funcionario f) {
            super(parent, modal);
            initComponents();
            this.getContentPane().setBackground(Color.PINK);
            this.setParent(parent);
            Consulta con = new Consulta();
            int codigo = con.getProxCodigo("Servico.csv");
            txtCodigoServico.setText(Integer.toString(codigo));
            this.setAgendado(false);
            this.setFinalizado(false);
            txtLoginFuncionario.setText(f.getLogin());
            this.setLocationRelativeTo(null);
        }

        /**
         * Define o pai do CadastroServicoModal
         * @param frame 
         */
        private void setParent(java.awt.Frame frame) {
            this.parent = frame;
        }
        
        /**
         * Retorna o pai do CadastroServicoModal
         * @return Pai
         */
        @Override
        public java.awt.Frame getParent() {
            return this.parent;
        }
        
        /**
         * Define se o Serviço foi agendado ou não
         * @param agendado 
         */
        private void setAgendado(boolean agendado) {
            this.agendado = agendado;
        }
        
        /**
         * Retorna true se o Serviço foi agendado, false se foi Realizado ou se não foi cadastrado
         * @return true se Agendado, false se Realizado ou se não foi cadastrado
         */
        public boolean isAgendado() {
            return this.agendado;
        }
        
        /**
         * Define se o Serviço foi finalizado ou cancelado - sem cadastro
         * @param agendado 
         */
        private void setFinalizado(boolean finalizado) {
            this.finalizado = finalizado;
        }
        
        /**
         * Retorna true se o Serviço foi finalizado, false se foi cancelado - sem cadastro
         * @return true se Agendado, false se Realizado ou se não foi cadastrado
         */
        public boolean isFinalizado() {
            return this.finalizado;
        }
        
        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            btnCadastrar = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();
            jLabel2 = new javax.swing.JLabel();
            txtNomeServico = new javax.swing.JTextField();
            txtCodigoCliente = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            btnConsultarCliente = new javax.swing.JButton();
            jLabel4 = new javax.swing.JLabel();
            txtCodigoServico = new javax.swing.JTextField();
            btnConsultarFuncionario = new javax.swing.JButton();
            txtLoginFuncionario = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            ftxtValor = new javax.swing.JFormattedTextField();
            ftxtData = new javax.swing.JFormattedTextField();
            jLabel7 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Cadastrar Serviço");
            setBackground(new java.awt.Color(249, 180, 209));

            btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico.png"))); // NOI18N
            btnCadastrar.setToolTipText("");
            btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnCadastrarMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnCadastrarMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnCadastrarMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnCadastrarMouseReleased(evt);
                }
            });
            btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCadastrarActionPerformed(evt);
                }
            });

            btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro.png"))); // NOI18N
            btnCancelar.setToolTipText("");
            btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnCancelarMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseReleased(evt);
                }
            });
            btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCancelarActionPerformed(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel2.setText("Nome do Serviço");

            txtNomeServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtNomeServico.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   btnCadastrar.doClick();
                }
            });

            txtCodigoCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtCodigoCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   btnCadastrar.doClick();
                }
            });

            jLabel3.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel3.setText("Código do Cliente");

            btnConsultarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"))); // NOI18N
            btnConsultarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnConsultarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMouseReleased(evt);
                }
            });
            btnConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnConsultarClienteActionPerformed(evt);
                }
            });

            jLabel4.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel4.setText("Código");

            jLabel4.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel4.setText("Código");

            txtCodigoServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtCodigoServico.setFocusable(false);

            btnConsultarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"))); // NOI18N
            btnConsultarFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnConsultarFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMouseReleased(evt);
                }
            });
            btnConsultarFuncionario.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnConsultarFuncionarioActionPerformed(evt);
                }
            });

            txtLoginFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtLoginFuncionario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   btnCadastrar.doClick();
                }
            });

            jLabel5.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel5.setText("Login do Funcionário");

            jLabel6.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel6.setText("Valor");

            //ftxtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
            ftxtValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            ftxtValor.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    ftxtValorFocusGained(evt);
                }
            });
            ftxtValor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   btnCadastrar.doClick();
                }
            });

            //ftxtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
            ftxtData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            ftxtData.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    ftxtDataFocusGained(evt);
                }
            });
            ftxtData.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   btnCadastrar.doClick();
                }
            });

            jLabel7.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel7.setText("Data");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnConsultarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ftxtData, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(180, 180, 180))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(245, 245, 245)
                            .addComponent(jLabel7))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtLoginFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(72, 72, 72))
                                        .addComponent(jLabel5))
                                    .addGap(38, 38, 38)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnConsultarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel3)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCodigoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(29, 29, 29)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(ftxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ftxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(txtNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ftxtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(btnConsultarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnConsultarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLoginFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28))
            );

            pack();
        }// </editor-fold>                        

        private void btnCadastrarMouseEntered(java.awt.event.MouseEvent evt) {                                          
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Hover.png"));
            btnCadastrar.setIcon(i);
        }                                         

        private void btnCadastrarMouseExited(java.awt.event.MouseEvent evt) {                                         
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico.png"));
            btnCadastrar.setIcon(i);
        }                                        

        private void btnCadastrarMousePressed(java.awt.event.MouseEvent evt) {                                          
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Pressed.png"));
            btnCadastrar.setIcon(i);
        }                                         

        private void btnCadastrarMouseReleased(java.awt.event.MouseEvent evt) {                                           
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Hover.png"));
            btnCadastrar.setIcon(i);
        }                                          

        private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {                                         
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro_Hover.png"));
            btnCancelar.setIcon(i);
        }                                        

        private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {                                        
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro.png"));
            btnCancelar.setIcon(i);
        }                                       

        private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {                                         
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro_Pressed.png"));
            btnCancelar.setIcon(i);
        }                                        

        private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {                                          
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro_Hover.png"));
            btnCancelar.setIcon(i);
        }                                         

        private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {  
            dispose();
        }                                           

        private void btnConsultarFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {                                                     
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
            btnConsultarFuncionario.setIcon(i);
        }                                                    

        private void btnConsultarFuncionarioMouseExited(java.awt.event.MouseEvent evt) {                                                    
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
            btnConsultarFuncionario.setIcon(i);
        }                                                   

        private void btnConsultarFuncionarioMousePressed(java.awt.event.MouseEvent evt) {                                                     
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
            btnConsultarFuncionario.setIcon(i);
        }                                                    

        private void btnConsultarFuncionarioMouseReleased(java.awt.event.MouseEvent evt) {                                                      
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
            btnConsultarFuncionario.setIcon(i);
        }                                                     

        private void btnConsultarClienteMouseEntered(java.awt.event.MouseEvent evt) {                                                 
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
            btnConsultarCliente.setIcon(i);
        }                                                

        private void btnConsultarClienteMouseExited(java.awt.event.MouseEvent evt) {                                                
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
            btnConsultarCliente.setIcon(i);
        }                                               

        private void btnConsultarClienteMousePressed(java.awt.event.MouseEvent evt) {                                                 
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
            btnConsultarCliente.setIcon(i);
        }                                                

        private void btnConsultarClienteMouseReleased(java.awt.event.MouseEvent evt) {                                                  
            ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
            btnConsultarCliente.setIcon(i);
        }                                                    

        private void btnConsultarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) { 
            // TODO add your handling code here:
        }                                                       

        private void btnConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                    
            // TODO add your handling code here:
        }                                                   

        private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {                                             
            Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
            Border bordaPadrao = txtCodigoServico.getBorder();
            int aux = 0;
            if (txtCodigoCliente.getText().equals("")) {
                txtCodigoCliente.setBorder(bordaVermelha);
                aux = 1;
            }
            else {
                txtCodigoCliente.setBorder(bordaPadrao);
            }
            if (txtLoginFuncionario.getText().equals("")) {
                txtLoginFuncionario.setBorder(bordaVermelha);
                aux = 1;
            }
            else {
                txtLoginFuncionario.setBorder(bordaPadrao);
            }
            if (txtNomeServico.getText().equals("")) {
                txtNomeServico.setBorder(bordaVermelha);
                aux = 1;
            }
            else {
                txtNomeServico.setBorder(bordaPadrao);
            }
            if (ftxtData.getText().equals("")) {
                ftxtData.setBorder(bordaVermelha);
                aux = 1;
            }
            else {
                ftxtData.setBorder(bordaPadrao);
            }
            if (ftxtValor.getText().equals("")) {
                ftxtValor.setBorder(bordaVermelha);
                aux = 1;
            }
            else {
                ftxtValor.setBorder(bordaPadrao);
            }
            if (aux == 1) {
                MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Preencha todos os campos para "
                        + "o cadastro", "Erro - Preencha os campos");
                m.setVisible(true);
            }
            else {
                Consulta con = new Consulta();
                Funcionario f = con.encontrarFuncionarioLogin(txtLoginFuncionario.getText());
                if (f == null) {
                    txtLoginFuncionario.setBorder(bordaVermelha);
                    MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Funcionário não encontrado, "
                            + "verifique o Login" + txtLoginFuncionario.getText() + ".", "Erro - Funcionário inválido");
                    m.setVisible(true);
                }
                else {
                    Cliente c = con.encontrarCliente(parseInt(txtCodigoCliente.getText()));
                    if (c == null) {
                        txtCodigoCliente.setBorder(bordaVermelha);
                        MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Cliente não encontrado, "
                                + "verifique o Código", "Erro - Cliente inválido");
                        m.setVisible(true);
                    }
                    else {
                        /* Aqui todos os dados foram preenchidos, Cliente e Funcionário são existentes */
                        try {
                            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
                            df.setLenient(false);
                            Date data = df.parse(ftxtData.getText());
                            
                            /*Calendar agoraCalendar = Calendar.getInstance();
                            agoraCalendar.set(Calendar.HOUR_OF_DAY, -1);
                            agoraCalendar.set(Calendar.MINUTE, -1);
                            agoraCalendar.set(Calendar.SECOND, -1);
                            
                            Date agora = agoraCalendar.getTime();*/
                            // Com o código comentado acima, não conseguia colocar as horas atuais
                            // Por isso usei métodos descontinuados
                            Date agora = new Date();
                            agora.getHours();
                            agora.getMinutes();
                            agora.getSeconds();
                            
                            String texto = ftxtValor.getText();
                            String[] valorTexto = texto.split(" ");
                            try{
                                Float valor = Float.parseFloat(valorTexto[1]);
                                Cadastro cad = new Cadastro();
                                Servico s = new Servico(txtNomeServico.getText());
                                s.setValor(valor);
                                try { // Então, agenda
                                    if (agora.before(data)) {   
                                            s.agendarServico(c, f, data);
                                            this.setAgendado(true);
                                    }
                                    else { // Não agenda, já aconteceu!
                                        s.setCliente(c);
                                        s.setFuncionario(f);
                                        s.setData(data);
                                        s.setEstado("Realizado");
                                    }
                                    this.setFinalizado(true);
                                    cad.gravarServico(s);
                                    dispose();
                                } catch (DataInvalidaException | EstadoServicoInvalidoException | ChaveNulaException ex) {
                                    //
                                }
                            } catch (NumberFormatException e) {
                                ftxtValor.setBorder(bordaVermelha);
                                MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Valor informado"
                                        + " inválido: " + ftxtValor.getText(), "Erro - Valor inválido");
                                m.setVisible(true);
                            }
                        } catch (ParseException ex) {
                            ftxtData.setBorder(bordaVermelha);
                            MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Data (" 
                                    + ftxtData.getText() + ") inválida", "Erro - Data inválida");
                            m.setVisible(true);
                        } 
                    }
                }
                
            }
        }                              

        private void ftxtDataFocusGained(java.awt.event.FocusEvent evt) {                                     
            MaskFormatter mask;
            try {
                mask = new MaskFormatter("##/##/## ##:##");
                mask.install(ftxtData);
            } catch (ParseException ex) {
                //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                                 

        private void ftxtValorFocusGained(java.awt.event.FocusEvent evt) {                                    
            MaskFormatter mask;
            try {
                mask = new MaskFormatter("R$ ###.##");
                mask.install(ftxtValor);
            } catch (ParseException ex) {
                //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    

        
        // Variables declaration - do not modify                     
        private javax.swing.JButton btnCadastrar;
        private javax.swing.JButton btnCancelar;
        private javax.swing.JButton btnConsultarCliente;
        private javax.swing.JButton btnConsultarFuncionario;
        private javax.swing.JFormattedTextField ftxtData;
        private javax.swing.JFormattedTextField ftxtValor;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JTextField txtCodigoCliente;
        private javax.swing.JTextField txtCodigoServico;
        private javax.swing.JTextField txtNomeServico;
        private javax.swing.JTextField txtLoginFuncionario;
        // End of variables declaration
    }
    
    
    
    /**
     * Classe interna para um JDialog que exibe uma mensagem e possui apenas o botão OK
     * 
     * @author Rafael Tavares
     */ 
    public class MensagemOkModal extends javax.swing.JDialog {
        
        
        /**
         * Construtor da classe
         * @param parent
         * @param modal 
         */
        public MensagemOkModal(java.awt.Frame parent, boolean modal, String mensagem, String titulo) {
            super(parent, modal);
            initComponents();
            this.getContentPane().setBackground(Color.PINK);
            lblMensagem.setText(mensagem);
            this.setTitle(titulo);
            this.setLocationRelativeTo(null);

        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {
            btnOk = new javax.swing.JButton();
            lblMensagem = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Erro");
            setResizable(false);

            btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"))); // NOI18N
            btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnOkMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnOkMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnOkMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnOkMouseReleased(evt);
                }
            });
            btnOk.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOkActionPerformed(evt);
                }
            });

            lblMensagem.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblMensagem.setText("Nenhuma linha foi selecionadaaaaaaaaaaaaa");
            lblMensagem.setToolTipText("");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGap(202, 202, 202)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(202, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>                        

        private void btnOkMouseEntered(java.awt.event.MouseEvent evt) {                                   
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
            btnOk.setIcon(i);
        }                                  

        private void btnOkMouseExited(java.awt.event.MouseEvent evt) {                                  
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"));
            btnOk.setIcon(i);
        }                                 

        private void btnOkMousePressed(java.awt.event.MouseEvent evt) {                                   
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Pressed.png"));
            btnOk.setIcon(i);
        }                                  

        private void btnOkMouseReleased(java.awt.event.MouseEvent evt) {                                    
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
            btnOk.setIcon(i);
        }                                   

        private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {                                      
            this.dispose();
        }                

        // Variables declaration - do not modify                     
        private javax.swing.JButton btnOk;
        private javax.swing.JLabel lblMensagem;
        // End of variables declaration                   
    }
    
    /**
    * Classe interna para um JDialog que lê o funcionário e cliente para finalizar a venda
    * 
    * @author Rafael Tavares
    */ 
   public class FinalizarVendaModal extends javax.swing.JDialog {

       private boolean finalizada;
       private boolean pagoEmCartao;
       private Cliente cliente;
       private Funcionario funcionario;
       private Cartao cartao;
       private Dinheiro dinheiro;
       private float valorTotal;
       private float valorOriginal;
       private java.awt.Frame parent;

       /**
        * Cria um novo JDialog para o FinalizarVendaModal
        * @param parent
        * @param modal
        * @param f - funcionário logado
        * @param v - valor da venda
        */
       public FinalizarVendaModal(java.awt.Frame parent, boolean modal, Funcionario f, float v) {
           super(parent, modal);
           initComponents();
           this.getContentPane().setBackground(Color.PINK);
           this.setLocationRelativeTo(null);
           this.setFinalizada(false);
           txtNomeFuncionario.setDisabledTextColor(Color.black);
           txtNomeCliente.setDisabledTextColor(Color.black);
           rdbGrupoPagamento.add(rdbCartao);
           rdbGrupoPagamento.add(rdbDinheiro);
           rdbCartao.setSelected(true);
           pnlDinheiro.setVisible(false);
           cartao = new Cartao();
           dinheiro = new Dinheiro();
           valorOriginal = v;
           try {
               cartao.setTipo("Débito");
                txtQtdParcelas.setEnabled(false);
               valorTotal = cartao.getTaxa() * valorOriginal;
           } catch (TipoDeCartaoInvalidoException ex) {
               //
           }
           this.txtLoginFuncionario.setText(f.getLogin());
           this.txtNomeFuncionario.setText(f.getNome());
           funcionario = f;
           lblValorTotal.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
           lblValorParcela.setText("de R$ " + String.format("%.2f", valorTotal));
       }

        /**
         * Define o pai do FinalizarVendaModal
         * @param frame 
         */
        private void setParent(java.awt.Frame frame) {
            this.parent = frame;
        }

        /**
         * Retorna o pai do FinalizarVendaModal
         * @return Pai do LoginModal
         */
        @Override
        public java.awt.Frame getParent() {
            return this.parent;
        }

       /**
        * Define true se a venda foi finalizada, false se foi cancelada
        * @param f 
        */
       private void setFinalizada(boolean f) {
           this.finalizada = f;
       }

       /**
        * Retorna true se a venda foi finalizada, false se foi cancelada
        * @return Finalizada - true, cancelada - false
        */
       private boolean isFinalizada() {
           return this.finalizada;
       }

       /**
        * Define true se foi pago em cartão, false em dinheiro
        * @param p
        */
       private void setPagoEmCartao(boolean p) {
           this.pagoEmCartao = p;
       }

       /**
        * Retorna true se foi pago em cartão, false em dinheiro
        * @return Pago em cartão - true, dinheiro - false
        */
       private boolean isPagoEmCartao() {
           return this.pagoEmCartao;
       }

       /**
        * Define o funcionário da venda
        * @param f - funcionário
        */
       private void setFuncionario(Funcionario f) {
           this.funcionario = f;
       }

       /**
        * Retorna o funcionário da venda
        * @return Funcionario que realizou a venda
        */
       public Funcionario getFuncionario() {
           return this.funcionario;
       }

       /**
        * Define o cliente da venda
        * @param c - cliente
        */
       private void setCliente(Cliente c) {
           this.cliente = c;
       }

       /**
        * Retorna o cliente da venda
        * @return Cliente que participou da venda
        */
       public Cliente getCliente() {
           return this.cliente;
       }

       /**
        * Define a forma de pagamento em cartão da venda
        * @param c - cartao
        */
       private void setCartao(Cartao c) {
           this.cartao = c;
       }

       /**
        * Retorna a forma de pagamento em cartão da venda
        * @return Cartao do pagamento
        */
       public Cartao getCartao() {
           return this.cartao;
       }

       /**
        * Define a forma de pagamento em dinheiro da venda
        * @param d - dinhero
        */
       private void setCartao(Dinheiro d) {
           this.dinheiro = d;
       }

       /**
        * Retorna a forma de pagamento em cartão da venda
        * @return Cartao do pagamento
        */
       public Dinheiro getDinheiro() {
           return this.dinheiro;
       }

       /**
        * Define o valor total da venda - pode ter mudado por causa das taxas do cartão
        * @param v - valor total da venda
        */
       private void setValorTotal(float v) {
           this.valorTotal = v;
       }

       /**
        * Retorna a forma de pagamento em cartão da venda
        * @return Cartao do pagamento
        */
       public float getValorTotal() {
           return this.valorTotal;
       }
        
        /**
         * Cálculo do valor da parcela do cartão de crédito
         */
        private void calcValorParcela() {
            try {
                int qtdParcelas = Integer.parseInt(txtQtdParcelas.getText());
                if (qtdParcelas >= 1) {
                    float valorParcela = cartao.getValorTotal()/(float) qtdParcelas;
                    lblValorParcela.setText("de R$ " + String.format("%.2f", valorParcela));
                }
                else {
                    lblValorParcela.setText("de R$ -");
                }
            } catch (NumberFormatException ex) {
                lblValorParcela.setText("de R$ -");
            }
        }

       /**
        * This method is called from within the constructor to initialize the form.
        * WARNING: Do NOT modify this code. The content of this method is always
        * regenerated by the Form Editor.
        */
       @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            rdbGrupoPagamento = new javax.swing.ButtonGroup();
            btnOk = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();
            jLabel3 = new javax.swing.JLabel();
            btnConsultarCliente = new javax.swing.JButton();
            btnConsultarFuncionario = new javax.swing.JButton();
            txtLoginFuncionario = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            txtCodigoCliente = new javax.swing.JTextField();
            jLabel6 = new javax.swing.JLabel();
            txtNomeFuncionario = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            txtNomeCliente = new javax.swing.JTextField();
            rdbDinheiro = new javax.swing.JRadioButton();
            rdbCartao = new javax.swing.JRadioButton();
            pnlDinheiro = new javax.swing.JPanel();
            jLabel12 = new javax.swing.JLabel();
            txtValorRecebido = new javax.swing.JTextField();
            lblTroco = new javax.swing.JLabel();
            pnlCartao = new javax.swing.JPanel();
            jLabel9 = new javax.swing.JLabel();
            cmbCartao = new javax.swing.JComboBox<>();
            txtQtdParcelas = new javax.swing.JTextField();
            jLabel13 = new javax.swing.JLabel();
            lblValorParcela = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            lblValorTotal = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Finalizar Venda");
            setBackground(new java.awt.Color(249, 180, 209));

            btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"))); // NOI18N
            btnOk.setToolTipText("");
            btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnOkMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnOkMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnOkMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnOkMouseReleased(evt);
                }
            });
            btnOk.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOkActionPerformed(evt);
                }
            });

            btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar.png"))); // NOI18N
            btnCancelar.setToolTipText("");
            btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnCancelarMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseReleased(evt);
                }
            });
            btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCancelarActionPerformed(evt);
                }
            });

            jLabel3.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel3.setText("Código do Cliente");

            btnConsultarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"))); // NOI18N
            btnConsultarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnConsultarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnConsultarClienteMouseReleased(evt);
                }
            });
            btnConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnConsultarClienteActionPerformed(evt);
                }
            });

            btnConsultarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"))); // NOI18N
            btnConsultarFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnConsultarFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnConsultarFuncionarioMouseReleased(evt);
                }
            });
            btnConsultarFuncionario.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnConsultarFuncionarioActionPerformed(evt);
                }
            });

            txtLoginFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N

            jLabel5.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel5.setText("Login do Funcionário");

            jLabel8.setFont(new java.awt.Font("Courier New", 0, 16)); // NOI18N
            jLabel8.setText("Informações adicionais da venda");

            txtCodigoCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N

            jLabel6.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel6.setText("Nome");

            txtNomeFuncionario.setFont(new java.awt.Font("Courier New", 1, 15)); // NOI18N
            txtNomeFuncionario.setEnabled(false);

            jLabel7.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel7.setText("Nome");

            txtNomeCliente.setFont(new java.awt.Font("Courier New", 1, 15)); // NOI18N
            txtNomeCliente.setEnabled(false);

            rdbDinheiro.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            rdbDinheiro.setText("Dinheiro");
            rdbDinheiro.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rdbDinheiroActionPerformed(evt);
                }
            });

            rdbCartao.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            rdbCartao.setText("Cartão");
            rdbCartao.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rdbCartaoActionPerformed(evt);
                }
            });

            pnlDinheiro.setOpaque(false);

            jLabel12.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel12.setText("Valor Recebido:");

            txtValorRecebido.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtValorRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtValorRecebidoKeyReleased(evt);
                }
            });

            lblTroco.setFont(new java.awt.Font("Courier New", 0, 16)); // NOI18N
            lblTroco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblTroco.setText("Troco: R$ -");

            javax.swing.GroupLayout pnlDinheiroLayout = new javax.swing.GroupLayout(pnlDinheiro);
            pnlDinheiro.setLayout(pnlDinheiroLayout);
            pnlDinheiroLayout.setHorizontalGroup(
                pnlDinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDinheiroLayout.createSequentialGroup()
                    .addContainerGap(29, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addGap(18, 18, 18)
                    .addComponent(txtValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27))
                .addGroup(pnlDinheiroLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            pnlDinheiroLayout.setVerticalGroup(
                pnlDinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDinheiroLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlDinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addComponent(lblTroco)
                    .addContainerGap())
            );

            pnlCartao.setOpaque(false);

            jLabel9.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel9.setText("Pagamento");

            cmbCartao.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            cmbCartao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Débito", "Crédito" }));
            cmbCartao.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cmbCartaoActionPerformed(evt);
                }
            });

            txtQtdParcelas.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtQtdParcelas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtQtdParcelas.setText("1");
            txtQtdParcelas.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtQtdParcelasKeyReleased(evt);
                }
            });

            jLabel13.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel13.setText("Parcelas");

            lblValorParcela.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            lblValorParcela.setText("de R$ -");

            javax.swing.GroupLayout pnlCartaoLayout = new javax.swing.GroupLayout(pnlCartao);
            pnlCartao.setLayout(pnlCartaoLayout);
            pnlCartaoLayout.setHorizontalGroup(
                pnlCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCartaoLayout.createSequentialGroup()
                    .addContainerGap(71, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(18, 18, 18)
                    .addComponent(cmbCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50))
                .addGroup(pnlCartaoLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(jLabel13)
                    .addGap(18, 18, 18)
                    .addComponent(txtQtdParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lblValorParcela)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            pnlCartaoLayout.setVerticalGroup(
                pnlCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCartaoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cmbCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQtdParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValorParcela))
                        .addComponent(jLabel13))
                    .addContainerGap(16, Short.MAX_VALUE))
            );

            jLabel10.setFont(new java.awt.Font("Courier New", 0, 16)); // NOI18N
            jLabel10.setText("Pagamento");

            lblValorTotal.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
            lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblValorTotal.setText("Valor total: R$ ");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(jLabel5)
                            .addGap(42, 42, 42)
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(txtLoginFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addComponent(btnConsultarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(btnConsultarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jLabel6)
                            .addGap(208, 208, 208)
                            .addComponent(jLabel7))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(200, 200, 200)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(136, 136, 136)
                            .addComponent(rdbCartao)
                            .addGap(70, 70, 70)
                            .addComponent(rdbDinheiro))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pnlDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(pnlCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(22, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel8)
                    .addGap(15, 15, 15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel3))
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtLoginFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConsultarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConsultarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addComponent(jLabel10)
                    .addGap(9, 9, 9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rdbCartao)
                        .addComponent(rdbDinheiro))
                    .addGap(1, 1, 1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(pnlCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(12, 12, 12)
                    .addComponent(lblValorTotal)
                    .addGap(9, 9, 9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>                   

       private void btnOkMouseEntered(java.awt.event.MouseEvent evt) {                                   
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
           btnOk.setIcon(i);
       }                                  

       private void btnOkMouseExited(java.awt.event.MouseEvent evt) {                                  
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"));
           btnOk.setIcon(i);
       }                                 

       private void btnOkMousePressed(java.awt.event.MouseEvent evt) {                                   
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Pressed.png"));
           btnOk.setIcon(i);
       }                                  

       private void btnOkMouseReleased(java.awt.event.MouseEvent evt) {                                    
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
           btnOk.setIcon(i);
       }                                   

       private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Hover.png"));
           btnCancelar.setIcon(i);
       }                                        

       private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {                                        
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar.png"));
           btnCancelar.setIcon(i);
       }                                       

       private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Pressed.png"));
           btnCancelar.setIcon(i);
       }                                        

       private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Hover.png"));
           btnCancelar.setIcon(i);
       }                                         

       private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
           this.setFinalizada(false);
           cliente = null;
           funcionario = null;
           this.dispose();
       }                                           

       private void btnConsultarFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {                                                     
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
           btnConsultarFuncionario.setIcon(i);
       }                                                    

       private void btnConsultarFuncionarioMouseExited(java.awt.event.MouseEvent evt) {                                                    
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
           btnConsultarFuncionario.setIcon(i);
       }                                                   

       private void btnConsultarFuncionarioMousePressed(java.awt.event.MouseEvent evt) {                                                     
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
           btnConsultarFuncionario.setIcon(i);
       }                                                    

       private void btnConsultarFuncionarioMouseReleased(java.awt.event.MouseEvent evt) {                                                      
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
           btnConsultarFuncionario.setIcon(i);
       }                                                     

       private void btnConsultarClienteMouseEntered(java.awt.event.MouseEvent evt) {                                                 
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
           btnConsultarCliente.setIcon(i);
       }                                                

       private void btnConsultarClienteMouseExited(java.awt.event.MouseEvent evt) {                                                
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
           btnConsultarCliente.setIcon(i);
       }                                               

       private void btnConsultarClienteMousePressed(java.awt.event.MouseEvent evt) {                                                 
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
           btnConsultarCliente.setIcon(i);
       }                                                

       private void btnConsultarClienteMouseReleased(java.awt.event.MouseEvent evt) {                                                  
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
           btnConsultarCliente.setIcon(i);
       }                                                 

       private void btnConsultarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {                                                        
           // TODO add your handling code here:
       }                                                       

       private void btnConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                    
           // TODO add your handling code here:
       }                                                   

       private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {                                       
           Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
           Border bordaPadrao = txtPadrao.getBorder();                                   
           this.setFinalizada(true);
           funcionario = null;
           cliente = null;
           txtNomeFuncionario.setText("");
           txtNomeCliente.setText("");
           Consulta con = new Consulta();
           if (txtLoginFuncionario.getText().length() != 0) {
               this.setFuncionario(con.encontrarFuncionarioLogin(txtLoginFuncionario.getText()));
               if (funcionario == null) {
                    txtLoginFuncionario.setBorder(bordaVermelha);
                    MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe um login de funcionário válido",
                            "Erro - Login inválido");
                    m.setVisible(true);
                   return;
               }
               txtNomeFuncionario.setText(this.getFuncionario().getNome());
           }
           if (txtCodigoCliente.getText().length() != 0) {
               try{
                   this.setCliente(con.encontrarCliente(Integer.parseInt(txtCodigoCliente.getText())));
                   if (cliente == null) {
                        txtCodigoCliente.setBorder(bordaVermelha);
                        MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe um código de cliente válido",
                                "Erro - Código inválido");
                        m.setVisible(true);
                       return;
                   }
                   txtNomeCliente.setText(this.getCliente().getNome());
               } catch (NumberFormatException e) {
                    txtCodigoCliente.setBorder(bordaVermelha);
                    MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe um código de cliente válido",
                            "Erro - Código inválido");
                    m.setVisible(true);
                    return;
               }
           }
           txtCodigoCliente.setBorder(bordaPadrao);
           txtLoginFuncionario.setBorder(bordaPadrao);
           if (rdbCartao.isSelected()) {
               if (txtQtdParcelas.getText().length() > 0) {
                   try {
                        int qtdParcelas = Integer.parseInt(txtQtdParcelas.getText());
                        if (qtdParcelas >= 1) {
                            txtQtdParcelas.setBorder(bordaPadrao);
                            float valorParcela = cartao.getValorTotal()/(float) qtdParcelas;
                            cartao.setIdentificador(0);
                            cartao.setQtdParcelas(qtdParcelas);
                             this.setPagoEmCartao(true);
                             dispose();
                        }
                        else {
                            txtQtdParcelas.setBorder(bordaVermelha);
                            MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe uma quantidade de parcelas maior ou igual à um", 
                                    "Erro - Quantidade de parcelas inválida");
                            m.setVisible(true);
                        }
                    } catch (NumberFormatException ex) {
                        txtQtdParcelas.setBorder(bordaVermelha);
                        MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe uma quantidade de parcelas válida", 
                                "Erro - Quantidade de parcelas inválida");
                        m.setVisible(true);
                    }
               }
               else {
                    txtQtdParcelas.setBorder(bordaVermelha);
                    MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe a quantidade de parcelas", 
                            "Erro - Quantidade de parcelas não informada");
                    m.setVisible(true);
               }
           }
           else {
               if (txtValorRecebido.getText().length() > 0) {
                    try {
                         float valorRecebido = Float.parseFloat(txtValorRecebido.getText());
                         dinheiro.setValorTotal(valorTotal);
                         dinheiro.setValorRecebido(valorRecebido);
                         if (dinheiro.calcularTroco() >= 0) {
                             txtValorRecebido.setBorder(bordaPadrao);
                             dinheiro.setIdentificador(1);
                             this.setPagoEmCartao(false);
                             dispose();
                         }
                         else {
                             txtValorRecebido.setBorder(bordaVermelha);
                             MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "O valor recebido é menor que o "
                                     + "valor total da venda", "Erro - Valor recebido insuficiente");
                             m.setVisible(true);
                         }
                     } catch (NumberFormatException ex) {
                         txtValorRecebido.setBorder(bordaVermelha);
                         MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe um valor recebido válido, somente números", 
                                 "Erro - Valor recebido inválido");
                         m.setVisible(true);
                     }
               }
               else {
                    txtValorRecebido.setBorder(bordaVermelha);
                    MensagemOkModal m = new MensagemOkModal(this.getParent(), true, "Informe o valor recebido", 
                            "Erro - Valor recebido não informado");
                    m.setVisible(true);
               }
           }
       }                                     

       private void txtValorRecebidoKeyReleased(java.awt.event.KeyEvent evt) {                                             
           try {
               float valorRecebido = Float.parseFloat(txtValorRecebido.getText());
               dinheiro.setValorTotal(valorOriginal);
               dinheiro.setValorRecebido(valorRecebido);
               if (dinheiro.calcularTroco() >= 0) {
                   lblTroco.setText("Troco: R$ " + String.format("%.2f", dinheiro.calcularTroco()));
               }
               else {
                   lblTroco.setText("Troco: R$ -");
               }
           } catch (NumberFormatException ex) {
               lblTroco.setText("Troco: R$ -");
           }
       }                                            

       private void txtQtdParcelasKeyReleased(java.awt.event.KeyEvent evt) {                                           
           calcValorParcela();
       }                                          

       private void cmbCartaoActionPerformed(java.awt.event.ActionEvent evt) {                                          
           String tipo = (String) cmbCartao.getSelectedItem();
           try {
               cartao.setTipo(tipo);
               if ("Débito".equals(tipo)) {
                    txtQtdParcelas.setEnabled(false);
                    txtQtdParcelas.setText("1");
               }
               else {
                   txtQtdParcelas.setEnabled(true);
               }
               cartao.setValorTotal(valorOriginal * cartao.getTaxa());
               lblValorTotal.setText("Valor Total: R$ " + String.format("%.2f", cartao.getValorTotal()));                                       
               calcValorParcela();
           } catch (TipoDeCartaoInvalidoException ex) {
               //
           }
       }       
       
       private void rdbCartaoActionPerformed(java.awt.event.ActionEvent evt) {                                          
           String tipo = (String) cmbCartao.getSelectedItem();
           pnlDinheiro.setVisible(false);
           pnlCartao.setVisible(true);
           try {
               if ("Débito".equals(tipo)) {
                    txtQtdParcelas.setEnabled(false);
                    txtQtdParcelas.setText("1");
               }
               else {
                   txtQtdParcelas.setEnabled(true);
               }
               cartao.setTipo(tipo);
               cartao.setValorTotal(valorOriginal * cartao.getTaxa());
               lblValorTotal.setText("Valor Total: R$ " + String.format("%.2f", cartao.getValorTotal()));
               calcValorParcela();
           } catch (TipoDeCartaoInvalidoException ex) {
               //
           }
       }                                         

       private void rdbDinheiroActionPerformed(java.awt.event.ActionEvent evt) {  
           pnlCartao.setVisible(false);                                        
           pnlDinheiro.setVisible(true);  
           lblValorTotal.setText("Valor Total: R$ " + String.format("%.2f", valorOriginal));
       }   

        // Variables declaration - do not modify                     
        private javax.swing.JButton btnCancelar;
        private javax.swing.JButton btnConsultarCliente;
        private javax.swing.JButton btnConsultarFuncionario;
        private javax.swing.JButton btnOk;
        private javax.swing.JComboBox<String> cmbCartao;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JLabel lblTroco;
        private javax.swing.JLabel lblValorParcela;
        private javax.swing.JLabel lblValorTotal;
        private javax.swing.JPanel pnlCartao;
        private javax.swing.JPanel pnlDinheiro;
        private javax.swing.JRadioButton rdbCartao;
        private javax.swing.JRadioButton rdbDinheiro;
        private javax.swing.ButtonGroup rdbGrupoPagamento;
        private javax.swing.JTextField txtCodigoCliente;
        private javax.swing.JTextField txtLoginFuncionario;
        private javax.swing.JTextField txtNomeCliente;
        private javax.swing.JTextField txtNomeFuncionario;
        private javax.swing.JTextField txtQtdParcelas;
        private javax.swing.JTextField txtValorRecebido;
        // End of variables declaration                               
    }
    
    
    /**
    * Classe interna para um JDialog que exibe uma mensagem e possui apenas o botão OK
    * 
    * @author Rafael Tavares
    */ 
   public class MensagemOkCancelModal extends javax.swing.JDialog {
       private boolean confirmado;


       /**
       * Construtor da classe
       * @param parent
       * @param modal 
       */   
       public MensagemOkCancelModal(java.awt.Frame parent, boolean modal, String mensagem, String titulo) {
           super(parent, modal);
           initComponents();
           this.setLocationRelativeTo(null);
           confirmado = false;
           this.setTitle(titulo);
           this.getContentPane().setBackground(Color.PINK);
           lblMensagem.setText(mensagem);
       }

       /**
        * Define se o JDialog foi confirmado ou não
        * @param c 
        */
       private void setConfirmado(boolean c) {
           this.confirmado = c;
       }

       /**
        * Retorna se o JDialog foi confirmado - true
        * @return Confirmado - true, cancelado - false
        */
       public boolean getConfirmado() {
           return this.confirmado;
       }

       /**
        * This method is called from within the constructor to initialize the form.
        * WARNING: Do NOT modify this code. The content of this method is always
        * regenerated by the Form Editor.
        */
       @SuppressWarnings("unchecked")
       // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
       private void initComponents() {

           btnOk = new javax.swing.JButton();
           lblMensagem = new javax.swing.JLabel();
           btnCancelar = new javax.swing.JButton();

           setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
           setTitle("Erro");
           setResizable(false);

           btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"))); // NOI18N
           btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
           btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                   btnOkMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                   btnOkMouseExited(evt);
               }
               public void mousePressed(java.awt.event.MouseEvent evt) {
                   btnOkMousePressed(evt);
               }
               public void mouseReleased(java.awt.event.MouseEvent evt) {
                   btnOkMouseReleased(evt);
               }
           });
           btnOk.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   btnOkActionPerformed(evt);
               }
           });

           lblMensagem.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
           lblMensagem.setText("Nenhuma linha foi selecionadaaaaaaaaaaaaa");
           lblMensagem.setToolTipText("");

           btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar.png"))); // NOI18N
           btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
           btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                   btnCancelarMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                   btnCancelarMouseExited(evt);
               }
               public void mousePressed(java.awt.event.MouseEvent evt) {
                   btnCancelarMousePressed(evt);
               }
               public void mouseReleased(java.awt.event.MouseEvent evt) {
                   btnCancelarMouseReleased(evt);
               }
           });
           btnCancelar.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   btnCancelarActionPerformed(evt);
               }
           });

           javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
           getContentPane().setLayout(layout);
           layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addContainerGap()
                   .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                   .addContainerGap())
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                   .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(44, 44, 44)
                   .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(101, 101, 101))
           );
           layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addGap(41, 41, 41)
                   .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addGap(44, 44, 44))
           );

           pack();
       }// </editor-fold>                        

       private void btnOkMouseEntered(java.awt.event.MouseEvent evt) {                                   
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
           btnOk.setIcon(i);
       }                                  

       private void btnOkMouseExited(java.awt.event.MouseEvent evt) {                                  
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"));
           btnOk.setIcon(i);
       }                                 

       private void btnOkMousePressed(java.awt.event.MouseEvent evt) {                                   
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Pressed.png"));
           btnOk.setIcon(i);
       }                                  

       private void btnOkMouseReleased(java.awt.event.MouseEvent evt) {                                    
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
           btnOk.setIcon(i);
       }                                   

       private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {                                      
           this.setConfirmado(true);
           this.dispose();
       }                                     

       private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Hover.png"));
           btnCancelar.setIcon(i);
       }                                        

       private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {                                        
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar.png"));
           btnCancelar.setIcon(i);
       }                                       

       private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Pressed.png"));
           btnCancelar.setIcon(i);
       }                                        

       private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Hover.png"));
           btnCancelar.setIcon(i);
       }                                         

       private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
           this.setConfirmado(false);
           this.dispose();
       }                 

       // Variables declaration - do not modify                     
       private javax.swing.JButton btnCancelar;
       private javax.swing.JButton btnOk;
       private javax.swing.JLabel lblMensagem;
       // End of variables declaration                   
   }
    
    
    
    /**
     * Classe interna para um JDialog para o login no sistema
     * 
     * @author Rafael Tavares
     */ 
    public class LoginModal extends javax.swing.JDialog {
        private java.awt.Frame parent;
        private boolean logado;
        private Funcionario funcionarioLogado;


        /**
         * Construtor da classe
         * @param parent
         * @param modal 
         */
        public LoginModal(java.awt.Frame parent, boolean modal) {
            super(parent, modal);
            initComponents();
            jTextField1.setVisible(false);
            this.setParent(parent);
            this.setLogado(false);
            this.setFuncionarioLogado(null);
            this.getContentPane().setBackground(Color.PINK);
            this.setLocationRelativeTo(null);
        }

        /**
         * Define o pai do LoginModal
         * @param frame 
         */
        private void setParent(java.awt.Frame frame) {
            this.parent = frame;
        }

        /**
         * Retorna o pai do LoginModal
         * @return Pai do LoginModal
         */
        @Override
        public java.awt.Frame getParent() {
            return this.parent;
        }

        /**
         * Define se o usuário logou ou não no sistema
         * @param logado 
         */
        private void setLogado(boolean logado) {
            this.logado = logado;
        }

        /**
         * Retorna se o usuário logou ou não no sistema
         * @return Logado - true se entrou, false se cancelou
         */
        public boolean isLogado() {
            return this.logado;
        }
        /**
         * Define o funcionário que logou no sistema
         * @param f 
         */
        private void setFuncionarioLogado(Funcionario f) {
            this.funcionarioLogado = f;
        }

        /**
         * Retorna o funcionário que logou no sistema
         * @return Funcionário que logou
         */
        public Funcionario getFuncionarioLogado() {
            return this.funcionarioLogado;
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            lblImagem = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            txtLogin = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            ptxtSenha = new javax.swing.JPasswordField();
            btnOk = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();
            jTextField1 = new javax.swing.JTextField();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Login");

            lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icones/funcionario.png"))); // NOI18N

            jLabel1.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel1.setText("Login");

            txtLogin.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtLogin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtLoginActionPerformed(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            jLabel2.setText("Senha");

            ptxtSenha.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            ptxtSenha.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ptxtSenhaActionPerformed(evt);
                }
            });

            btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"))); // NOI18N
            btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnOkMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnOkMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnOkMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnOkMouseReleased(evt);
                }
            });
            btnOk.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOkActionPerformed(evt);
                }
            });

            btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar.png"))); // NOI18N
            btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnCancelarMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnCancelarMouseReleased(evt);
                }
            });
            btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCancelarActionPerformed(evt);
                }
            });

            jTextField1.setEditable(false);
            jTextField1.setText("jTextField1");
            jTextField1.setEnabled(false);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(lblImagem)
                            .addGap(32, 32, 32)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(txtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(ptxtSenha)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(22, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ptxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(37, 37, 37))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblImagem)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );

            pack();
        }// </editor-fold>                        

        private void btnOkMouseEntered(java.awt.event.MouseEvent evt) {                                   
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
            btnOk.setIcon(i);
        }                                  

        private void btnOkMouseExited(java.awt.event.MouseEvent evt) {                                  
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"));
            btnOk.setIcon(i);
        }                                 

        private void btnOkMousePressed(java.awt.event.MouseEvent evt) {                                   
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Pressed.png"));
            btnOk.setIcon(i);
        }                                  

        private void btnOkMouseReleased(java.awt.event.MouseEvent evt) {                                    
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
            btnOk.setIcon(i);
        }                                   

        private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {                                      
            Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
            Border bordaPadrao = jTextField1.getBorder();
            int aux = 0;
            if (txtLogin.getText().equals("")) {
                txtLogin.setBorder(bordaVermelha);
                aux = 1;
            }
            else {
                txtLogin.setBorder(bordaPadrao);
            }
            String senha = String.copyValueOf(ptxtSenha.getPassword());
            if (senha.length() == 0) {
                ptxtSenha.setBorder(bordaVermelha);
                aux = 1;
            }
            else {
                ptxtSenha.setBorder(bordaPadrao);
            }
            if (aux == 1) {
                MensagemOkModal msg = new MensagemOkModal(this.getParent(), true, "Informe o Login e Senha", 
                        "Erro - Preencha todos os campos");
                msg.setVisible(true);
            }
            else {
                try {
                    Consulta con = new Consulta();
                    Funcionario f = con.encontrarFuncionarioLogin(txtLogin.getText());
                    if (!f.validarLoginSenha(senha)) {
                        MensagemOkModal msg = new MensagemOkModal(this.getParent(), true, "Login e/ou senha inválidos", 
                                "Erro - Preencha todos os campos");
                        msg.setVisible(true);
                    }
                    else {
                        this.setLogado(true);
                        this.setFuncionarioLogado(f);
                        dispose();
                    }
                } catch (NullPointerException ex) {
                    MensagemOkModal msg = new MensagemOkModal(this.getParent(), true, "Login e/ou senha inválidos", 
                            "Erro - Preencha todos os campos");
                    msg.setVisible(true);
                }
            }
        }                                             

        private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {                                         
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Hover.png"));
            btnCancelar.setIcon(i);
        }                                        

        private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {                                        
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar.png"));
            btnCancelar.setIcon(i);
        }                                       

        private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {                                         
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Pressed.png"));
            btnCancelar.setIcon(i);
        }                                        

        private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {                                          
            ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoCancelar_Hover.png"));
            btnCancelar.setIcon(i);
        }                                         

        private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
            dispose();
        }                                           

        private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
            btnOkActionPerformed(evt);
        }                                        

        private void ptxtSenhaActionPerformed(java.awt.event.ActionEvent evt) {                                          
            btnOkActionPerformed(evt);
        }       


        // Variables declaration - do not modify                     
        private javax.swing.JButton btnCancelar;
        private javax.swing.JButton btnOk;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JLabel lblImagem;
        private javax.swing.JPasswordField ptxtSenha;
        private javax.swing.JTextField txtLogin;
        // End of variables declaration                   
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnAdicionarProdutoVenda;
    private javax.swing.JButton btnAdicionarSerivocVenda;
    private javax.swing.JButton btnAgendarServico;
    private javax.swing.JButton btnCadastrarServico;
    private javax.swing.JButton btnCancelarServico;
    private javax.swing.JButton btnConsultarServico;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnMenuAgenda;
    private javax.swing.JButton btnMenuCadastro;
    private javax.swing.JButton btnMenuCliente;
    private javax.swing.JButton btnMenuConsulta;
    private javax.swing.JButton btnMenuFornecedor;
    private javax.swing.JButton btnMenuFornecimento;
    private javax.swing.JButton btnMenuFuncionario;
    private javax.swing.JButton btnMenuProduto;
    private javax.swing.JButton btnMenuServico;
    private javax.swing.JButton btnMenuVenda;
    private javax.swing.JButton btnRemoverProdutoVenda;
    private javax.swing.JButton btnRemoverServicoVenda;
    private javax.swing.JCheckBox chkCodigoCliente;
    private javax.swing.JCheckBox chkCodigoServico;
    private javax.swing.JCheckBox chkData;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JCheckBox chkLoginFuncionario;
    private javax.swing.JCheckBox chkNomeServico;
    private javax.swing.JCheckBox chkValor;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JFormattedTextField ftxtDataFim;
    private javax.swing.JFormattedTextField ftxtDataInicio;
    private javax.swing.JFormattedTextField ftxtValorFim;
    private javax.swing.JFormattedTextField ftxtValorInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDataAte;
    private javax.swing.JLabel lblValorAte;
    private javax.swing.JLabel lblValorTotalVenda;
    private javax.swing.JPanel pnlAgenda;
    private javax.swing.JPanel pnlServico;
    private javax.swing.JPanel pnlSubMenu;
    private javax.swing.JPanel pnlVenda;
    private javax.swing.JTable tblAgenda;
    private javax.swing.JTable tblProdutoVenda;
    private javax.swing.JTable tblServico;
    private javax.swing.JTable tblServicoVenda;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoServico;
    private javax.swing.JTextField txtLoginFuncionario;
    private javax.swing.JTextField txtNomeServico;
    private javax.swing.JTextField txtPadrao;
    private javax.swing.JTextField txtProdutoVendaCodigo;
    private javax.swing.JTextField txtProdutoVendaMarca;
    private javax.swing.JTextField txtProdutoVendaNome;
    private javax.swing.JTextField txtProdutoVendaQtdUnitaria;
    private javax.swing.JTextField txtProdutoVendaQuantidade;
    private javax.swing.JTextField txtProdutoVendaValorTotal;
    private javax.swing.JTextField txtProdutoVendaValorUnitario;
    private javax.swing.JTextField txtServicoVendaCliente;
    private javax.swing.JTextField txtServicoVendaCodigo;
    private javax.swing.JTextField txtServicoVendaData;
    private javax.swing.JTextField txtServicoVendaEstado;
    private javax.swing.JTextField txtServicoVendaFuncionario;
    private javax.swing.JTextField txtServicoVendaNome;
    private javax.swing.JTextField txtServicoVendaValor;
    // End of variables declaration//GEN-END:variables
}
