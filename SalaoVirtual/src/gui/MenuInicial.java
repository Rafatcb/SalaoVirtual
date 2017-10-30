/*
 * Classe referente ao Menu Inicial
 */
package gui;

import gui.tableModel.AgendaTableModel;
import gui.tableModel.ProdutoVendaTableModel;
import gui.tableModel.ForcedListSelectionModel;
import gui.tableModel.CadastroProdutoFornecimentoTableModel;
import gui.tableModel.ConsultaClienteTableModel;
import gui.tableModel.ConsultaFornecedorTableModel;
import gui.tableModel.ConsultaFuncionarioTableModel;
import gui.tableModel.ServicoTableModel;
import exceptions.ChaveNulaException;
import exceptions.DataInvalidaException;
import exceptions.EstadoServicoInvalidoException;
import exceptions.ObjetoJaCadastradoException;
import exceptions.TipoDeCartaoInvalidoException;
import gui.tableModel.ConsultaProdutoTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import salaovirtual.Cartao;
import salaovirtual.Cliente;
import salaovirtual.Compra;
import salaovirtual.Dinheiro;
import salaovirtual.Fornecedor;
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
    private List<Float> listaCadastroFornecimentoValor; // valor de compra
    private List<Produto> listaCadastroFornecimentoProduto; // produto de compra
    private List<Integer> listaCadastroFornecimentoQuantidade; // qtd de compra
    private float valorTotalVenda;
    private float valorTotalFornecimento;
    private Servico servicoVenda;
    private Produto produtoVenda;
    private Produto produtoFornecimento;
    private Fornecedor fornecedorFornecimento;
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
        this.getContentPane().setBackground(Color.PINK);
        this.setSize(1316, 710);
        this.setLocationRelativeTo(null);
        pnlSubMenu.setVisible(false);
        pnlServico.setVisible(false);
        pnlCadastroCliente.setVisible(false);
        pnlCadastroFuncionario.setVisible(false);
        pnlVenda.setVisible(false);
        pnlCadastroProduto.setVisible(false);
        pnlCadastroFornecedor.setVisible(false);
        pnlCadastroFornecimento.setVisible(false);
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
        tblCadastroFornecimentoProduto.setSelectionModel(new ForcedListSelectionModel());
        tblCadastroFornecimentoProduto.setModel(new CadastroProdutoFornecimentoTableModel(listaCadastroFornecimentoProduto,listaCadastroFornecimentoQuantidade,listaCadastroFornecimentoValor));
        gerarTabelaCadastroFornecimentoProduto();
        List<Servico> sl = null;
        gerarTabelaServicoVenda(sl);
        List<Produto> pl = null;
        List<Integer> il = null;
        gerarTabelaProdutoVenda(pl, il);
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
        txtCadastroFornecimentoProdutoMarca.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoProdutoNome.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoProdutoQtdUnitaria.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoProdutoValorVenda.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoProdutoValorTotal.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoFornecedorNome.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoFornecedorCnpj.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoFornecedorTelefone.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoFornecedorCidade.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoFornecedorEstado.setDisabledTextColor(Color.black);
        txtCadastroFornecimentoCodigo.setDisabledTextColor(Color.black);
        listaServicoVenda = new ArrayList();
        listaProdutoVenda = new ArrayList();
        listaProdutoVendaQuantidade = new ArrayList();
        valorTotalVenda = 0f;
        valorTotalFornecimento = 0f;
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
     * Método para facilitar a criação/formatação da tblAgenda
     */
    private void gerarTabelaCadastroFornecimentoProduto() {
        tblCadastroFornecimentoProduto.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(0).setMaxWidth(105);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(0).setPreferredWidth(105);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(2).setMaxWidth(125);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(2).setPreferredWidth(125);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(4).setMaxWidth(100);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(5).setMaxWidth(110);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(5).setPreferredWidth(110);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(6).setMaxWidth(110);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(6).setPreferredWidth(110);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(7).setMaxWidth(110);
        tblCadastroFornecimentoProduto.getColumnModel().getColumn(7).setPreferredWidth(110);
    }
    
    /**
     * Método para organizar a tela de cadastro de cliente
     */
    private void limparTelaCadastroCliente() {
        Consulta con = new Consulta();
        Border bordaPadrao = txtCadastroClienteCodigo.getBorder();
        txtCadastroClienteCodigo.setText(Integer.toString(con.getProxCodigo("Cliente.csv")));
        txtCadastroClienteEmail.setText("");
        txtCadastroClienteNome.setText("");
        ftxtCadastroClienteCpf.setText("");
        ftxtCadastroClienteData.setText("");
        ftxtCadastroClienteTelefone.setText("");
        ftxtCadastroClienteData.setBorder(bordaPadrao);
        ftxtCadastroClienteData.setSize(157, txtCadastroClienteCodigo.getHeight());
        ftxtCadastroClienteCpf.setBorder(bordaPadrao);
        ftxtCadastroClienteCpf.setSize(157, txtCadastroClienteCodigo.getHeight());
        ftxtCadastroClienteTelefone.setBorder(bordaPadrao);
        ftxtCadastroClienteTelefone.setSize(157, txtCadastroClienteCodigo.getHeight());
        txtCadastroClienteNome.setBorder(bordaPadrao);
        txtCadastroClienteNome.setSize(286, txtCadastroClienteCodigo.getHeight());
    }
    
    /**
     * Método para organizar a tela de cadastro de fornecedor
     */
    private void limparTelaCadastroFornecedor() {
        Consulta con = new Consulta();
        Border bordaPadrao = txtCadastroFornecedorCodigo.getBorder();
        txtCadastroFornecedorCodigo.setText(Integer.toString(con.getProxCodigo("Fornecedor.csv")));
        txtCadastroFornecedorCidade.setText("");
        txtCadastroFornecedorComplemento.setText("");
        txtCadastroFornecedorEmail.setText("");
        txtCadastroFornecedorNome.setText("");
        txtCadastroFornecedorNome.setBorder(bordaPadrao);
        txtCadastroFornecedorRua.setText("");
        ftxtCadastroFornecedorCnpj.setText("");
        ftxtCadastroFornecedorCnpj.setBorder(bordaPadrao);
        ftxtCadastroFornecedorNumero.setText("");
        ftxtCadastroFornecedorNumero.setBorder(bordaPadrao);
        ftxtCadastroFornecedorTelefone.setText("");
        ftxtCadastroFornecedorTelefone.setBorder(bordaPadrao);
        cmbCadastroFornecedorEstado.setSelectedIndex(24);
    }
    
    /**
     * Método para organizar a tela de cadastro de fornecimento
     */
    private void limparTelaCadastroFornecimento() {
        Consulta con = new Consulta();
        Border bordaPadrao = txtCadastroFornecimentoCodigo.getBorder();
        txtCadastroFornecimentoCodigo.setText(Integer.toString(con.getProxCodigo("Compra.csv")));
        listaCadastroFornecimentoProduto = new ArrayList();
        listaCadastroFornecimentoQuantidade = new ArrayList();
        listaCadastroFornecimentoValor = new ArrayList();
        lblCadastroFornecimentoValorTotal.setText("R$ 0.0");
        gerarTabelaCadastroFornecimentoProduto();
        tblCadastroFornecimentoProduto.setModel( new CadastroProdutoFornecimentoTableModel(listaCadastroFornecimentoProduto, listaCadastroFornecimentoQuantidade, listaCadastroFornecimentoValor));
        limparProdutoFornecimento();
        limparFornecedorFornecimento();
    }
    
    
    /**
     * Método para organizar a tela de cadastro de funcionário
     */
    private void limparTelaCadastroFuncionario() {
        Consulta con = new Consulta();
        Border bordaPadrao = txtCadastroClienteCodigo.getBorder();
        cmbCadastroFuncionarioEstado.setSelectedIndex(24);
        txtCadastroFuncionarioCidade.setText("");
        txtCadastroFuncionarioComplemento.setText("");
        txtCadastroFuncionarioLogin.setText("");
        txtCadastroFuncionarioNome.setText("");
        txtCadastroFuncionarioRua.setText("");
        ftxtCadastroFuncionarioCpf.setText("");
        ftxtCadastroFuncionarioNumero.setText("");
        ftxtCadastroFuncionarioTelefone.setText("");
        ptxtCadastroFuncionarioSenha.setText("");
        ptxtCadastroFuncionarioSenha.setBorder(bordaPadrao);
        ptxtCadastroFuncionarioSenha.setSize(157, txtCadastroClienteCodigo.getHeight());
        ftxtCadastroFuncionarioCpf.setBorder(bordaPadrao);
        ftxtCadastroFuncionarioCpf.setSize(157, txtCadastroClienteCodigo.getHeight());
        ftxtCadastroFuncionarioTelefone.setBorder(bordaPadrao);
        ftxtCadastroFuncionarioTelefone.setSize(157, txtCadastroClienteCodigo.getHeight());
        ptxtCadastroFuncionarioSenha.setBorder(bordaPadrao);
        ptxtCadastroFuncionarioSenha.setSize(130, txtCadastroClienteCodigo.getHeight());
        txtCadastroFuncionarioNome.setBorder(bordaPadrao);
        txtCadastroFuncionarioNome.setSize(290, txtCadastroClienteCodigo.getHeight());
        txtCadastroFuncionarioCidade.setBorder(bordaPadrao);
        txtCadastroFuncionarioCidade.setSize(220, txtCadastroClienteCodigo.getHeight());
        txtCadastroFuncionarioLogin.setBorder(bordaPadrao);
        txtCadastroFuncionarioLogin.setSize(130, txtCadastroClienteCodigo.getHeight());
    }
    
    /**
     * Método para organizar a tela de cadastro de produto
     */
    private void limparTelaCadastroProduto() {
        Border bordaPadrao = txtCadastrarProdutoCodigo.getBorder();
        Consulta con = new Consulta();
        txtCadastrarProdutoCodigo.setText(Integer.toString(con.getProxCodigo("Produto.csv")));
        txtCadastrarProdutoNome.setText("");
        txtCadastrarProdutoMarca.setText("");
        txtCadastrarProdutoUnidade.setText("");
        ftxtCadastrarProdutoValor.setText("");
        ftxtCadastrarProdutoEstoque.setText("");
        ftxtCadastrarProdutoEstoqueMin.setText("");
        ftxtCadastrarProdutoQtdUnitaria.setText("");
        int altura = txtCadastrarProdutoCodigo.getHeight();
        txtCadastrarProdutoNome.setBorder(bordaPadrao);
        txtCadastrarProdutoNome.setSize(186, altura);
        txtCadastrarProdutoMarca.setBorder(bordaPadrao);
        txtCadastrarProdutoMarca.setSize(160, altura);
        txtCadastrarProdutoUnidade.setBorder(bordaPadrao);
        txtCadastrarProdutoUnidade.setSize(80, altura);
        ftxtCadastrarProdutoValor.setBorder(bordaPadrao);
        ftxtCadastrarProdutoValor.setSize(94, altura);
        ftxtCadastrarProdutoEstoque.setBorder(bordaPadrao);
        ftxtCadastrarProdutoEstoque.setSize(70, altura);
        ftxtCadastrarProdutoEstoqueMin.setBorder(bordaPadrao);
        ftxtCadastrarProdutoEstoqueMin.setSize(70, altura);
        ftxtCadastrarProdutoQtdUnitaria.setBorder(bordaPadrao);
        ftxtCadastrarProdutoQtdUnitaria.setSize(108, altura);
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
    
    private void limparProdutoFornecimento() {
        txtCadastroFornecimentoProdutoMarca.setText("");
        txtCadastroFornecimentoProdutoNome.setText("");
        txtCadastroFornecimentoProdutoQtdUnitaria.setText("");
        txtCadastroFornecimentoProdutoValorVenda.setText("");
        txtCadastroFornecimentoProdutoValor.setText("");
        txtCadastroFornecimentoProdutoQuantidade.setText("");
        txtCadastroFornecimentoProdutoValorTotal.setText("");
        txtCadastroFornecimentoProdutoCodigo.setText("");
    }
    
    private void limparFornecedorFornecimento() {
        txtCadastroFornecimentoFornecedorCodigo.setText("");
        txtCadastroFornecimentoFornecedorNome.setText("");
        txtCadastroFornecimentoFornecedorCnpj.setText("");
        txtCadastroFornecimentoFornecedorTelefone.setText("");
        txtCadastroFornecimentoFornecedorCidade.setText("");
        txtCadastroFornecimentoFornecedorEstado.setText("");
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
        btnAlterarServico = new javax.swing.JButton();
        pnlSubMenu = new javax.swing.JPanel();
        btnMenuFornecedor = new javax.swing.JButton();
        btnMenuFuncionario = new javax.swing.JButton();
        btnMenuFornecimento = new javax.swing.JButton();
        btnMenuProduto = new javax.swing.JButton();
        btnMenuCliente = new javax.swing.JButton();
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
        pnlCadastroCliente = new javax.swing.JPanel();
        btnCadastrarCliente = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtCadastroClienteNome = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        ftxtCadastroClienteCpf = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCadastroClienteCodigo = new javax.swing.JTextField();
        ftxtCadastroClienteTelefone = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtCadastroClienteEmail = new javax.swing.JTextField();
        ftxtCadastroClienteData = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        pnlCadastroFuncionario = new javax.swing.JPanel();
        btnCadastrarFuncionario = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtCadastroFuncionarioComplemento = new javax.swing.JTextField();
        ftxtCadastroFuncionarioCpf = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        ftxtCadastroFuncionarioTelefone = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtCadastroFuncionarioRua = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCadastroFuncionarioNome = new javax.swing.JTextField();
        ptxtCadastroFuncionarioSenha = new javax.swing.JPasswordField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ftxtCadastroFuncionarioNumero = new javax.swing.JFormattedTextField();
        txtCadastroFuncionarioCidade = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cmbCadastroFuncionarioEstado = new javax.swing.JComboBox<>();
        txtCadastroFuncionarioLogin = new javax.swing.JTextField();
        pnlCadastroProduto = new javax.swing.JPanel();
        btnCadastrarProduto = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtCadastrarProdutoUnidade = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        ftxtCadastrarProdutoEstoque = new javax.swing.JFormattedTextField();
        txtCadastrarProdutoCodigo = new javax.swing.JTextField();
        txtCadastrarProdutoNome = new javax.swing.JTextField();
        ftxtCadastrarProdutoQtdUnitaria = new javax.swing.JFormattedTextField();
        txtCadastrarProdutoMarca = new javax.swing.JTextField();
        ftxtCadastrarProdutoEstoqueMin = new javax.swing.JFormattedTextField();
        ftxtCadastrarProdutoValor = new javax.swing.JFormattedTextField();
        pnlCadastroFornecedor = new javax.swing.JPanel();
        btnCadastrarFornecedor = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtCadastroFornecedorCodigo = new javax.swing.JTextField();
        txtCadastroFornecedorNome = new javax.swing.JTextField();
        ftxtCadastroFornecedorCnpj = new javax.swing.JFormattedTextField();
        txtCadastroFornecedorEmail = new javax.swing.JTextField();
        ftxtCadastroFornecedorTelefone = new javax.swing.JFormattedTextField();
        txtCadastroFornecedorComplemento = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtCadastroFornecedorRua = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        ftxtCadastroFornecedorNumero = new javax.swing.JFormattedTextField();
        txtCadastroFornecedorCidade = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cmbCadastroFornecedorEstado = new javax.swing.JComboBox<>();
        pnlCadastroFornecimento = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCadastroFornecimentoProduto = new javax.swing.JTable();
        btnCadastroFornecimentoAddProduto = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        txtCadastroFornecimentoCodigo = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtCadastroFornecimentoProdutoCodigo = new javax.swing.JTextField();
        txtCadastroFornecimentoProdutoNome = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtCadastroFornecimentoProdutoMarca = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txtCadastroFornecimentoProdutoQtdUnitaria = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtCadastroFornecimentoProdutoValorVenda = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        txtCadastroFornecimentoProdutoValorTotal = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txtCadastroFornecimentoProdutoQuantidade = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtCadastroFornecimentoProdutoValor = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtCadastroFornecimentoFornecedorCodigo = new javax.swing.JTextField();
        txtCadastroFornecimentoFornecedorNome = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtCadastroFornecimentoFornecedorCnpj = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txtCadastroFornecimentoFornecedorTelefone = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtCadastroFornecimentoFornecedorCidade = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txtCadastroFornecimentoFornecedorEstado = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        btnCadastroFornecimentoConsultarFornecedor = new javax.swing.JButton();
        btnCadastroFornecimentoConsultarProduto = new javax.swing.JButton();
        btnFinalizarFornecimento = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        lblCadastroFornecimentoValorTotal = new javax.swing.JLabel();
        btnCadastroFornecimentoRemoverProduto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Salão Virtual");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuInicial.class.getResource("/images/background/mini-icon.png")));
        setResizable(false);
        getContentPane().setLayout(null);

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
        getContentPane().add(btnMenuAgenda);
        btnMenuAgenda.setBounds(31, 13, 240, 82);

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
        getContentPane().add(btnMenuConsulta);
        btnMenuConsulta.setBounds(1057, 13, 240, 82);

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
        getContentPane().add(btnMenuServico);
        btnMenuServico.setBounds(289, 13, 240, 82);

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
        getContentPane().add(btnMenuVenda);
        btnMenuVenda.setBounds(547, 13, 240, 82);

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
        getContentPane().add(btnMenuCadastro);
        btnMenuCadastro.setBounds(805, 13, 240, 82);

        jSeparator1.setToolTipText("");
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 102, 1305, 10);

        pnlAgenda.setAutoscrolls(true);
        pnlAgenda.setOpaque(false);
        pnlAgenda.setPreferredSize(new java.awt.Dimension(1290, 645));
        pnlAgenda.setLayout(null);

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

        pnlAgenda.add(jScrollPane1);
        jScrollPane1.setBounds(115, 47, 1075, 370);

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
        pnlAgenda.add(btnAgendarServico);
        btnAgendarServico.setBounds(360, 460, 163, 68);

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
        pnlAgenda.add(btnCancelarServico);
        btnCancelarServico.setBounds(770, 460, 163, 68);

        btnAlterarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agenda/botaoAlterarServico.png"))); // NOI18N
        btnAlterarServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterarServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAlterarServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlterarServicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAlterarServicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAlterarServicoMouseReleased(evt);
            }
        });
        btnAlterarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarServicoActionPerformed(evt);
            }
        });
        pnlAgenda.add(btnAlterarServico);
        btnAlterarServico.setBounds(570, 460, 163, 68);

        getContentPane().add(pnlAgenda);
        pnlAgenda.setBounds(12, 109, 1300, 590);

        pnlSubMenu.setAutoscrolls(true);
        pnlSubMenu.setMaximumSize(new java.awt.Dimension(1220, 710));
        pnlSubMenu.setOpaque(false);
        pnlSubMenu.setPreferredSize(new java.awt.Dimension(1220, 710));
        pnlSubMenu.setLayout(null);

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
        btnMenuFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuFornecedorActionPerformed(evt);
            }
        });
        pnlSubMenu.add(btnMenuFornecedor);
        btnMenuFornecedor.setBounds(830, 170, 240, 82);

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
        btnMenuFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuFuncionarioActionPerformed(evt);
            }
        });
        pnlSubMenu.add(btnMenuFuncionario);
        btnMenuFuncionario.setBounds(560, 170, 240, 82);

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
        btnMenuFornecimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuFornecimentoActionPerformed(evt);
            }
        });
        pnlSubMenu.add(btnMenuFornecimento);
        btnMenuFornecimento.setBounds(700, 280, 240, 82);

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
        btnMenuProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuProdutoActionPerformed(evt);
            }
        });
        pnlSubMenu.add(btnMenuProduto);
        btnMenuProduto.setBounds(410, 280, 240, 82);

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
        btnMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuClienteActionPerformed(evt);
            }
        });
        pnlSubMenu.add(btnMenuCliente);
        btnMenuCliente.setBounds(280, 170, 240, 82);

        getContentPane().add(pnlSubMenu);
        pnlSubMenu.setBounds(0, 110, 1310, 590);

        pnlServico.setAutoscrolls(true);
        pnlServico.setOpaque(false);
        pnlServico.setPreferredSize(new java.awt.Dimension(1290, 645));
        pnlServico.setLayout(null);

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

        pnlServico.add(jScrollPane2);
        jScrollPane2.setBounds(41, 138, 1204, 310);

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
        pnlServico.add(btnCadastrarServico);
        btnCadastrarServico.setBounds(390, 480, 219, 80);

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
        pnlServico.add(btnConsultarServico);
        btnConsultarServico.setBounds(680, 480, 219, 80);

        txtNomeServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtNomeServico.setEnabled(false);
        pnlServico.add(txtNomeServico);
        txtNomeServico.setBounds(277, 96, 212, 24);

        chkNomeServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkNomeServico.setText("Nome do Serviço");
        chkNomeServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNomeServicoActionPerformed(evt);
            }
        });
        pnlServico.add(chkNomeServico);
        chkNomeServico.setBounds(293, 66, 163, 27);

        chkValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkValor.setText("Faixa de valor");
        chkValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkValorActionPerformed(evt);
            }
        });
        pnlServico.add(chkValor);
        chkValor.setBounds(570, 66, 155, 27);

        lblValorAte.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        lblValorAte.setText("até");
        lblValorAte.setEnabled(false);
        pnlServico.add(lblValorAte);
        lblValorAte.setBounds(634, 99, 27, 18);

        chkData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkData.setText("Faixa de data");
        chkData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDataActionPerformed(evt);
            }
        });
        pnlServico.add(chkData);
        chkData.setBounds(885, 66, 145, 27);

        lblDataAte.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        lblDataAte.setText("até");
        lblDataAte.setEnabled(false);
        pnlServico.add(lblDataAte);
        lblDataAte.setBounds(949, 99, 27, 18);

        cmbEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agendado", "Realizado", "Cancelado" }));
        cmbEstado.setEnabled(false);
        pnlServico.add(cmbEstado);
        cmbEstado.setBounds(127, 96, 106, 24);

        chkLoginFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkLoginFuncionario.setText("Login Funcionário");
        chkLoginFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLoginFuncionarioActionPerformed(evt);
            }
        });
        pnlServico.add(chkLoginFuncionario);
        chkLoginFuncionario.setBounds(561, 9, 181, 27);

        txtLoginFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtLoginFuncionario.setEnabled(false);
        pnlServico.add(txtLoginFuncionario);
        txtLoginFuncionario.setBounds(561, 39, 181, 24);

        txtCodigoCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCodigoCliente.setEnabled(false);
        pnlServico.add(txtCodigoCliente);
        txtCodigoCliente.setBounds(924, 39, 57, 24);

        chkCodigoCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkCodigoCliente.setText("Código Cliente");
        chkCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCodigoClienteActionPerformed(evt);
            }
        });
        pnlServico.add(chkCodigoCliente);
        chkCodigoCliente.setBounds(875, 9, 155, 27);

        chkEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkEstado.setText("Estado");
        chkEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstadoActionPerformed(evt);
            }
        });
        pnlServico.add(chkEstado);
        chkEstado.setBounds(136, 66, 83, 27);

        txtCodigoServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCodigoServico.setEnabled(false);
        pnlServico.add(txtCodigoServico);
        txtCodigoServico.setBounds(338, 39, 77, 24);

        chkCodigoServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        chkCodigoServico.setText("Código do Serviço");
        chkCodigoServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCodigoServicoActionPerformed(evt);
            }
        });
        pnlServico.add(chkCodigoServico);
        chkCodigoServico.setBounds(293, 9, 181, 27);

        txtPadrao.setEditable(false);
        pnlServico.add(txtPadrao);
        txtPadrao.setBounds(55, 36, 6, 22);

        ftxtDataFim.setEnabled(false);
        ftxtDataFim.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtDataFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtDataFimFocusGained(evt);
            }
        });
        pnlServico.add(ftxtDataFim);
        ftxtDataFim.setBounds(988, 96, 108, 24);

        ftxtDataInicio.setEnabled(false);
        ftxtDataInicio.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtDataInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtDataInicioFocusGained(evt);
            }
        });
        pnlServico.add(ftxtDataInicio);
        ftxtDataInicio.setBounds(829, 96, 108, 24);

        ftxtValorInicio.setEnabled(false);
        ftxtValorInicio.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtValorInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtValorInicioFocusGained(evt);
            }
        });
        pnlServico.add(ftxtValorInicio);
        ftxtValorInicio.setBounds(521, 96, 108, 24);

        ftxtValorFim.setEnabled(false);
        ftxtValorFim.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtValorFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtValorFimFocusGained(evt);
            }
        });
        pnlServico.add(ftxtValorFim);
        ftxtValorFim.setBounds(673, 96, 108, 24);

        getContentPane().add(pnlServico);
        pnlServico.setBounds(12, 110, 1293, 590);

        pnlVenda.setAutoscrolls(true);
        pnlVenda.setOpaque(false);
        pnlVenda.setPreferredSize(new java.awt.Dimension(1290, 645));
        pnlVenda.setLayout(null);

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

        pnlVenda.add(jScrollPane3);
        jScrollPane3.setBounds(80, 100, 1075, 90);

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
        pnlVenda.add(btnAdicionarProdutoVenda);
        btnAdicionarProdutoVenda.setBounds(380, 210, 163, 68);

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
        pnlVenda.add(btnRemoverProdutoVenda);
        btnRemoverProdutoVenda.setBounds(660, 210, 163, 68);

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel1.setText("Código");
        pnlVenda.add(jLabel1);
        jLabel1.setBounds(43, 43, 54, 18);

        txtProdutoVendaCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProdutoVendaCodigoFocusLost(evt);
            }
        });
        pnlVenda.add(txtProdutoVendaCodigo);
        txtProdutoVendaCodigo.setBounds(43, 68, 70, 24);

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel2.setText("Produtos");
        pnlVenda.add(jLabel2);
        jLabel2.setBounds(89, 13, 96, 23);

        txtProdutoVendaNome.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaNome.setEnabled(false);
        pnlVenda.add(txtProdutoVendaNome);
        txtProdutoVendaNome.setBounds(268, 68, 228, 24);

        jLabel3.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel3.setText("Nome");
        pnlVenda.add(jLabel3);
        jLabel3.setBounds(268, 43, 36, 18);

        txtProdutoVendaMarca.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaMarca.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaMarca.setEnabled(false);
        pnlVenda.add(txtProdutoVendaMarca);
        txtProdutoVendaMarca.setBounds(683, 68, 228, 24);

        jLabel4.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel4.setText("Marca");
        pnlVenda.add(jLabel4);
        jLabel4.setBounds(683, 43, 45, 18);

        txtProdutoVendaQtdUnitaria.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaQtdUnitaria.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaQtdUnitaria.setEnabled(false);
        pnlVenda.add(txtProdutoVendaQtdUnitaria);
        txtProdutoVendaQtdUnitaria.setBounds(529, 68, 117, 24);

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel5.setText("Qtd. Unitária");
        pnlVenda.add(jLabel5);
        jLabel5.setBounds(529, 43, 117, 18);

        txtProdutoVendaValorUnitario.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaValorUnitario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaValorUnitario.setEnabled(false);
        pnlVenda.add(txtProdutoVendaValorUnitario);
        txtProdutoVendaValorUnitario.setBounds(947, 68, 98, 24);

        jLabel6.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel6.setText("Valor Unitário");
        pnlVenda.add(jLabel6);
        jLabel6.setBounds(937, 43, 126, 18);

        txtProdutoVendaValorTotal.setBackground(new java.awt.Color(240, 240, 240));
        txtProdutoVendaValorTotal.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaValorTotal.setEnabled(false);
        pnlVenda.add(txtProdutoVendaValorTotal);
        txtProdutoVendaValorTotal.setBounds(1091, 68, 98, 24);

        jLabel7.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel7.setText("Valor Total");
        pnlVenda.add(jLabel7);
        jLabel7.setBounds(1091, 43, 99, 18);

        txtProdutoVendaQuantidade.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtProdutoVendaQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdutoVendaQuantidadeKeyReleased(evt);
            }
        });
        pnlVenda.add(txtProdutoVendaQuantidade);
        txtProdutoVendaQuantidade.setBounds(143, 68, 90, 24);

        jLabel8.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel8.setText("Quantidade");
        pnlVenda.add(jLabel8);
        jLabel8.setBounds(143, 43, 90, 18);

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

        pnlVenda.add(jScrollPane4);
        jScrollPane4.setBounds(60, 360, 1075, 90);

        txtServicoVendaCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtServicoVendaCodigoFocusLost(evt);
            }
        });
        pnlVenda.add(txtServicoVendaCodigo);
        txtServicoVendaCodigo.setBounds(30, 320, 54, 24);

        jLabel9.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel9.setText("Código");
        pnlVenda.add(jLabel9);
        jLabel9.setBounds(30, 300, 54, 18);

        jLabel10.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel10.setText("Serviços");
        pnlVenda.add(jLabel10);
        jLabel10.setBounds(60, 270, 96, 23);

        jLabel12.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel12.setText("Nome");
        pnlVenda.add(jLabel12);
        jLabel12.setBounds(100, 300, 36, 18);

        txtServicoVendaNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaNome.setEnabled(false);
        pnlVenda.add(txtServicoVendaNome);
        txtServicoVendaNome.setBounds(100, 320, 228, 24);

        jLabel13.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel13.setText("Estado");
        pnlVenda.add(jLabel13);
        jLabel13.setBounds(350, 300, 54, 18);

        txtServicoVendaEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaEstado.setEnabled(false);
        pnlVenda.add(txtServicoVendaEstado);
        txtServicoVendaEstado.setBounds(350, 320, 98, 24);

        txtServicoVendaData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaData.setEnabled(false);
        pnlVenda.add(txtServicoVendaData);
        txtServicoVendaData.setBounds(460, 320, 158, 24);

        jLabel14.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel14.setText("Data");
        pnlVenda.add(jLabel14);
        jLabel14.setBounds(460, 300, 36, 18);

        txtServicoVendaValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaValor.setEnabled(false);
        pnlVenda.add(txtServicoVendaValor);
        txtServicoVendaValor.setBounds(640, 320, 79, 24);

        jLabel15.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel15.setText("Valor");
        pnlVenda.add(jLabel15);
        jLabel15.setBounds(640, 300, 45, 18);

        jLabel16.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel16.setText("Funcionário");
        pnlVenda.add(jLabel16);
        jLabel16.setBounds(750, 300, 99, 18);

        txtServicoVendaFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaFuncionario.setEnabled(false);
        pnlVenda.add(txtServicoVendaFuncionario);
        txtServicoVendaFuncionario.setBounds(750, 320, 224, 24);

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
        pnlVenda.add(btnRemoverServicoVenda);
        btnRemoverServicoVenda.setBounds(650, 470, 163, 68);

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
        pnlVenda.add(btnAdicionarSerivocVenda);
        btnAdicionarSerivocVenda.setBounds(370, 470, 163, 68);

        txtServicoVendaCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtServicoVendaCliente.setEnabled(false);
        pnlVenda.add(txtServicoVendaCliente);
        txtServicoVendaCliente.setBounds(990, 320, 187, 24);

        jLabel17.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel17.setText("Cliente");
        pnlVenda.add(jLabel17);
        jLabel17.setBounds(990, 300, 63, 18);

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
        pnlVenda.add(btnFinalizarVenda);
        btnFinalizarVenda.setBounds(1040, 470, 217, 77);

        jLabel11.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel11.setText("Valor Total");
        pnlVenda.add(jLabel11);
        jLabel11.setBounds(1150, 410, 132, 23);

        lblValorTotalVenda.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        lblValorTotalVenda.setText("R$ 0.0");
        pnlVenda.add(lblValorTotalVenda);
        lblValorTotalVenda.setBounds(1150, 440, 140, 23);

        getContentPane().add(pnlVenda);
        pnlVenda.setBounds(12, 110, 1293, 590);

        pnlCadastroCliente.setAutoscrolls(true);
        pnlCadastroCliente.setOpaque(false);
        pnlCadastroCliente.setPreferredSize(new java.awt.Dimension(1288, 727));
        pnlCadastroCliente.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlCadastroClienteComponentShown(evt);
            }
        });
        pnlCadastroCliente.setLayout(null);

        btnCadastrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroCliente.png"))); // NOI18N
        btnCadastrarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastrarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastrarClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastrarClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastrarClienteMouseReleased(evt);
            }
        });
        btnCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarClienteActionPerformed(evt);
            }
        });
        pnlCadastroCliente.add(btnCadastrarCliente);
        btnCadastrarCliente.setBounds(340, 450, 219, 80);

        jLabel18.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel18.setText("Nome");
        pnlCadastroCliente.add(jLabel18);
        jLabel18.setBounds(300, 170, 36, 18);

        txtCadastroClienteNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroCliente.add(txtCadastroClienteNome);
        txtCadastroClienteNome.setBounds(300, 200, 286, 24);

        jLabel19.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel19.setText("Código");
        pnlCadastroCliente.add(jLabel19);
        jLabel19.setBounds(300, 100, 54, 18);

        ftxtCadastroClienteCpf.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroClienteCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroClienteCpfFocusGained(evt);
            }
        });
        pnlCadastroCliente.add(ftxtCadastroClienteCpf);
        ftxtCadastroClienteCpf.setBounds(420, 120, 157, 24);

        jLabel20.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Cliente");
        pnlCadastroCliente.add(jLabel20);
        jLabel20.setBounds(230, 30, 390, 50);

        txtCadastroClienteCodigo.setEnabled(false);
        pnlCadastroCliente.add(txtCadastroClienteCodigo);
        txtCadastroClienteCodigo.setBounds(300, 120, 54, 22);

        ftxtCadastroClienteTelefone.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroClienteTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroClienteTelefoneFocusGained(evt);
            }
        });
        pnlCadastroCliente.add(ftxtCadastroClienteTelefone);
        ftxtCadastroClienteTelefone.setBounds(280, 360, 157, 24);

        jLabel21.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel21.setText("Telefone");
        pnlCadastroCliente.add(jLabel21);
        jLabel21.setBounds(280, 340, 72, 18);

        jLabel22.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel22.setText("E-mail");
        pnlCadastroCliente.add(jLabel22);
        jLabel22.setBounds(300, 250, 54, 18);

        txtCadastroClienteEmail.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroCliente.add(txtCadastroClienteEmail);
        txtCadastroClienteEmail.setBounds(300, 280, 286, 24);

        ftxtCadastroClienteData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftxtCadastroClienteData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroClienteData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroClienteDataFocusGained(evt);
            }
        });
        pnlCadastroCliente.add(ftxtCadastroClienteData);
        ftxtCadastroClienteData.setBounds(460, 360, 157, 24);

        jLabel23.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel23.setText("Data de Nascimento");
        pnlCadastroCliente.add(jLabel23);
        jLabel23.setBounds(450, 340, 162, 18);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background/bgCliente.png"))); // NOI18N
        pnlCadastroCliente.add(jLabel24);
        jLabel24.setBounds(464, 0, 830, 630);

        jLabel25.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel25.setText("CPF");
        pnlCadastroCliente.add(jLabel25);
        jLabel25.setBounds(420, 100, 27, 18);

        getContentPane().add(pnlCadastroCliente);
        pnlCadastroCliente.setBounds(12, 107, 1294, 590);

        pnlCadastroFuncionario.setAutoscrolls(true);
        pnlCadastroFuncionario.setMaximumSize(new java.awt.Dimension(1290, 645));
        pnlCadastroFuncionario.setMinimumSize(new java.awt.Dimension(1290, 645));
        pnlCadastroFuncionario.setOpaque(false);
        pnlCadastroFuncionario.setPreferredSize(new java.awt.Dimension(1288, 727));
        pnlCadastroFuncionario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlCadastroFuncionarioComponentShown(evt);
            }
        });
        pnlCadastroFuncionario.setLayout(null);

        btnCadastrarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFuncionario.png"))); // NOI18N
        btnCadastrarFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrarFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastrarFuncionarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastrarFuncionarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastrarFuncionarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastrarFuncionarioMouseReleased(evt);
            }
        });
        btnCadastrarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarFuncionarioActionPerformed(evt);
            }
        });
        pnlCadastroFuncionario.add(btnCadastrarFuncionario);
        btnCadastrarFuncionario.setBounds(230, 460, 219, 80);

        jLabel26.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel26.setText("Senha");
        pnlCadastroFuncionario.add(jLabel26);
        jLabel26.setBounds(240, 150, 100, 18);

        txtCadastroFuncionarioComplemento.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFuncionario.add(txtCadastroFuncionarioComplemento);
        txtCadastroFuncionarioComplemento.setBounds(450, 310, 80, 24);

        ftxtCadastroFuncionarioCpf.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroFuncionarioCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroFuncionarioCpfFocusGained(evt);
            }
        });
        pnlCadastroFuncionario.add(ftxtCadastroFuncionarioCpf);
        ftxtCadastroFuncionarioCpf.setBounds(390, 170, 157, 24);

        jLabel28.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Funcionário");
        pnlCadastroFuncionario.add(jLabel28);
        jLabel28.setBounds(110, 60, 390, 50);

        ftxtCadastroFuncionarioTelefone.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroFuncionarioTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroFuncionarioTelefoneFocusGained(evt);
            }
        });
        pnlCadastroFuncionario.add(ftxtCadastroFuncionarioTelefone);
        ftxtCadastroFuncionarioTelefone.setBounds(390, 240, 157, 24);

        jLabel29.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel29.setText("Telefone");
        pnlCadastroFuncionario.add(jLabel29);
        jLabel29.setBounds(390, 220, 72, 18);

        jLabel30.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel30.setText("Rua");
        pnlCadastroFuncionario.add(jLabel30);
        jLabel30.setBounds(80, 290, 50, 18);

        txtCadastroFuncionarioRua.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFuncionario.add(txtCadastroFuncionarioRua);
        txtCadastroFuncionarioRua.setBounds(80, 310, 260, 24);

        jLabel31.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel31.setText("Complemento");
        pnlCadastroFuncionario.add(jLabel31);
        jLabel31.setBounds(440, 290, 100, 18);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background/bgFuncionario.png"))); // NOI18N
        pnlCadastroFuncionario.add(jLabel32);
        jLabel32.setBounds(450, 0, 830, 650);

        jLabel33.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel33.setText("CPF");
        pnlCadastroFuncionario.add(jLabel33);
        jLabel33.setBounds(390, 150, 27, 18);

        jLabel27.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel27.setText("Nome");
        pnlCadastroFuncionario.add(jLabel27);
        jLabel27.setBounds(80, 220, 36, 18);

        txtCadastroFuncionarioNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFuncionario.add(txtCadastroFuncionarioNome);
        txtCadastroFuncionarioNome.setBounds(80, 240, 290, 24);
        pnlCadastroFuncionario.add(ptxtCadastroFuncionarioSenha);
        ptxtCadastroFuncionarioSenha.setBounds(240, 170, 130, 22);

        jLabel34.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel34.setText("Login");
        pnlCadastroFuncionario.add(jLabel34);
        jLabel34.setBounds(80, 150, 80, 18);

        jLabel35.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel35.setText("Número");
        pnlCadastroFuncionario.add(jLabel35);
        jLabel35.setBounds(360, 290, 54, 18);

        ftxtCadastroFuncionarioNumero.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroFuncionarioNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroFuncionarioNumeroFocusGained(evt);
            }
        });
        pnlCadastroFuncionario.add(ftxtCadastroFuncionarioNumero);
        ftxtCadastroFuncionarioNumero.setBounds(360, 310, 60, 24);

        txtCadastroFuncionarioCidade.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFuncionario.add(txtCadastroFuncionarioCidade);
        txtCadastroFuncionarioCidade.setBounds(80, 370, 220, 24);

        jLabel36.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel36.setText("Estado");
        pnlCadastroFuncionario.add(jLabel36);
        jLabel36.setBounds(320, 350, 90, 18);

        jLabel37.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel37.setText("Cidade");
        pnlCadastroFuncionario.add(jLabel37);
        jLabel37.setBounds(80, 350, 90, 18);

        cmbCadastroFuncionarioEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        cmbCadastroFuncionarioEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre", "Alagoas", "Amapás", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins" }));
        pnlCadastroFuncionario.add(cmbCadastroFuncionarioEstado);
        cmbCadastroFuncionarioEstado.setBounds(320, 370, 230, 24);

        txtCadastroFuncionarioLogin.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFuncionario.add(txtCadastroFuncionarioLogin);
        txtCadastroFuncionarioLogin.setBounds(80, 170, 130, 24);

        getContentPane().add(pnlCadastroFuncionario);
        pnlCadastroFuncionario.setBounds(6, 107, 1290, 645);

        pnlCadastroProduto.setAutoscrolls(true);
        pnlCadastroProduto.setMinimumSize(new java.awt.Dimension(1290, 645));
        pnlCadastroProduto.setOpaque(false);
        pnlCadastroProduto.setPreferredSize(new java.awt.Dimension(1290, 645));
        pnlCadastroProduto.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlCadastroProdutoComponentShown(evt);
            }
        });
        pnlCadastroProduto.setLayout(null);

        btnCadastrarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroProduto.png"))); // NOI18N
        btnCadastrarProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrarProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastrarProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastrarProdutoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastrarProdutoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastrarProdutoMouseReleased(evt);
            }
        });
        btnCadastrarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarProdutoActionPerformed(evt);
            }
        });
        pnlCadastroProduto.add(btnCadastrarProduto);
        btnCadastrarProduto.setBounds(200, 430, 219, 80);

        jLabel38.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel38.setText("Nome");
        pnlCadastroProduto.add(jLabel38);
        jLabel38.setBounds(170, 160, 100, 18);

        txtCadastrarProdutoUnidade.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroProduto.add(txtCadastrarProdutoUnidade);
        txtCadastrarProdutoUnidade.setBounds(270, 250, 80, 24);

        jLabel39.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Produto");
        pnlCadastroProduto.add(jLabel39);
        jLabel39.setBounds(90, 80, 390, 50);

        jLabel40.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel40.setText("Valor");
        pnlCadastroProduto.add(jLabel40);
        jLabel40.setBounds(370, 230, 45, 18);

        jLabel41.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel41.setText("Estoque");
        pnlCadastroProduto.add(jLabel41);
        jLabel41.setBounds(200, 290, 70, 18);

        jLabel42.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel42.setText("Unidade");
        pnlCadastroProduto.add(jLabel42);
        jLabel42.setBounds(270, 230, 71, 20);

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background/bgProduto.png"))); // NOI18N
        pnlCadastroProduto.add(jLabel43);
        jLabel43.setBounds(590, 100, 650, 390);

        jLabel44.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel44.setText("Marca");
        pnlCadastroProduto.add(jLabel44);
        jLabel44.setBounds(390, 150, 45, 18);

        jLabel45.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel45.setText("Qtd Unitária");
        pnlCadastroProduto.add(jLabel45);
        jLabel45.setBounds(130, 230, 108, 18);

        jLabel46.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel46.setText("Código");
        pnlCadastroProduto.add(jLabel46);
        jLabel46.setBounds(80, 163, 80, 18);

        jLabel47.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel47.setText("Estoque mín");
        pnlCadastroProduto.add(jLabel47);
        jLabel47.setBounds(320, 290, 120, 18);

        ftxtCadastrarProdutoEstoque.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastrarProdutoEstoque.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastrarProdutoEstoqueFocusGained(evt);
            }
        });
        pnlCadastroProduto.add(ftxtCadastrarProdutoEstoque);
        ftxtCadastrarProdutoEstoque.setBounds(200, 310, 60, 24);

        txtCadastrarProdutoCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastrarProdutoCodigo.setEnabled(false);
        pnlCadastroProduto.add(txtCadastrarProdutoCodigo);
        txtCadastrarProdutoCodigo.setBounds(80, 183, 53, 24);

        txtCadastrarProdutoNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroProduto.add(txtCadastrarProdutoNome);
        txtCadastrarProdutoNome.setBounds(170, 180, 186, 24);

        ftxtCadastrarProdutoQtdUnitaria.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastrarProdutoQtdUnitaria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastrarProdutoQtdUnitariaFocusGained(evt);
            }
        });
        pnlCadastroProduto.add(ftxtCadastrarProdutoQtdUnitaria);
        ftxtCadastrarProdutoQtdUnitaria.setBounds(130, 250, 108, 24);

        txtCadastrarProdutoMarca.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroProduto.add(txtCadastrarProdutoMarca);
        txtCadastrarProdutoMarca.setBounds(390, 183, 160, 24);

        ftxtCadastrarProdutoEstoqueMin.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastrarProdutoEstoqueMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastrarProdutoEstoqueMinFocusGained(evt);
            }
        });
        pnlCadastroProduto.add(ftxtCadastrarProdutoEstoqueMin);
        ftxtCadastrarProdutoEstoqueMin.setBounds(330, 310, 60, 24);

        ftxtCadastrarProdutoValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastrarProdutoValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastrarProdutoValorFocusGained(evt);
            }
        });
        pnlCadastroProduto.add(ftxtCadastrarProdutoValor);
        ftxtCadastrarProdutoValor.setBounds(370, 250, 130, 24);

        getContentPane().add(pnlCadastroProduto);
        pnlCadastroProduto.setBounds(12, 113, 1293, 645);

        pnlCadastroFornecedor.setAutoscrolls(true);
        pnlCadastroFornecedor.setMinimumSize(new java.awt.Dimension(1290, 645));
        pnlCadastroFornecedor.setOpaque(false);
        pnlCadastroFornecedor.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlCadastroFornecedorComponentShown(evt);
            }
        });
        pnlCadastroFornecedor.setLayout(null);

        btnCadastrarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFornecedor.png"))); // NOI18N
        btnCadastrarFornecedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrarFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastrarFornecedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastrarFornecedorMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastrarFornecedorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastrarFornecedorMouseReleased(evt);
            }
        });
        btnCadastrarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarFornecedorActionPerformed(evt);
            }
        });
        pnlCadastroFornecedor.add(btnCadastrarFornecedor);
        btnCadastrarFornecedor.setBounds(250, 400, 219, 80);

        jLabel48.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel48.setText("Nome");
        pnlCadastroFornecedor.add(jLabel48);
        jLabel48.setBounds(210, 120, 100, 18);

        jLabel49.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Fornecedor");
        pnlCadastroFornecedor.add(jLabel49);
        jLabel49.setBounds(150, 40, 390, 50);

        jLabel52.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel52.setText("Telefone");
        pnlCadastroFornecedor.add(jLabel52);
        jLabel52.setBounds(400, 190, 110, 20);

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background/bgFornecedor.png"))); // NOI18N
        pnlCadastroFornecedor.add(jLabel53);
        jLabel53.setBounds(660, 10, 620, 540);

        jLabel54.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel54.setText("E-mail");
        pnlCadastroFornecedor.add(jLabel54);
        jLabel54.setBounds(160, 190, 60, 18);

        jLabel55.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel55.setText("CNPJ");
        pnlCadastroFornecedor.add(jLabel55);
        jLabel55.setBounds(420, 120, 36, 18);

        jLabel56.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel56.setText("Código");
        pnlCadastroFornecedor.add(jLabel56);
        jLabel56.setBounds(120, 120, 80, 18);

        txtCadastroFornecedorCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecedorCodigo.setEnabled(false);
        pnlCadastroFornecedor.add(txtCadastroFornecedorCodigo);
        txtCadastroFornecedorCodigo.setBounds(120, 140, 53, 24);

        txtCadastroFornecedorNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFornecedor.add(txtCadastroFornecedorNome);
        txtCadastroFornecedorNome.setBounds(210, 140, 186, 24);

        ftxtCadastroFornecedorCnpj.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroFornecedorCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroFornecedorCnpjFocusGained(evt);
            }
        });
        pnlCadastroFornecedor.add(ftxtCadastroFornecedorCnpj);
        ftxtCadastroFornecedorCnpj.setBounds(420, 140, 190, 24);

        txtCadastroFornecedorEmail.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFornecedor.add(txtCadastroFornecedorEmail);
        txtCadastroFornecedorEmail.setBounds(160, 210, 220, 24);

        ftxtCadastroFornecedorTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroFornecedorTelefoneFocusGained(evt);
            }
        });
        pnlCadastroFornecedor.add(ftxtCadastroFornecedorTelefone);
        ftxtCadastroFornecedorTelefone.setBounds(400, 210, 140, 22);

        txtCadastroFornecedorComplemento.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFornecedor.add(txtCadastroFornecedorComplemento);
        txtCadastroFornecedorComplemento.setBounds(510, 270, 80, 24);

        jLabel58.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel58.setText("Rua");
        pnlCadastroFornecedor.add(jLabel58);
        jLabel58.setBounds(140, 250, 50, 18);

        txtCadastroFornecedorRua.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFornecedor.add(txtCadastroFornecedorRua);
        txtCadastroFornecedorRua.setBounds(140, 270, 260, 24);

        jLabel59.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel59.setText("Complemento");
        pnlCadastroFornecedor.add(jLabel59);
        jLabel59.setBounds(500, 250, 100, 18);

        jLabel61.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel61.setText("Número");
        pnlCadastroFornecedor.add(jLabel61);
        jLabel61.setBounds(420, 250, 54, 18);

        ftxtCadastroFornecedorNumero.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxtCadastroFornecedorNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtCadastroFornecedorNumeroFocusGained(evt);
            }
        });
        pnlCadastroFornecedor.add(ftxtCadastroFornecedorNumero);
        ftxtCadastroFornecedorNumero.setBounds(420, 270, 60, 24);

        txtCadastroFornecedorCidade.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        pnlCadastroFornecedor.add(txtCadastroFornecedorCidade);
        txtCadastroFornecedorCidade.setBounds(140, 330, 220, 24);

        jLabel62.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel62.setText("Estado");
        pnlCadastroFornecedor.add(jLabel62);
        jLabel62.setBounds(380, 310, 90, 18);

        jLabel63.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel63.setText("Cidade");
        pnlCadastroFornecedor.add(jLabel63);
        jLabel63.setBounds(140, 310, 90, 18);

        cmbCadastroFornecedorEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        cmbCadastroFornecedorEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre", "Alagoas", "Amapás", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins" }));
        pnlCadastroFornecedor.add(cmbCadastroFornecedorEstado);
        cmbCadastroFornecedorEstado.setBounds(380, 330, 230, 24);

        getContentPane().add(pnlCadastroFornecedor);
        pnlCadastroFornecedor.setBounds(12, 113, 1293, 580);

        pnlCadastroFornecimento.setAutoscrolls(true);
        pnlCadastroFornecimento.setOpaque(false);
        pnlCadastroFornecimento.setPreferredSize(new java.awt.Dimension(1290, 645));
        pnlCadastroFornecimento.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlCadastroFornecimentoComponentShown(evt);
            }
        });
        pnlCadastroFornecimento.setLayout(null);

        jScrollPane6.setOpaque(false);

        tblCadastroFornecimentoProduto.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        tblCadastroFornecimentoProduto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCadastroFornecimentoProduto.setToolTipText("");
        tblCadastroFornecimentoProduto.setOpaque(false);
        jScrollPane6.setViewportView(tblCadastroFornecimentoProduto);

        pnlCadastroFornecimento.add(jScrollPane6);
        jScrollPane6.setBounds(41, 278, 1204, 160);

        btnCadastroFornecimentoAddProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add/botaoAddProduto.png"))); // NOI18N
        btnCadastroFornecimentoAddProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastroFornecimentoAddProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoAddProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoAddProdutoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoAddProdutoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoAddProdutoMouseReleased(evt);
            }
        });
        btnCadastroFornecimentoAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroFornecimentoAddProdutoActionPerformed(evt);
            }
        });
        pnlCadastroFornecimento.add(btnCadastroFornecimentoAddProduto);
        btnCadastroFornecimentoAddProduto.setBounds(460, 200, 160, 60);

        jLabel50.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jLabel50.setText("Fornecedor");
        pnlCadastroFornecimento.add(jLabel50);
        jLabel50.setBounds(200, 20, 130, 18);

        txtCadastroFornecimentoCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoCodigo.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoCodigo);
        txtCadastroFornecimentoCodigo.setBounds(50, 70, 60, 24);

        jLabel51.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel51.setText("Código");
        pnlCadastroFornecimento.add(jLabel51);
        jLabel51.setBounds(50, 140, 54, 18);

        txtCadastroFornecimentoProdutoCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCadastroFornecimentoProdutoCodigoFocusLost(evt);
            }
        });
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoCodigo);
        txtCadastroFornecimentoProdutoCodigo.setBounds(50, 160, 70, 24);

        txtCadastroFornecimentoProdutoNome.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoProdutoNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoNome.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoNome);
        txtCadastroFornecimentoProdutoNome.setBounds(360, 160, 228, 24);

        jLabel57.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel57.setText("Nome");
        pnlCadastroFornecimento.add(jLabel57);
        jLabel57.setBounds(360, 140, 36, 18);

        txtCadastroFornecimentoProdutoMarca.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoProdutoMarca.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoMarca.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoMarca);
        txtCadastroFornecimentoProdutoMarca.setBounds(750, 160, 228, 24);

        jLabel60.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel60.setText("Marca");
        pnlCadastroFornecimento.add(jLabel60);
        jLabel60.setBounds(750, 140, 45, 18);

        txtCadastroFornecimentoProdutoQtdUnitaria.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoProdutoQtdUnitaria.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoQtdUnitaria.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoQtdUnitaria);
        txtCadastroFornecimentoProdutoQtdUnitaria.setBounds(610, 160, 117, 24);

        jLabel64.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel64.setText("Qtd. Unitária");
        pnlCadastroFornecimento.add(jLabel64);
        jLabel64.setBounds(610, 140, 117, 18);

        txtCadastroFornecimentoProdutoValorVenda.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoProdutoValorVenda.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoValorVenda.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoValorVenda);
        txtCadastroFornecimentoProdutoValorVenda.setBounds(1000, 160, 98, 24);

        jLabel65.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel65.setText("Valor de Venda");
        pnlCadastroFornecimento.add(jLabel65);
        jLabel65.setBounds(990, 140, 126, 18);

        txtCadastroFornecimentoProdutoValorTotal.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoProdutoValorTotal.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoValorTotal.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoValorTotal);
        txtCadastroFornecimentoProdutoValorTotal.setBounds(1130, 160, 98, 24);

        jLabel66.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel66.setText("Valor Total");
        pnlCadastroFornecimento.add(jLabel66);
        jLabel66.setBounds(1130, 140, 99, 18);

        txtCadastroFornecimentoProdutoQuantidade.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCadastroFornecimentoProdutoQuantidadeKeyReleased(evt);
            }
        });
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoQuantidade);
        txtCadastroFornecimentoProdutoQuantidade.setBounds(140, 160, 90, 24);

        jLabel67.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel67.setText("Quantidade");
        pnlCadastroFornecimento.add(jLabel67);
        jLabel67.setBounds(140, 140, 90, 18);

        txtCadastroFornecimentoProdutoValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoProdutoValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCadastroFornecimentoProdutoValorKeyReleased(evt);
            }
        });
        pnlCadastroFornecimento.add(txtCadastroFornecimentoProdutoValor);
        txtCadastroFornecimentoProdutoValor.setBounds(250, 160, 90, 24);

        jLabel68.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel68.setText("Valor");
        pnlCadastroFornecimento.add(jLabel68);
        jLabel68.setBounds(250, 140, 45, 18);

        jLabel69.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel69.setText("Código");
        pnlCadastroFornecimento.add(jLabel69);
        jLabel69.setBounds(50, 30, 70, 18);

        jLabel70.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel70.setText("Fornecimento");
        pnlCadastroFornecimento.add(jLabel70);
        jLabel70.setBounds(30, 50, 130, 20);

        jLabel71.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jLabel71.setText("Produto");
        pnlCadastroFornecimento.add(jLabel71);
        jLabel71.setBounds(50, 110, 90, 18);

        jLabel72.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel72.setText("Código");
        pnlCadastroFornecimento.add(jLabel72);
        jLabel72.setBounds(170, 50, 54, 18);

        txtCadastroFornecimentoFornecedorCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoFornecedorCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCadastroFornecimentoFornecedorCodigoFocusLost(evt);
            }
        });
        pnlCadastroFornecimento.add(txtCadastroFornecimentoFornecedorCodigo);
        txtCadastroFornecimentoFornecedorCodigo.setBounds(170, 70, 70, 24);

        txtCadastroFornecimentoFornecedorNome.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoFornecedorNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoFornecedorNome.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoFornecedorNome);
        txtCadastroFornecimentoFornecedorNome.setBounds(260, 70, 210, 24);

        jLabel73.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel73.setText("Nome");
        pnlCadastroFornecimento.add(jLabel73);
        jLabel73.setBounds(260, 50, 36, 18);

        txtCadastroFornecimentoFornecedorCnpj.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoFornecedorCnpj.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoFornecedorCnpj.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoFornecedorCnpj);
        txtCadastroFornecimentoFornecedorCnpj.setBounds(490, 70, 170, 24);

        jLabel74.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel74.setText("CNPJ");
        pnlCadastroFornecimento.add(jLabel74);
        jLabel74.setBounds(490, 50, 36, 18);

        txtCadastroFornecimentoFornecedorTelefone.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoFornecedorTelefone.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoFornecedorTelefone.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoFornecedorTelefone);
        txtCadastroFornecimentoFornecedorTelefone.setBounds(680, 70, 160, 24);

        jLabel75.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel75.setText("Telefone");
        pnlCadastroFornecimento.add(jLabel75);
        jLabel75.setBounds(680, 50, 80, 18);

        txtCadastroFornecimentoFornecedorCidade.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoFornecedorCidade.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoFornecedorCidade.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoFornecedorCidade);
        txtCadastroFornecimentoFornecedorCidade.setBounds(860, 70, 180, 24);

        jLabel76.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel76.setText("Cidade");
        pnlCadastroFornecimento.add(jLabel76);
        jLabel76.setBounds(860, 50, 80, 18);

        txtCadastroFornecimentoFornecedorEstado.setBackground(new java.awt.Color(240, 240, 240));
        txtCadastroFornecimentoFornecedorEstado.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtCadastroFornecimentoFornecedorEstado.setEnabled(false);
        pnlCadastroFornecimento.add(txtCadastroFornecimentoFornecedorEstado);
        txtCadastroFornecimentoFornecedorEstado.setBounds(1060, 70, 170, 24);

        jLabel77.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel77.setText("Estado");
        pnlCadastroFornecimento.add(jLabel77);
        jLabel77.setBounds(1060, 50, 80, 18);

        btnCadastroFornecimentoConsultarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"))); // NOI18N
        btnCadastroFornecimentoConsultarFornecedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastroFornecimentoConsultarFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarFornecedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarFornecedorMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarFornecedorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarFornecedorMouseReleased(evt);
            }
        });
        btnCadastroFornecimentoConsultarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroFornecimentoConsultarFornecedorActionPerformed(evt);
            }
        });
        pnlCadastroFornecimento.add(btnCadastroFornecimentoConsultarFornecedor);
        btnCadastroFornecimentoConsultarFornecedor.setBounds(320, 20, 60, 20);

        btnCadastroFornecimentoConsultarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"))); // NOI18N
        btnCadastroFornecimentoConsultarProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastroFornecimentoConsultarProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarProdutoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarProdutoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoConsultarProdutoMouseReleased(evt);
            }
        });
        btnCadastroFornecimentoConsultarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroFornecimentoConsultarProdutoActionPerformed(evt);
            }
        });
        pnlCadastroFornecimento.add(btnCadastroFornecimentoConsultarProduto);
        btnCadastroFornecimentoConsultarProduto.setBounds(140, 110, 60, 20);

        btnFinalizarFornecimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarFornecimento.png"))); // NOI18N
        btnFinalizarFornecimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinalizarFornecimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizarFornecimentoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizarFornecimentoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFinalizarFornecimentoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnFinalizarFornecimentoMouseReleased(evt);
            }
        });
        btnFinalizarFornecimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarFornecimentoActionPerformed(evt);
            }
        });
        pnlCadastroFornecimento.add(btnFinalizarFornecimento);
        btnFinalizarFornecimento.setBounds(530, 460, 219, 80);

        jLabel78.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        jLabel78.setText("Valor Total");
        pnlCadastroFornecimento.add(jLabel78);
        jLabel78.setBounds(1060, 470, 132, 23);

        lblCadastroFornecimentoValorTotal.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        lblCadastroFornecimentoValorTotal.setText("R$ 0.0");
        pnlCadastroFornecimento.add(lblCadastroFornecimentoValorTotal);
        lblCadastroFornecimentoValorTotal.setBounds(1060, 500, 150, 23);

        btnCadastroFornecimentoRemoverProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto.png"))); // NOI18N
        btnCadastroFornecimentoRemoverProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastroFornecimentoRemoverProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoRemoverProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoRemoverProdutoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoRemoverProdutoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCadastroFornecimentoRemoverProdutoMouseReleased(evt);
            }
        });
        btnCadastroFornecimentoRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroFornecimentoRemoverProdutoActionPerformed(evt);
            }
        });
        pnlCadastroFornecimento.add(btnCadastroFornecimentoRemoverProduto);
        btnCadastroFornecimentoRemoverProduto.setBounds(660, 200, 160, 60);

        getContentPane().add(pnlCadastroFornecimento);
        pnlCadastroFornecimento.setBounds(12, 110, 1293, 590);

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
       if (pnlSubMenu.isVisible()) {
            if (!this.isCadastro()) {
                ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuCadastro.png"));
                btnMenuCadastro.setIcon(i);
            }
        }
        else if (!this.isCadastro()) {
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
        if (pnlSubMenu.isVisible()) {
            if (this.isCadastro()) {
                ImageIcon i = new ImageIcon(getClass().getResource("/images/menu/botaoMenuConsulta.png"));
                btnMenuConsulta.setIcon(i);
            }
        }
        else {
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
        pnlCadastroCliente.setVisible(false);
        pnlCadastroFuncionario.setVisible(false);
        pnlCadastroProduto.setVisible(false);
        pnlCadastroFornecedor.setVisible(false);
        pnlCadastroFornecimento.setVisible(false);
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
        pnlCadastroCliente.setVisible(false);
        pnlCadastroFuncionario.setVisible(false);
        pnlCadastroProduto.setVisible(false);
        pnlCadastroFornecedor.setVisible(false);
        pnlCadastroFornecimento.setVisible(false);
        pnlSubMenu.setVisible(true);    
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuAgendaMouseExited(me);
        btnMenuServicoMouseExited(me);
        btnMenuVendaMouseExited(me);
        btnMenuCadastroMouseExited(me); 
    }//GEN-LAST:event_btnMenuConsultaActionPerformed

    private void btnMenuVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuVendaActionPerformed
        pnlSubMenu.setVisible(false);
        pnlServico.setVisible(false);
        pnlAgenda.setVisible(false);
        pnlCadastroCliente.setVisible(false);
        pnlCadastroFuncionario.setVisible(false);
        pnlCadastroProduto.setVisible(false);
        pnlCadastroFornecedor.setVisible(false);
        pnlCadastroFornecimento.setVisible(false);
        pnlVenda.setVisible(true);
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuAgendaMouseExited(me);
        btnMenuServicoMouseExited(me);
        btnMenuConsultaMouseExited(me);
        btnMenuCadastroMouseExited(me);
    }//GEN-LAST:event_btnMenuVendaActionPerformed

    private void btnMenuServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuServicoActionPerformed
        pnlSubMenu.setVisible(false);
        pnlAgenda.setVisible(false);
        pnlVenda.setVisible(false);
        pnlCadastroCliente.setVisible(false);
        pnlCadastroFuncionario.setVisible(false);
        pnlCadastroProduto.setVisible(false);
        pnlCadastroFornecedor.setVisible(false);
        pnlCadastroFornecimento.setVisible(false);
        pnlServico.setVisible(true);
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuAgendaMouseExited(me);
        btnMenuVendaMouseExited(me);
        btnMenuConsultaMouseExited(me);
        btnMenuCadastroMouseExited(me);
    }//GEN-LAST:event_btnMenuServicoActionPerformed

    private void btnMenuAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAgendaActionPerformed
        pnlSubMenu.setVisible(false);
        pnlServico.setVisible(false);
        pnlVenda.setVisible(false);
        pnlCadastroCliente.setVisible(false);
        pnlCadastroFuncionario.setVisible(false);
        pnlCadastroFornecimento.setVisible(false);
        pnlCadastroProduto.setVisible(false);
        pnlAgenda.setVisible(true);
        this.setCadastro(false);
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(this, 1, 1, 1, 1, 1, 1, cadastro);
        btnMenuServicoMouseExited(me);
        btnMenuVendaMouseExited(me);
        btnMenuConsultaMouseExited(me);
        btnMenuCadastroMouseExited(me);
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

    private void btnCadastrarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarClienteMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroCliente_Hover.png"));
        btnCadastrarCliente.setIcon(i);
    }//GEN-LAST:event_btnCadastrarClienteMouseEntered

    private void btnCadastrarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarClienteMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroCliente.png"));
        btnCadastrarCliente.setIcon(i);
    }//GEN-LAST:event_btnCadastrarClienteMouseExited

    private void btnCadastrarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarClienteMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroCliente_Pressed.png"));
        btnCadastrarCliente.setIcon(i);
    }//GEN-LAST:event_btnCadastrarClienteMousePressed

    private void btnCadastrarClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarClienteMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroCliente_Hover.png"));
        btnCadastrarCliente.setIcon(i);
    }//GEN-LAST:event_btnCadastrarClienteMouseReleased

    private void btnCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarClienteActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtCadastroClienteCodigo.getBorder();
        int aux = 0;
        if (txtCadastroClienteNome.getText().length() == 0) {
            aux = 1;
            txtCadastroClienteNome.setBorder(bordaVermelha);
        }
        if ((ftxtCadastroClienteTelefone.getText().length() == 0) || (ftxtCadastroClienteTelefone.getText().equals("(  )      -    "))) {
            aux = 1;
            ftxtCadastroClienteTelefone.setBorder(bordaVermelha);
        }
        if (ftxtCadastroClienteData.getText().length() == 0) {
            aux = 1;
            ftxtCadastroClienteData.setBorder(bordaVermelha);
        }
        if (aux == 1) {
            this.setMensagemDialog("Preencha todos os campos");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
            dialog.setVisible(true);
        }
        else {
            ftxtCadastroClienteTelefone.setBorder(bordaPadrao);
            txtCadastroClienteNome.setBorder(bordaPadrao);
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date data = df.parse(ftxtCadastroClienteData.getText());
                Cliente c = new Cliente(txtCadastroClienteNome.getText());
                c.setCpf(ftxtCadastroClienteCpf.getText());
                Date agora = new Date();
                if (data.before(agora)) {
                    ftxtCadastroClienteData.setBorder(bordaPadrao);
                    c.setDataAniversario(data);
                    c.setEmail(txtCadastroClienteEmail.getText());
                    c.setTelefone(ftxtCadastroClienteTelefone.getText());
                    Cadastro cad = new Cadastro();
                    try {
                        cad.gravarCliente(c);
                        this.setMensagemDialog("O cliente foi cadastrado com sucesso");
                        MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Cliente cadastrado");
                        dialog.setVisible(true);
                        limparTelaCadastroCliente();
                    } catch (ObjetoJaCadastradoException ex) {
                        ftxtCadastroClienteCpf.setBorder(bordaVermelha);
                        this.setMensagemDialog("Este CPF já está cadastrado");
                        MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe um CPF válido");
                        dialog.setVisible(true);
                    }
                }
                else {
                    ftxtCadastroClienteData.setBorder(bordaVermelha);
                    this.setMensagemDialog("Informe uma data de nascimento válida");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe uma data válida");
                    dialog.setVisible(true);
                }
            } catch (ParseException e) {
                ftxtCadastroClienteData.setBorder(bordaVermelha);
                this.setMensagemDialog("Informe uma data de nascimento válida");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe uma data válida");
                dialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnCadastrarClienteActionPerformed

    private void ftxtCadastroClienteCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroClienteCpfFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###.###.###-##");
            mask.install(ftxtCadastroClienteCpf);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroClienteCpfFocusGained

    private void btnMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuClienteActionPerformed
        if (this.isCadastro()) {
            pnlSubMenu.setVisible(false);
            pnlCadastroCliente.setVisible(true);
        }
        else {
            ConsultaClienteModal consulta = new ConsultaClienteModal(this, true);
            consulta.setVisible(true);
        }
    }//GEN-LAST:event_btnMenuClienteActionPerformed

    private void ftxtCadastroClienteTelefoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroClienteTelefoneFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("(##) #####-####");
            mask.install(ftxtCadastroClienteTelefone);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroClienteTelefoneFocusGained

    private void ftxtCadastroClienteDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroClienteDataFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##/##/####");
            mask.install(ftxtCadastroClienteData);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroClienteDataFocusGained

    private void pnlCadastroClienteComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCadastroClienteComponentShown
        limparTelaCadastroCliente();
    }//GEN-LAST:event_pnlCadastroClienteComponentShown

    private void btnCadastrarFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFuncionarioMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFuncionario_Hover.png"));
        btnCadastrarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFuncionarioMouseEntered

    private void btnCadastrarFuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFuncionarioMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFuncionario.png"));
        btnCadastrarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFuncionarioMouseExited

    private void btnCadastrarFuncionarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFuncionarioMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFuncionario_Pressed.png"));
        btnCadastrarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFuncionarioMousePressed

    private void btnCadastrarFuncionarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFuncionarioMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFuncionario_Hover.png"));
        btnCadastrarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFuncionarioMouseReleased

    private void btnCadastrarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarFuncionarioActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtCadastroClienteCodigo.getBorder();
        int aux = 0;
        if (txtCadastroFuncionarioLogin.getText().length() == 0) {
            aux = 1;
            txtCadastroFuncionarioLogin.setBorder(bordaVermelha);
        }
        if (txtCadastroFuncionarioCidade.getText().length() == 0) {
            aux = 1;
            txtCadastroFuncionarioCidade.setBorder(bordaVermelha);
        }
        if ((ftxtCadastroFuncionarioCpf.getText().length() == 0) || ("   .   .   -  ".equals(ftxtCadastroFuncionarioCpf.getText()))) {
            aux = 1;
            ftxtCadastroFuncionarioCpf.setBorder(bordaVermelha);
        }
        if ((ftxtCadastroFuncionarioTelefone.getText().length() == 0) || ( ftxtCadastroFuncionarioTelefone.getText().equals("(  )      -    "))) {
            aux = 1;
            ftxtCadastroFuncionarioTelefone.setBorder(bordaVermelha);
        }
        if (ptxtCadastroFuncionarioSenha.getText().length() == 0) {
            aux = 1;
            ptxtCadastroFuncionarioSenha.setBorder(bordaVermelha);
        }
        if (txtCadastroFuncionarioNome.getText().length() == 0) {
            aux = 1;
            txtCadastroFuncionarioNome.setBorder(bordaVermelha);
        }
        if (aux == 1) {
            this.setMensagemDialog("Preencha todos os campos");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
            dialog.setVisible(true);
        }
        else {
            try {
                txtCadastroFuncionarioCidade.setBorder(bordaPadrao);
                ftxtCadastroFuncionarioCpf.setBorder(bordaPadrao);
                ftxtCadastroFuncionarioTelefone.setBorder(bordaPadrao);
                ptxtCadastroFuncionarioSenha.setBorder(bordaPadrao);
                txtCadastroFuncionarioNome.setBorder(bordaPadrao);
                aux = 0;
                if (!ftxtCadastroFuncionarioNumero.getText().equals("   ")) {
                    String[] numero = ftxtCadastroFuncionarioNumero.getText().split(" ");
                    aux = parseInt(numero[0]);
                }
                String senha = new String(ptxtCadastroFuncionarioSenha.getPassword());
                Funcionario f = new Funcionario(txtCadastroFuncionarioLogin.getText(), senha, txtCadastroFuncionarioNome.getText());
                f.setCidade(txtCadastroFuncionarioCidade.getText());
                f.setComplemento(txtCadastroFuncionarioComplemento.getText());
                f.setCpf(ftxtCadastroFuncionarioCpf.getText());
                f.setEstado((String) cmbCadastroFuncionarioEstado.getSelectedItem());
                f.setNumero(aux);
                f.setRua(txtCadastroFuncionarioRua.getText());
                f.setTelefone(ftxtCadastroFuncionarioTelefone.getText());
                Cadastro cad = new Cadastro();
                try {
                    cad.gravarFuncionario(f);
                    this.setMensagemDialog("O funcionário foi cadastrado com sucesso");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Funcionário cadastrado");
                    dialog.setVisible(true);
                    limparTelaCadastroFuncionario();
                } catch (ObjetoJaCadastradoException ex) {
                    txtCadastroFuncionarioLogin.setBorder(bordaVermelha);
                    ftxtCadastroFuncionarioCpf.setBorder(bordaVermelha);
                    this.setMensagemDialog("Login ou CPF já cadastrados");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Login ou CPF já cadastrados");
                    dialog.setVisible(true);
                } catch (ChaveNulaException ex) {
                    txtCadastroFuncionarioLogin.setBorder(bordaVermelha);
                    this.setMensagemDialog("Informe o login");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe um login válido");
                    dialog.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                ftxtCadastroFuncionarioNumero.setBorder(bordaVermelha);
                this.setMensagemDialog("Informe um número de endereço válido");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe um número válido");
                dialog.setVisible(true);
            } catch (ArrayIndexOutOfBoundsException ex1) {
                //
            }
        }
    }//GEN-LAST:event_btnCadastrarFuncionarioActionPerformed

    private void ftxtCadastroFuncionarioCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroFuncionarioCpfFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###.###.###-##");
            mask.install(ftxtCadastroFuncionarioCpf);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroFuncionarioCpfFocusGained

    private void ftxtCadastroFuncionarioTelefoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroFuncionarioTelefoneFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("(##) #####-####");
            mask.install(ftxtCadastroFuncionarioTelefone);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroFuncionarioTelefoneFocusGained

    private void pnlCadastroFuncionarioComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCadastroFuncionarioComponentShown
        limparTelaCadastroFuncionario();
    }//GEN-LAST:event_pnlCadastroFuncionarioComponentShown

    private void btnMenuFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFuncionarioActionPerformed
        if (this.isCadastro()) {
            pnlSubMenu.setVisible(false);
            pnlCadastroFuncionario.setVisible(true);
        }
        else {
            ConsultaFuncionarioModal consulta = new ConsultaFuncionarioModal(this, true);
            consulta.setVisible(true);
        }
    }//GEN-LAST:event_btnMenuFuncionarioActionPerformed

    private void ftxtCadastroFuncionarioNumeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroFuncionarioNumeroFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###");
            mask.install(ftxtCadastroFuncionarioNumero);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroFuncionarioNumeroFocusGained

    private void pnlCadastroProdutoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCadastroProdutoComponentShown
        limparTelaCadastroProduto();
    }//GEN-LAST:event_pnlCadastroProdutoComponentShown

    private void ftxtCadastrarProdutoEstoqueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastrarProdutoEstoqueFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###");
            mask.install(ftxtCadastrarProdutoEstoque);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastrarProdutoEstoqueFocusGained

    private void btnCadastrarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarProdutoActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtCadastrarProdutoCodigo.getBorder();
        int aux = 0;
        if (txtCadastrarProdutoNome.getText().length() == 0) {
            aux = 1;
            txtCadastrarProdutoNome.setBorder(bordaVermelha);
        }
        if (txtCadastrarProdutoMarca.getText().length() == 0) {
            aux = 1;
            txtCadastrarProdutoMarca.setBorder(bordaVermelha);
        }
        if (txtCadastrarProdutoUnidade.getText().length() == 0) {
            aux = 1;
            txtCadastrarProdutoUnidade.setBorder(bordaVermelha);
        }
        if ((ftxtCadastrarProdutoEstoque.getText().length() == 0) || (ftxtCadastrarProdutoEstoque.getText().equals("   "))) {
            aux = 1;
            ftxtCadastrarProdutoEstoque.setBorder(bordaVermelha);
        }
        if ((ftxtCadastrarProdutoEstoqueMin.getText().length() == 0) || (ftxtCadastrarProdutoEstoqueMin.getText().equals(("   ")))) {
            aux = 1;
            ftxtCadastrarProdutoEstoqueMin.setBorder(bordaVermelha);
        }
        if ((ftxtCadastrarProdutoQtdUnitaria.getText().length() == 0) || (ftxtCadastrarProdutoQtdUnitaria.getText().equals("   "))) {
            aux = 1;
            ftxtCadastrarProdutoQtdUnitaria.setBorder(bordaVermelha);
        }
        if ((ftxtCadastrarProdutoValor.getText().length() == 0) || (ftxtCadastrarProdutoValor.getText().equals("R$    .  "))) {
            aux = 1;
            ftxtCadastrarProdutoValor.setBorder(bordaVermelha);
        }
        if (aux == 1) {
            this.setMensagemDialog("Preencha todos os campos");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
            dialog.setVisible(true);
        }
        else {
            try {
                txtCadastrarProdutoNome.setBorder(bordaPadrao);
                txtCadastrarProdutoMarca.setBorder(bordaPadrao);
                txtCadastrarProdutoUnidade.setBorder(bordaPadrao);
                ftxtCadastrarProdutoEstoque.setBorder(bordaPadrao);
                ftxtCadastrarProdutoEstoqueMin.setBorder(bordaPadrao);
                ftxtCadastrarProdutoQtdUnitaria.setBorder(bordaPadrao);
                ftxtCadastrarProdutoValor.setBorder(bordaPadrao);
                
                Produto p = new Produto(txtCadastrarProdutoNome.getText());
                String[] numero = ftxtCadastrarProdutoEstoque.getText().split(" ");
                p.setQtdEstoque(Integer.parseInt(numero[0]));
                try {
                    numero = ftxtCadastrarProdutoEstoqueMin.getText().split(" ");
                    p.setQtdEstoqueMin(Integer.parseInt(numero[0]));
                    try {
                        numero = ftxtCadastrarProdutoQtdUnitaria.getText().split(" ");
                        p.setQtdUnitaria(Float.parseFloat(numero[0]));
                        try {
                            numero = ftxtCadastrarProdutoValor.getText().split(" ");
                            p.setValor(Float.parseFloat(numero[1]));
                            p.setUnidade(txtCadastrarProdutoUnidade.getText());
                            p.setMarca(txtCadastrarProdutoMarca.getText());
                            Cadastro cad = new Cadastro();
                            cad.gravarProduto(p);
                            this.setMensagemDialog("O produto foi cadastrado com sucesso");
                            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Produto cadastrado");
                            dialog.setVisible(true);
                            limparTelaCadastroProduto();
                            
                        } catch (NumberFormatException e2) {
                            ftxtCadastrarProdutoQtdUnitaria.setBorder(bordaVermelha);
                            this.setMensagemDialog("Informe um valor válido");
                            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Valor inválido");
                            dialog.setVisible(true);
                        }
                    } catch (NumberFormatException e1) {
                        ftxtCadastrarProdutoQtdUnitaria.setBorder(bordaVermelha);
                        this.setMensagemDialog("Informe uma quantidade unitária válida");
                        MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Quantidade unitária inválida");
                        dialog.setVisible(true);
                    }
                } catch (NumberFormatException e) {
                    ftxtCadastrarProdutoEstoqueMin.setBorder(bordaVermelha);
                    this.setMensagemDialog("Informe um estoque mínimo válido");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Estoque mínimo inválido");
                    dialog.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                ftxtCadastrarProdutoEstoque.setBorder(bordaVermelha);
                this.setMensagemDialog("Informe um estoque válido");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Estoque inválido");
                dialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnCadastrarProdutoActionPerformed

    private void btnCadastrarProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarProdutoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroProduto_Hover.png"));
        btnCadastrarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastrarProdutoMouseReleased

    private void btnCadastrarProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarProdutoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroProduto_Pressed.png"));
        btnCadastrarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastrarProdutoMousePressed

    private void btnCadastrarProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarProdutoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroProduto.png"));
        btnCadastrarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastrarProdutoMouseExited

    private void btnCadastrarProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarProdutoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroProduto_Hover.png"));
        btnCadastrarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastrarProdutoMouseEntered

    private void ftxtCadastrarProdutoQtdUnitariaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastrarProdutoQtdUnitariaFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("####.##");
            mask.install(ftxtCadastrarProdutoQtdUnitaria);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastrarProdutoQtdUnitariaFocusGained

    private void ftxtCadastrarProdutoEstoqueMinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastrarProdutoEstoqueMinFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###");
            mask.install(ftxtCadastrarProdutoEstoqueMin);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastrarProdutoEstoqueMinFocusGained

    private void btnMenuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProdutoActionPerformed
        if (this.isCadastro()) {
            pnlSubMenu.setVisible(false);
            pnlCadastroProduto.setVisible(true);
        }
        else {
            ConsultaProdutoModal consulta = new ConsultaProdutoModal(this, true);
            consulta.setVisible(true);
        }
    }//GEN-LAST:event_btnMenuProdutoActionPerformed

    private void ftxtCadastrarProdutoValorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastrarProdutoValorFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("R$ ###.##");
            mask.install(ftxtCadastrarProdutoValor);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastrarProdutoValorFocusGained

    private void btnCadastrarFornecedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFornecedorMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFornecedor_Hover.png"));
        btnCadastrarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFornecedorMouseEntered

    private void btnCadastrarFornecedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFornecedorMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFornecedor.png"));
        btnCadastrarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFornecedorMouseExited

    private void btnCadastrarFornecedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFornecedorMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFornecedor_Pressed.png"));
        btnCadastrarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFornecedorMousePressed

    private void btnCadastrarFornecedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarFornecedorMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroFornecedor_Hover.png"));
        btnCadastrarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastrarFornecedorMouseReleased

    private void btnCadastrarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarFornecedorActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtCadastroFornecedorCodigo.getBorder();
        int aux = 0;
        if (txtCadastroFornecedorNome.getText().length() == 0) {
            txtCadastroFornecedorNome.setBorder(bordaVermelha);
            aux = 1;
        }
        if ((ftxtCadastroFornecedorCnpj.getText().length() == 0) || ftxtCadastroFornecedorCnpj.getText().equals("  .   .   /    -  ")) {
            ftxtCadastroFornecedorCnpj.setBorder(bordaVermelha);
            aux = 1;
        }
        if ((ftxtCadastroFornecedorTelefone.getText().length() == 0) || (ftxtCadastroFornecedorTelefone.getText().equals("(  )      -    "))) {
            ftxtCadastroFornecedorTelefone.setBorder(bordaVermelha);
            aux = 1;
        }
        if (aux == 1) {
            this.setMensagemDialog("Preencha todos os campos");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
            dialog.setVisible(true);
        }
        else {
            try {
                Fornecedor f = new Fornecedor(ftxtCadastroFornecedorCnpj.getText());
                txtCadastroFornecedorNome.setBorder(bordaPadrao);
                ftxtCadastroFornecedorCnpj.setBorder(bordaPadrao);
                ftxtCadastroFornecedorTelefone.setBorder(bordaPadrao);
                if (!ftxtCadastroFornecedorNumero.getText().equals("   ")) {
                    String[] numero = ftxtCadastroFornecedorNumero.getText().split(" ");
                    f.setNumero(Integer.parseInt(numero[0]));
                }
                ftxtCadastroFornecedorNumero.setBorder(bordaPadrao);
                f.setCidade(txtCadastroFornecedorCidade.getText());
                f.setComplemento(txtCadastroFornecedorComplemento.getText());
                f.setEmail(txtCadastroFornecedorEmail.getText());
                f.setEstado((String) cmbCadastroFornecedorEstado.getSelectedItem());
                f.setNome(txtCadastroFornecedorNome.getText());
                f.setRua(txtCadastroFornecedorRua.getText());
                f.setTelefone(ftxtCadastroFornecedorTelefone.getText());
                Cadastro cad = new Cadastro();
                try {
                    cad.gravarFornecedor(f);
                    this.setMensagemDialog("Fornecedor cadastrado com sucesso");
                    limparTelaCadastroFornecedor();
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Fornecedor cadastrado");
                    dialog.setVisible(true);
                } catch (ObjetoJaCadastradoException ex) {
                    ftxtCadastroFornecedorCnpj.setBorder(bordaVermelha);
                    this.setMensagemDialog("O CNPJ já está cadastrado");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - CNPJ já cadastrado");
                    dialog.setVisible(true);
                }
            } catch (NumberFormatException e) {
                ftxtCadastroFornecedorNumero.setBorder(bordaVermelha);
                this.setMensagemDialog("Informe um número de endereço válido");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Informe um número válido");
                dialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnCadastrarFornecedorActionPerformed

    private void ftxtCadastroFornecedorCnpjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroFornecedorCnpjFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##.###.###/####-##");
            mask.install(ftxtCadastroFornecedorCnpj);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroFornecedorCnpjFocusGained

    private void pnlCadastroFornecedorComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCadastroFornecedorComponentShown
       limparTelaCadastroFornecedor();
    }//GEN-LAST:event_pnlCadastroFornecedorComponentShown

    private void ftxtCadastroFornecedorNumeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroFornecedorNumeroFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###");
            mask.install(ftxtCadastroFornecedorNumero);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroFornecedorNumeroFocusGained

    private void btnMenuFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFornecedorActionPerformed
        if (this.isCadastro()) {
            pnlSubMenu.setVisible(false);
            pnlCadastroFornecedor.setVisible(true);
        }
        else {
            ConsultaFornecedorModal consulta = new ConsultaFornecedorModal(this, true);
            consulta.setVisible(true);
        }
    }//GEN-LAST:event_btnMenuFornecedorActionPerformed

    private void ftxtCadastroFornecedorTelefoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtCadastroFornecedorTelefoneFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("(##) #####-####");
            mask.install(ftxtCadastroFornecedorTelefone);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtCadastroFornecedorTelefoneFocusGained

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

    private void btnConsultarServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande_Hover.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMouseReleased

    private void btnConsultarServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande_Pressed.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMousePressed

    private void btnConsultarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMouseExited

    private void btnConsultarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarServicoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaGrande_Hover.png"));
        btnConsultarServico.setIcon(i);
    }//GEN-LAST:event_btnConsultarServicoMouseEntered

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

    private void ftxtValorFimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtValorFimFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("R$ ###.##");
            mask.install(ftxtValorFim);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtValorFimFocusGained

    private void ftxtValorInicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtValorInicioFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("R$ ###.##");
            mask.install(ftxtValorInicio);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtValorInicioFocusGained

    private void ftxtDataInicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtDataInicioFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##/##/##");
            mask.install(ftxtDataInicio);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtDataInicioFocusGained

    private void ftxtDataFimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtDataFimFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##/##/##");
            mask.install(ftxtDataFim);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxtDataFimFocusGained

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

    private void btnCadastroFornecimentoAddProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoAddProdutoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto_Hover.png"));
        btnCadastroFornecimentoAddProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoAddProdutoMouseEntered

    private void btnCadastroFornecimentoAddProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoAddProdutoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto.png"));
        btnCadastroFornecimentoAddProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoAddProdutoMouseExited

    private void btnCadastroFornecimentoAddProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoAddProdutoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto_Pressed.png"));
        btnCadastroFornecimentoAddProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoAddProdutoMousePressed

    private void btnCadastroFornecimentoAddProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoAddProdutoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/add/botaoAddProduto_Hover.png"));
        btnCadastroFornecimentoAddProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoAddProdutoMouseReleased

    private void btnCadastroFornecimentoAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoAddProdutoActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtPadrao.getBorder();
        int aux = 0;
        if (txtCadastroFornecimentoProdutoValor.getText().length() == 0) {
            aux = 1;
            txtCadastroFornecimentoProdutoValor.setBorder(bordaVermelha);
        }
        if (txtCadastroFornecimentoProdutoQuantidade.getText().length() == 0) {
            aux = 1;
            txtCadastroFornecimentoProdutoQuantidade.setBorder(bordaVermelha);
        }
        if (txtCadastroFornecimentoProdutoCodigo.getText().length() == 0) {
            aux = 1;
            txtCadastroFornecimentoProdutoCodigo.setBorder(bordaVermelha);
        }
        if (aux == 1) {
            this.setMensagemDialog("Preencha todos os campos");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Preencha todos os campos");
            dialog.setVisible(true);
        }
        else {
            if (txtCadastroFornecimentoProdutoNome.getText().length() == 0) {
                txtCadastroFornecimentoProdutoCodigo.setBorder(bordaVermelha);
                this.setMensagemDialog("Insira um código válido de produto");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Código inválido");
                dialog.setVisible(true);
            }
            else {
                try {
                    int quantidade = Integer.parseInt(txtCadastroFornecimentoProdutoQuantidade.getText());
                    txtCadastroFornecimentoProdutoCodigo.setBorder(bordaPadrao);
                    txtCadastroFornecimentoProdutoQuantidade.setBorder(bordaPadrao);
                    try {
                        float valor = Float.parseFloat(txtCadastroFornecimentoProdutoValor.getText());
                        txtCadastroFornecimentoProdutoValor.setBorder(bordaPadrao);
                        listaCadastroFornecimentoProduto.add(produtoFornecimento);
                        listaCadastroFornecimentoQuantidade.add(quantidade);
                        listaCadastroFornecimentoValor.add(valor);
                        tblCadastroFornecimentoProduto.setModel( new CadastroProdutoFornecimentoTableModel(listaCadastroFornecimentoProduto, listaCadastroFornecimentoQuantidade, listaCadastroFornecimentoValor));
                        valorTotalFornecimento += valor*quantidade;
                        lblCadastroFornecimentoValorTotal.setText("R$ " + valorTotalFornecimento);
                        limparProdutoFornecimento();
                        gerarTabelaCadastroFornecimentoProduto();
                    } catch (NumberFormatException ex) {
                        txtCadastroFornecimentoProdutoValor.setBorder(bordaVermelha);
                        this.setMensagemDialog("Insira um valor válido");
                        MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Valor inválido");
                        dialog.setVisible(true);
                    }
                } catch (NumberFormatException e) {
                    txtCadastroFornecimentoProdutoQuantidade.setBorder(bordaVermelha);
                    this.setMensagemDialog("Insira uma quantidade válida");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Quantidade inválida");
                    dialog.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_btnCadastroFornecimentoAddProdutoActionPerformed

    private void btnMenuFornecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFornecimentoActionPerformed
        if (this.isCadastro()) {
            pnlSubMenu.setVisible(false);
            pnlCadastroFornecimento.setVisible(true);
        }
        else {
            ConsultaFornecimentoModal consulta = new ConsultaFornecimentoModal(this, true);
            consulta.setVisible(true);
        }
    }//GEN-LAST:event_btnMenuFornecimentoActionPerformed

    private void txtCadastroFornecimentoProdutoCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCadastroFornecimentoProdutoCodigoFocusLost
        Consulta con = new Consulta();
        try {
            produtoFornecimento = con.encontrarProduto(Integer.parseInt(txtCadastroFornecimentoProdutoCodigo.getText()));
            txtCadastroFornecimentoProdutoMarca.setText(produtoFornecimento.getMarca());
            txtCadastroFornecimentoProdutoNome.setText(produtoFornecimento.getNome());
            txtCadastroFornecimentoProdutoQtdUnitaria.setText(produtoFornecimento.getQtdUnitaria() + " " + produtoFornecimento.getUnidade());
            txtCadastroFornecimentoProdutoValorVenda.setText(Float.toString(produtoFornecimento.getValor()));
            try {
                float valorTotal;
                if ((Integer.parseInt(txtCadastroFornecimentoProdutoQuantidade.getText()) > 0) && (Float.parseFloat(txtCadastroFornecimentoProdutoQuantidade.getText()) > 0)) {
                    valorTotal = Float.parseFloat(txtCadastroFornecimentoProdutoQuantidade.getText())*
                            Float.parseFloat(txtCadastroFornecimentoProdutoValor.getText());
                    txtCadastroFornecimentoProdutoValorTotal.setText(Float.toString(valorTotal));
                }
                else {
                    txtCadastroFornecimentoProdutoValorTotal.setText("");
                }
            } catch (NumberFormatException | NullPointerException e) {
                txtCadastroFornecimentoProdutoValorTotal.setText("");
            }
        } catch (NumberFormatException | NullPointerException e){
            limparProdutoFornecimento();
        }       
    }//GEN-LAST:event_txtCadastroFornecimentoProdutoCodigoFocusLost

    private void txtCadastroFornecimentoProdutoQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCadastroFornecimentoProdutoQuantidadeKeyReleased
        try {
            float valorTotal;
            if ((Float.parseFloat(txtCadastroFornecimentoProdutoQuantidade.getText()) > 0) && (Float.parseFloat(txtCadastroFornecimentoProdutoValor.getText()) > 0)) {
                valorTotal = Float.parseFloat(txtCadastroFornecimentoProdutoValor.getText())*Float.parseFloat(txtCadastroFornecimentoProdutoQuantidade.getText());
                txtCadastroFornecimentoProdutoValorTotal.setText(Float.toString(valorTotal));
            }
            else {
                txtCadastroFornecimentoProdutoValorTotal.setText("");
            }
        } catch (NumberFormatException e) {
            txtCadastroFornecimentoProdutoValorTotal.setText("");
        }
    }//GEN-LAST:event_txtCadastroFornecimentoProdutoQuantidadeKeyReleased

    private void txtCadastroFornecimentoProdutoValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCadastroFornecimentoProdutoValorKeyReleased
        try {
            float valorTotal;
            if ((Integer.parseInt(txtCadastroFornecimentoProdutoQuantidade.getText()) > 0) && (Float.parseFloat(txtCadastroFornecimentoProdutoValor.getText()) > 0)) {
                valorTotal = Float.parseFloat(txtCadastroFornecimentoProdutoValor.getText())*Float.parseFloat(txtCadastroFornecimentoProdutoQuantidade.getText());
                txtCadastroFornecimentoProdutoValorTotal.setText(Float.toString(valorTotal));
            }
            else {
                txtCadastroFornecimentoProdutoValorTotal.setText("");
            }
        } catch (NumberFormatException e) {
            txtCadastroFornecimentoProdutoValorTotal.setText("");
        }
    }//GEN-LAST:event_txtCadastroFornecimentoProdutoValorKeyReleased

    private void txtCadastroFornecimentoFornecedorCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCadastroFornecimentoFornecedorCodigoFocusLost
        Consulta con = new Consulta();
        try {
            fornecedorFornecimento = con.encontrarFornecedor(Integer.parseInt(txtCadastroFornecimentoFornecedorCodigo.getText()));
            txtCadastroFornecimentoFornecedorNome.setText(fornecedorFornecimento.getNome());
            txtCadastroFornecimentoFornecedorCnpj.setText(fornecedorFornecimento.getCnpj());
            txtCadastroFornecimentoFornecedorTelefone.setText(fornecedorFornecimento.getTelefone());
            txtCadastroFornecimentoFornecedorCidade.setText(fornecedorFornecimento.getCidade());
            txtCadastroFornecimentoFornecedorEstado.setText(fornecedorFornecimento.getEstado());
        } catch (NumberFormatException | NullPointerException e){
            limparFornecedorFornecimento();
        }       
    }//GEN-LAST:event_txtCadastroFornecimentoFornecedorCodigoFocusLost

    private void btnCadastroFornecimentoConsultarFornecedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarFornecedorMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
        btnCadastroFornecimentoConsultarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarFornecedorMouseEntered

    private void btnCadastroFornecimentoConsultarFornecedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarFornecedorMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
        btnCadastroFornecimentoConsultarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarFornecedorMouseExited

    private void btnCadastroFornecimentoConsultarFornecedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarFornecedorMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
        btnCadastroFornecimentoConsultarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarFornecedorMousePressed

    private void btnCadastroFornecimentoConsultarFornecedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarFornecedorMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
        btnCadastroFornecimentoConsultarFornecedor.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarFornecedorMouseReleased

    private void btnCadastroFornecimentoConsultarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarFornecedorActionPerformed
        ConsultaFornecedorModal consulta = new ConsultaFornecedorModal(this, true);
        consulta.setVisible(true);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarFornecedorActionPerformed

    private void btnCadastroFornecimentoConsultarProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarProdutoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
        btnCadastroFornecimentoConsultarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarProdutoMouseEntered

    private void btnCadastroFornecimentoConsultarProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarProdutoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
        btnCadastroFornecimentoConsultarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarProdutoMouseExited

    private void btnCadastroFornecimentoConsultarProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarProdutoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
        btnCadastroFornecimentoConsultarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarProdutoMousePressed

    private void btnCadastroFornecimentoConsultarProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarProdutoMouseReleased
       ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
        btnCadastroFornecimentoConsultarProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarProdutoMouseReleased

    private void btnCadastroFornecimentoConsultarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoConsultarProdutoActionPerformed
        ConsultaProdutoModal consulta = new ConsultaProdutoModal(this, true);
        consulta.setVisible(true);
    }//GEN-LAST:event_btnCadastroFornecimentoConsultarProdutoActionPerformed

    private void btnFinalizarFornecimentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarFornecimentoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarFornecimento_Hover.png"));
        btnFinalizarFornecimento.setIcon(i);
    }//GEN-LAST:event_btnFinalizarFornecimentoMouseEntered

    private void btnFinalizarFornecimentoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarFornecimentoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarFornecimento.png"));
        btnFinalizarFornecimento.setIcon(i);
    }//GEN-LAST:event_btnFinalizarFornecimentoMouseExited

    private void btnFinalizarFornecimentoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarFornecimentoMousePressed
       ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarFornecimento_Pressed.png"));
        btnFinalizarFornecimento.setIcon(i);
    }//GEN-LAST:event_btnFinalizarFornecimentoMousePressed

    private void btnFinalizarFornecimentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarFornecimentoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/finalizar/botaoFinalizarFornecimento_Hover.png"));
        btnFinalizarFornecimento.setIcon(i);
    }//GEN-LAST:event_btnFinalizarFornecimentoMouseReleased

    private void btnFinalizarFornecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarFornecimentoActionPerformed
        Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
        Border bordaPadrao = txtPadrao.getBorder();
        txtCadastroFornecimentoFornecedorCodigo.setBorder(bordaPadrao);
        if (listaCadastroFornecimentoProduto.isEmpty()) {
            txtCadastroFornecimentoProdutoCodigo.setBorder(bordaVermelha);
            txtCadastroFornecimentoProdutoValor.setBorder(bordaVermelha);
            txtCadastroFornecimentoProdutoQuantidade.setBorder(bordaVermelha);
            this.setMensagemDialog("Nenhum produto foi adicionado");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Nenhum produto foi adicionado");
            dialog.setVisible(true);
        }
        else if (txtCadastroFornecimentoFornecedorNome.getText().isEmpty()) {
            txtCadastroFornecimentoProdutoCodigo.setBorder(bordaPadrao);
            txtCadastroFornecimentoProdutoValor.setBorder(bordaPadrao);
            txtCadastroFornecimentoProdutoQuantidade.setBorder(bordaPadrao);
            txtCadastroFornecimentoFornecedorCodigo.setBorder(bordaVermelha);
            this.setMensagemDialog("Informe um fornecedor");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Nenhum fornecedor foi adicionado");
            dialog.setVisible(true);
        }
        else {
            txtCadastroFornecimentoProdutoCodigo.setBorder(bordaPadrao);
            txtCadastroFornecimentoProdutoValor.setBorder(bordaPadrao);
            txtCadastroFornecimentoProdutoQuantidade.setBorder(bordaPadrao);
            if (txtCadastroFornecimentoFornecedorNome.getText().length() == 0) {
                txtCadastroFornecimentoFornecedorCodigo.setBorder(bordaVermelha);
                this.setMensagemDialog("Informe um fornecedor válido");
                MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Fornecedor inválido");
                dialog.setVisible(true);
            }
            else {
                txtCadastroFornecimentoFornecedorCodigo.setBorder(bordaPadrao);
                Cadastro cad = new Cadastro();
                Compra c = new Compra();
                c.setData();
                try {
                    c.setFornecedor(fornecedorFornecimento);
                    for (int i = 0; i < listaCadastroFornecimentoProduto.size(); i++) {
                        c.addProduto(listaCadastroFornecimentoProduto.get(i).getCodigo(), listaCadastroFornecimentoValor.get(i), listaCadastroFornecimentoQuantidade.get(i));
                    }
                    c.setValorTotal(valorTotalFornecimento);
                    cad.gravarCompra(c);
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
                    // Aqui atualizaria o estoque do produto //
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
/* --------------------------------------------------------------------------------------------------- */
                    this.setMensagemDialog("O fornecimento foi cadastrado com sucesso");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Fornecimento cadastrado");
                    dialog.setVisible(true);
                    limparTelaCadastroFornecimento();
                } catch (ChaveNulaException ex) {
                    txtCadastroFornecimentoFornecedorCodigo.setBorder(bordaVermelha);
                    this.setMensagemDialog("Informe um fornecedor válido");
                    MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Fornecedor inválido");
                    dialog.setVisible(true);
                }
                
            }
        }
    }//GEN-LAST:event_btnFinalizarFornecimentoActionPerformed

    private void btnCadastroFornecimentoRemoverProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoRemoverProdutoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto_Hover.png"));
        btnCadastroFornecimentoRemoverProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoRemoverProdutoMouseEntered

    private void btnCadastroFornecimentoRemoverProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoRemoverProdutoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto.png"));
        btnCadastroFornecimentoRemoverProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoRemoverProdutoMouseExited

    private void btnCadastroFornecimentoRemoverProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoRemoverProdutoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto_Pressed.png"));
        btnCadastroFornecimentoRemoverProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoRemoverProdutoMousePressed

    private void btnCadastroFornecimentoRemoverProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoRemoverProdutoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/remover/botaoRemoverProduto_Hover.png"));
        btnCadastroFornecimentoRemoverProduto.setIcon(i);
    }//GEN-LAST:event_btnCadastroFornecimentoRemoverProdutoMouseReleased

    private void btnCadastroFornecimentoRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroFornecimentoRemoverProdutoActionPerformed
        int linha = tblCadastroFornecimentoProduto.getSelectedRow();
        if (linha == -1) {
            this.setMensagemDialog("Nenhum produto foi selecionado");
            MensagemOkModal dialog = new MensagemOkModal(this, true, this.getMensagemDialog(), "Erro - Produto não selecionado");
            dialog.setVisible(true);
        }
        else {
            this.setMensagemDialog("Você deseja remover " + tblCadastroFornecimentoProduto.getModel().getValueAt(linha, 1) + "?");
            MensagemOkCancelModal dialog = new MensagemOkCancelModal(this, true, this.getMensagemDialog(), "Confirmar - Remover produto");
            dialog.setVisible(true);
            if (dialog.getConfirmado()) {
                float valorTotalProduto = (Float) tblCadastroFornecimentoProduto.getModel().getValueAt(linha, 7);
                valorTotalFornecimento -= valorTotalProduto;
                lblCadastroFornecimentoValorTotal.setText("R$ " + valorTotalFornecimento);
                Consulta con = new Consulta();
                Produto p;
                p = con.encontrarProduto((Integer) tblCadastroFornecimentoProduto.getModel().getValueAt(linha, 0));
                valorTotalFornecimento -= listaCadastroFornecimentoValor.get(linha)*listaCadastroFornecimentoQuantidade.get(linha);
                lblCadastroFornecimentoValorTotal.setText("R$ " + valorTotalFornecimento);
                listaCadastroFornecimentoProduto.remove(linha);
                listaCadastroFornecimentoQuantidade.remove(linha);
                listaCadastroFornecimentoValor.remove(linha);
                tblCadastroFornecimentoProduto.setModel( new CadastroProdutoFornecimentoTableModel(listaCadastroFornecimentoProduto, listaCadastroFornecimentoQuantidade, listaCadastroFornecimentoValor));
                gerarTabelaCadastroFornecimentoProduto();
                this.setMensagemDialog("Produto removido com sucesso!");
                MensagemOkModal dialog2 = new MensagemOkModal(this, true, this.getMensagemDialog(), "Sucesso - Produto removido");
                dialog2.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnCadastroFornecimentoRemoverProdutoActionPerformed

    private void pnlCadastroFornecimentoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCadastroFornecimentoComponentShown
        limparTelaCadastroFornecimento();
    }//GEN-LAST:event_pnlCadastroFornecimentoComponentShown

    private void btnAlterarServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarServicoMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAlterarServico_Hover.png"));
        btnAlterarServico.setIcon(i);
    }//GEN-LAST:event_btnAlterarServicoMouseEntered

    private void btnAlterarServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarServicoMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAlterarServico.png"));
        btnAlterarServico.setIcon(i);
    }//GEN-LAST:event_btnAlterarServicoMouseExited

    private void btnAlterarServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarServicoMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAlterarServico_Pressed.png"));
        btnAlterarServico.setIcon(i);
    }//GEN-LAST:event_btnAlterarServicoMousePressed

    private void btnAlterarServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarServicoMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/agenda/botaoAlterarServico_Hover.png"));
        btnAlterarServico.setIcon(i);
    }//GEN-LAST:event_btnAlterarServicoMouseReleased

    private void btnAlterarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarServicoActionPerformed
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
    }//GEN-LAST:event_btnAlterarServicoActionPerformed

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
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
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
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
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
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
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
           ConsultaFuncionarioModal consulta = new ConsultaFuncionarioModal(this.getParent(), true);
           consulta.setVisible(true);
       }                                                       

       private void btnConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                    
           ConsultaClienteModal consulta = new ConsultaClienteModal(this.getParent(), true);
           consulta.setVisible(true);
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
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
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
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
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
    
    
    /**
    * Classe interna para um JDialog para consulta de clientes
    * 
    * @author Rafael Tavares
    */ 
   public class ConsultaClienteModal extends javax.swing.JDialog {
       List<Cliente> clientes;

       /**
       * Construtor da classe
       * @param parent
       * @param modal 
       */   
       public ConsultaClienteModal(java.awt.Frame parent, boolean modal) {
           super(parent, modal);
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
           initComponents();
           this.setLocationRelativeTo(null);
           this.getContentPane().setBackground(Color.PINK);
           txtPadrao.setVisible(false);
           clientes = new ArrayList();
           tblConsultaCliente.setSelectionModel(new ForcedListSelectionModel());
           tblConsultaCliente.setModel(new ConsultaClienteTableModel(clientes));
           gerarTabela();

       }

       /**
        * Método para auxiliar a gerar uma nova tabela
        */
       private void gerarTabela() {
           tblConsultaCliente.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
           tblConsultaCliente.getColumnModel().getColumn(0).setMaxWidth(65);
           tblConsultaCliente.getColumnModel().getColumn(3).setMaxWidth(160);
           tblConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(160);
           tblConsultaCliente.getColumnModel().getColumn(5).setMaxWidth(140);
           tblConsultaCliente.getColumnModel().getColumn(5).setPreferredWidth(140);
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
           jLabel1 = new javax.swing.JLabel();
           txtCodigo = new javax.swing.JTextField();
           chbCodigo = new javax.swing.JCheckBox();
           txtNome = new javax.swing.JTextField();
           chbNome = new javax.swing.JCheckBox();
           txtCpf = new javax.swing.JTextField();
           chbCpf = new javax.swing.JCheckBox();
           jScrollPane1 = new javax.swing.JScrollPane();
           tblConsultaCliente = new javax.swing.JTable();
           btnConsultar = new javax.swing.JButton();
           txtPadrao = new javax.swing.JTextField();

           setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
           setTitle("Consulta de Cliente");
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

           jLabel1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
           jLabel1.setText("Cliente");

           txtCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtCodigo.setEnabled(false);

           chbCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbCodigo.setText("Código");
           chbCodigo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbCodigoActionPerformed(evt);
               }
           });

           txtNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtNome.setEnabled(false);

           chbNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbNome.setText("Nome");
           chbNome.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbNomeActionPerformed(evt);
               }
           });

           txtCpf.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtCpf.setEnabled(false);

           chbCpf.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbCpf.setText("CPF");
           chbCpf.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbCpfActionPerformed(evt);
               }
           });

           tblConsultaCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           tblConsultaCliente.setModel(new javax.swing.table.DefaultTableModel(
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
           jScrollPane1.setViewportView(tblConsultaCliente);

           btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"))); // NOI18N
           btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
           btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseExited(evt);
               }
               public void mousePressed(java.awt.event.MouseEvent evt) {
                   btnConsultarMousePressed(evt);
               }
               public void mouseReleased(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseReleased(evt);
               }
           });
           btnConsultar.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   btnConsultarActionPerformed(evt);
               }
           });

           txtPadrao.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtPadrao.setFocusable(false);

           javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
           getContentPane().setLayout(layout);
           layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addContainerGap()
                   .addComponent(jScrollPane1)
                   .addContainerGap())
               .addGroup(layout.createSequentialGroup()
                   .addContainerGap()
                   .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                               .addComponent(txtCodigo)
                               .addComponent(chbCodigo))
                           .addGap(44, 44, 44)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addGroup(layout.createSequentialGroup()
                                   .addGap(74, 74, 74)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                       .addComponent(jLabel1)
                                       .addComponent(chbNome))))
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addGroup(layout.createSequentialGroup()
                                   .addGap(37, 37, 37)
                                   .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                   .addComponent(chbCpf)
                                   .addGap(69, 69, 69))))
                       .addGroup(layout.createSequentialGroup()
                           .addGap(180, 180, 180)
                           .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                   .addGap(138, 138, 138))
               .addGroup(layout.createSequentialGroup()
                   .addGap(371, 371, 371)
                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
           );
           layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                       .addGroup(layout.createSequentialGroup()
                           .addGap(135, 135, 135)
                           .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(33, 33, 33))
                       .addGroup(layout.createSequentialGroup()
                           .addContainerGap()
                           .addComponent(jLabel1)
                           .addGap(18, 18, 18)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(layout.createSequentialGroup()
                                       .addComponent(chbCpf)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(layout.createSequentialGroup()
                                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                           .addComponent(chbCodigo)
                                           .addComponent(chbNome))
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(18, 18, 18)))
                   .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(34, Short.MAX_VALUE))
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

       private void btnConsultarMouseEntered(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseExited(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"));
           btnConsultar.setIcon(i);
       }                                        

       private void btnConsultarMousePressed(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Pressed.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseReleased(java.awt.event.MouseEvent evt) {                                           
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                          

       private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {                                             
           Consulta con = new Consulta();
           Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
           Border bordaPadrao = txtPadrao.getBorder();
           txtCodigo.setBorder(bordaPadrao);
           txtCpf.setBorder(bordaPadrao);
           txtNome.setBorder(bordaPadrao);
           if (chbCodigo.isSelected()) {
                if (txtCodigo.getText().length() == 0) {
                     txtCodigo.setBorder(bordaVermelha);
                     MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o código", "Erro - Código não informado");
                     m.setVisible(true);
                }
                else {
                    try {
                        int cod = Integer.parseInt(txtCodigo.getText());
                        clientes = new ArrayList();
                        clientes.add(con.encontrarCliente(cod));
                        if (clientes.isEmpty()) {
                            MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Nenhum cliente foi encontrado", 
                                    "Nenhum cliente encontrado");
                            m.setVisible(true);
                            tblConsultaCliente.setModel(null);
                            gerarTabela();
                        }
                        else {
                            tblConsultaCliente.setModel(new ConsultaClienteTableModel(clientes));
                            gerarTabela();
                        }
                    } catch (NumberFormatException e) {
                       txtCodigo.setBorder(bordaVermelha);
                       MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe um código válido", "Erro - Código inválido");
                       m.setVisible(true);
                    } catch (NullPointerException ex) {
                        MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Nenhum cliente foi encontrado", 
                                "Nenhum cliente encontrado");
                        m.setVisible(true);
                    }
                }
           }
           else if (chbCpf.isSelected()) {
               if (txtCpf.getText().length() == 0) {
                   txtCpf.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe um CPF válido", "Erro - CPF inválido");
                   m.setVisible(true);
               }
               else {
                   try {
                        clientes = new ArrayList();
                        clientes.add(con.encontrarClienteCpf(txtCpf.getText()));
                        tblConsultaCliente.setModel(new ConsultaClienteTableModel(clientes));
                        gerarTabela();
                         if (clientes.isEmpty()) {
                              MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Nenhum cliente foi encontrado", 
                                      "Nenhum cliente encontrado");
                              m.setVisible(true);
                         } 
                    } catch (NullPointerException ex) {
                        MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Nenhum cliente foi encontrado", 
                                "Nenhum cliente encontrado");
                        m.setVisible(true);
                    }
               }
           }
           else if (chbNome.isSelected()) {
               if (txtNome.getText().length() == 0) {
                   txtNome.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe um nome", "Erro - Nome não informado");
                   m.setVisible(true);
               }
               else {         
                   try {
                        clientes = new ArrayList();
                        clientes = con.encontrarClienteNome(txtNome.getText());
                        tblConsultaCliente.setModel(new ConsultaClienteTableModel(clientes));
                        gerarTabela();
                         if (clientes.isEmpty()) {
                              MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Nenhum cliente foi encontrado", 
                                      "Nenhum cliente encontrado");
                              m.setVisible(true);
                         }
                    } catch (NullPointerException e) {
                        MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Nenhum cliente foi encontrado", 
                                "Nenhum cliente encontrado");
                        m.setVisible(true);
                    }
               }
           }
           else {
               MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Selecione algum filtro de busca", "Erro - Nenhum filtro foi selecionado");
               m.setVisible(true);
           }
       }                                            

       private void chbCodigoActionPerformed(java.awt.event.ActionEvent evt) {                                          
           if (chbCodigo.isSelected()) {
               chbCpf.setSelected(false);
               chbNome.setSelected(false);
               txtNome.setText("");
               txtCpf.setText("");
               txtNome.setEnabled(false);
               txtCpf.setEnabled(false);
               txtCodigo.setEnabled(true);
           }
           else {
               txtCodigo.setEnabled(false);
           }
       }                                         

       private void chbCpfActionPerformed(java.awt.event.ActionEvent evt) {                                       
           if (chbCpf.isSelected()) {
               chbCodigo.setSelected(false);
               chbNome.setSelected(false);
               txtCodigo.setText("");
               txtNome.setText("");
               txtCpf.setEnabled(true);
               txtCodigo.setEnabled(false);
               txtNome.setEnabled(false);
           }
           else {
               txtCpf.setEnabled(false);
           }
       }                                      

       private void chbNomeActionPerformed(java.awt.event.ActionEvent evt) {                                        
           if (chbNome.isSelected()) {
               chbCodigo.setSelected(false);
               chbCpf.setSelected(false);
               txtCodigo.setEnabled(false);
               txtCpf.setEnabled(false);
               txtCodigo.setText("");
               txtCpf.setText("");
               txtNome.setEnabled(true);
               txtCodigo.setEnabled(false);
               txtCpf.setEnabled(false);
           }
           else {
               txtNome.setEnabled(false);
           }
       }      
    
        // Variables declaration - do not modify                     
        private javax.swing.JButton btnConsultar;
        private javax.swing.JButton btnOk;
        private javax.swing.JCheckBox chbCodigo;
        private javax.swing.JCheckBox chbCpf;
        private javax.swing.JCheckBox chbNome;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable tblConsultaCliente;
        private javax.swing.JTextField txtCodigo;
        private javax.swing.JTextField txtCpf;
        private javax.swing.JTextField txtNome;
        private javax.swing.JTextField txtPadrao;
        // End of variables declaration     
   }

   
   /**
    * Classe interna para um JDialog para consulta de funcionarios
    * 
    * @author Rafael Tavares
    */ 
   public class ConsultaFuncionarioModal extends javax.swing.JDialog {
       List<Funcionario> funcionarios;

       /**
       * Construtor da classe
       * @param parent
       * @param modal 
       */   
       public ConsultaFuncionarioModal(java.awt.Frame parent, boolean modal) {
           super(parent, modal);
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
           initComponents();
           this.setLocationRelativeTo(null);
           this.getContentPane().setBackground(Color.PINK);
           txtPadrao.setVisible(false);
           funcionarios = new ArrayList();
           tblConsultaFuncionario.setSelectionModel(new ForcedListSelectionModel());
           tblConsultaFuncionario.setModel(new ConsultaFuncionarioTableModel(funcionarios));
           gerarTabela();

       }

       /**
        * Método para auxiliar a gerar uma nova tabela
        */
       private void gerarTabela() {
           tblConsultaFuncionario.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
           tblConsultaFuncionario.getColumnModel().getColumn(0).setMaxWidth(100);
           tblConsultaFuncionario.getColumnModel().getColumn(0).setMaxWidth(100);
           tblConsultaFuncionario.getColumnModel().getColumn(1).setMaxWidth(150);
           tblConsultaFuncionario.getColumnModel().getColumn(1).setPreferredWidth(150);
           tblConsultaFuncionario.getColumnModel().getColumn(2).setMaxWidth(200);
           tblConsultaFuncionario.getColumnModel().getColumn(2).setPreferredWidth(200);
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
            jLabel1 = new javax.swing.JLabel();
            txtLogin = new javax.swing.JTextField();
            chbCodigo = new javax.swing.JCheckBox();
            txtNome = new javax.swing.JTextField();
            chbNome = new javax.swing.JCheckBox();
            txtCpf = new javax.swing.JTextField();
            chbCpf = new javax.swing.JCheckBox();
            jScrollPane1 = new javax.swing.JScrollPane();
            tblConsultaFuncionario = new javax.swing.JTable();
            btnConsultar = new javax.swing.JButton();
            txtPadrao = new javax.swing.JTextField();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Consulta de Cliente");
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

            jLabel1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
            jLabel1.setText("Fúncionário");

            txtLogin.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtLogin.setEnabled(false);

            chbCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            chbCodigo.setText("Login");
            chbCodigo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chbCodigoActionPerformed(evt);
                }
            });

            txtNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtNome.setEnabled(false);

            chbNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            chbNome.setText("Nome");
            chbNome.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chbNomeActionPerformed(evt);
                }
            });

            txtCpf.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtCpf.setEnabled(false);

            chbCpf.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            chbCpf.setText("CPF");
            chbCpf.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chbCpfActionPerformed(evt);
                }
            });

            tblConsultaFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            tblConsultaFuncionario.setModel(new javax.swing.table.DefaultTableModel(
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
            jScrollPane1.setViewportView(tblConsultaFuncionario);

            btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"))); // NOI18N
            btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnConsultarMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnConsultarMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    btnConsultarMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    btnConsultarMouseReleased(evt);
                }
            });
            btnConsultar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnConsultarActionPerformed(evt);
                }
            });

            txtPadrao.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
            txtPadrao.setFocusable(false);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1)
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
                            .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(429, 429, 429))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(chbCodigo)
                                    .addGap(84, 84, 84)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(74, 74, 74)
                                    .addComponent(jLabel1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(95, 95, 95)
                                    .addComponent(chbNome)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chbCpf)
                                    .addGap(69, 69, 69)))
                            .addGap(181, 181, 181))))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(422, 422, 422))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(chbCpf)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(chbCodigo)
                                            .addComponent(chbNome))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE))
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

       private void btnConsultarMouseEntered(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseExited(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"));
           btnConsultar.setIcon(i);
       }                                        

       private void btnConsultarMousePressed(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Pressed.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseReleased(java.awt.event.MouseEvent evt) {                                           
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                          

       private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {                                             
           Consulta con = new Consulta();
           Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
           Border bordaPadrao = txtPadrao.getBorder();
           txtLogin.setBorder(bordaPadrao);
           txtCpf.setBorder(bordaPadrao);
           txtNome.setBorder(bordaPadrao);
           if (chbCodigo.isSelected()) {
              if (txtLogin.getText().length() == 0) {
                   txtLogin.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o login", "Erro - Login não informado");
                   m.setVisible(true);
              }
              else {
                   funcionarios = new ArrayList();
                   funcionarios.add(con.encontrarFuncionarioLogin(txtLogin.getText()));
                   tblConsultaFuncionario.setModel(new ConsultaFuncionarioTableModel(funcionarios));
                   gerarTabela();
              }
           }
           else if (chbCpf.isSelected()) {
               if (txtCpf.getText().length() == 0) {
                   txtCpf.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe um CPF válido", "Erro - CPF inválido");
                   m.setVisible(true);
               }
               else {
                   funcionarios = new ArrayList();
                   funcionarios.add(con.encontrarFuncionarioCpf(txtCpf.getText()));
                   tblConsultaFuncionario.setModel(new ConsultaFuncionarioTableModel(funcionarios));
                   gerarTabela();
               }
           }
           else if (chbNome.isSelected()) {
               if (txtNome.getText().length() == 0) {
                   txtNome.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Selecione algum filtro de busca", "Erro - Nenhum filtro foi selecionado");
                   m.setVisible(true);
               }
               else {                    
                   funcionarios = new ArrayList();
                   funcionarios = con.encontrarFuncionarioNome(txtNome.getText());
                   tblConsultaFuncionario.setModel(new ConsultaFuncionarioTableModel(funcionarios));
                   gerarTabela();
               }
           }
           else {
               MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Selecione algum filtro de busca", "Erro - Nenhum filtro foi selecionado");
               m.setVisible(true);
           }
       }                                            

       private void chbCodigoActionPerformed(java.awt.event.ActionEvent evt) {                                          
           if (chbCodigo.isSelected()) {
               chbCpf.setSelected(false);
               chbNome.setSelected(false);
               txtNome.setText("");
               txtCpf.setText("");
               txtNome.setEnabled(false);
               txtCpf.setEnabled(false);
               txtLogin.setEnabled(true);
           }
           else {
               txtLogin.setEnabled(false);
           }
       }                                         

       private void chbCpfActionPerformed(java.awt.event.ActionEvent evt) {                                       
           if (chbCpf.isSelected()) {
               chbCodigo.setSelected(false);
               chbNome.setSelected(false);
               txtLogin.setText("");
               txtNome.setText("");
               txtCpf.setEnabled(true);
               txtLogin.setEnabled(false);
               txtNome.setEnabled(false);
           }
           else {
               txtCpf.setEnabled(false);
           }
       }                                      

       private void chbNomeActionPerformed(java.awt.event.ActionEvent evt) {                                        
           if (chbNome.isSelected()) {
               chbCodigo.setSelected(false);
               chbCpf.setSelected(false);
               txtLogin.setEnabled(false);
               txtCpf.setEnabled(false);
               txtLogin.setText("");
               txtCpf.setText("");
               txtNome.setEnabled(true);
               txtLogin.setEnabled(false);
               txtCpf.setEnabled(false);
           }
           else {
               txtNome.setEnabled(false);
           }
       }                      
    
        // Variables declaration - do not modify                     
        private javax.swing.JButton btnConsultar;
        private javax.swing.JButton btnOk;
        private javax.swing.JCheckBox chbCodigo;
        private javax.swing.JCheckBox chbCpf;
        private javax.swing.JCheckBox chbNome;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable tblConsultaFuncionario;
        private javax.swing.JTextField txtCpf;
        private javax.swing.JTextField txtLogin;
        private javax.swing.JTextField txtNome;
        private javax.swing.JTextField txtPadrao;
        // End of variables declaration                   
    }  
   
   
   
   /**
    * Classe interna para um JDialog para consulta de produtos
    * 
    * @author Rafael Tavares
    */ 
   public class ConsultaProdutoModal extends javax.swing.JDialog {
       List<Produto> produtos;

       /**
       * Construtor da classe
       * @param parent
       * @param modal 
       */   
       public ConsultaProdutoModal(java.awt.Frame parent, boolean modal) {
           super(parent, modal);
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
           initComponents();
           this.setLocationRelativeTo(null);
           this.getContentPane().setBackground(Color.PINK);
           txtPadrao.setVisible(false);
           produtos = new ArrayList();
           tblConsultaProduto.setSelectionModel(new ForcedListSelectionModel());
           tblConsultaProduto.setModel(new ConsultaProdutoTableModel(produtos));
           gerarTabela();

       }

       /**
        * Método para auxiliar a gerar uma nova tabela
        */
       private void gerarTabela() {
           tblConsultaProduto.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
           tblConsultaProduto.getColumnModel().getColumn(0).setMaxWidth(65);
           tblConsultaProduto.getColumnModel().getColumn(4).setMaxWidth(100);
           tblConsultaProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
           tblConsultaProduto.getColumnModel().getColumn(5).setMaxWidth(110);
           tblConsultaProduto.getColumnModel().getColumn(5).setPreferredWidth(110);
           tblConsultaProduto.getColumnModel().getColumn(6).setMaxWidth(140);
           tblConsultaProduto.getColumnModel().getColumn(6).setPreferredWidth(140);
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
           jLabel1 = new javax.swing.JLabel();
           txtCodigo = new javax.swing.JTextField();
           chbCodigo = new javax.swing.JCheckBox();
           txtNome = new javax.swing.JTextField();
           chbNome = new javax.swing.JCheckBox();
           txtMarca = new javax.swing.JTextField();
           chbMarca = new javax.swing.JCheckBox();
           jScrollPane1 = new javax.swing.JScrollPane();
           tblConsultaProduto = new javax.swing.JTable();
           btnConsultar = new javax.swing.JButton();
           txtPadrao = new javax.swing.JTextField();

           setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
           setTitle("Consulta de Cliente");
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

           jLabel1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
           jLabel1.setText("Produto");

           txtCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtCodigo.setEnabled(false);

           chbCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbCodigo.setText("Código");
           chbCodigo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbCodigoActionPerformed(evt);
               }
           });

           txtNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtNome.setEnabled(false);

           chbNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbNome.setText("Nome");
           chbNome.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbNomeActionPerformed(evt);
               }
           });

           txtMarca.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtMarca.setEnabled(false);

           chbMarca.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbMarca.setText("Marca");
           chbMarca.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbMarcaActionPerformed(evt);
               }
           });

           tblConsultaProduto.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           tblConsultaProduto.setModel(new javax.swing.table.DefaultTableModel(
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
           jScrollPane1.setViewportView(tblConsultaProduto);

           btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"))); // NOI18N
           btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
           btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseExited(evt);
               }
               public void mousePressed(java.awt.event.MouseEvent evt) {
                   btnConsultarMousePressed(evt);
               }
               public void mouseReleased(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseReleased(evt);
               }
           });
           btnConsultar.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   btnConsultarActionPerformed(evt);
               }
           });

           txtPadrao.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtPadrao.setFocusable(false);

           javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
           getContentPane().setLayout(layout);
           layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addContainerGap()
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(layout.createSequentialGroup()
                           .addComponent(jScrollPane1)
                           .addContainerGap())
                       .addGroup(layout.createSequentialGroup()
                           .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
                           .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(429, 429, 429))
                       .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                           .addGap(0, 0, Short.MAX_VALUE)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                       .addComponent(chbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                       .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(43, 43, 43)
                                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                               .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                               .addGroup(layout.createSequentialGroup()
                                                   .addGap(74, 74, 74)
                                                   .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(126, 126, 126)
                                           .addComponent(chbNome)))
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(37, 37, 37)
                                           .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(91, 91, 91)
                                           .addComponent(chbMarca)))
                                   .addGap(181, 181, 181))
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGap(422, 422, 422))))))
           );
           layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                       .addGroup(layout.createSequentialGroup()
                           .addGap(135, 135, 135)
                           .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(33, 33, 33))
                       .addGroup(layout.createSequentialGroup()
                           .addContainerGap()
                           .addComponent(jLabel1)
                           .addGap(18, 18, 18)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(layout.createSequentialGroup()
                                       .addComponent(chbMarca)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                               .addGroup(layout.createSequentialGroup()
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                       .addComponent(chbCodigo)
                                       .addComponent(chbNome))
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                   .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(18, 18, 18)))
                   .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(18, 18, 18)
                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(23, Short.MAX_VALUE))
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

       private void btnConsultarMouseEntered(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseExited(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"));
           btnConsultar.setIcon(i);
       }                                        

       private void btnConsultarMousePressed(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Pressed.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseReleased(java.awt.event.MouseEvent evt) {                                           
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                          

       private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {                                             
           Consulta con = new Consulta();
           Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
           Border bordaPadrao = txtPadrao.getBorder();
           txtCodigo.setBorder(bordaPadrao);
           txtMarca.setBorder(bordaPadrao);
           txtNome.setBorder(bordaPadrao);
           if (chbCodigo.isSelected()) {
              if (txtCodigo.getText().length() == 0) {
                   txtCodigo.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o código", "Erro - Código não informado");
                   m.setVisible(true);
              }
              else {
                   try {
                       produtos = new ArrayList();
                       produtos.add(con.encontrarProduto(Integer.parseInt(txtCodigo.getText())));
                       tblConsultaProduto.setModel(new ConsultaProdutoTableModel(produtos));
                       gerarTabela();
                   } catch (NumberFormatException e) {
                       txtCodigo.setBorder(bordaVermelha);
                       MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe um código válido", "Erro - Código inválido");
                       m.setVisible(true);
                   }
              }
           }
           else if (chbMarca.isSelected()) {
               if (txtMarca.getText().length() == 0) {
                   txtMarca.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe uma marca válida", "Erro - Marca inválida");
                   m.setVisible(true);
               } else if (chbNome.isSelected()) {
                   if (txtNome.getText().length() == 0) {
                       txtNome.setBorder(bordaVermelha);
                       MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o nome do produto", "Erro - Nome não informado");
                       m.setVisible(true);
                   }
                   else {
                       List<Produto> produtosTemp = new ArrayList();
                       produtos = new ArrayList();
                       produtosTemp = (con.encontrarProdutoNome(txtNome.getText()));
                       for (int i = 0; i < produtosTemp.size(); i++) {
                           if (produtosTemp.get(i).getMarca().equals(txtMarca.getText())) {
                               produtos.add(produtosTemp.get(i));
                           }
                       }
                       tblConsultaProduto.setModel(new ConsultaProdutoTableModel(produtos));
                       gerarTabela();
                   }
               }
               else {
                   produtos = new ArrayList();
                   produtos = (con.encontrarProdutoMarca(txtMarca.getText()));
                   tblConsultaProduto.setModel(new ConsultaProdutoTableModel(produtos));
                   gerarTabela();
               }
           }
           else if (chbNome.isSelected()) {
               if (txtNome.getText().length() == 0) {
                   txtNome.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o nome do produto", "Erro - Nome não informado");
                   m.setVisible(true);
               }
               else {                    
                   produtos = new ArrayList();
                   produtos = con.encontrarProdutoNome(txtNome.getText());
                   tblConsultaProduto.setModel(new ConsultaProdutoTableModel(produtos));
                   gerarTabela();
               }
           }
           else {
               MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Selecione algum filtro de busca", "Erro - Nenhum filtro foi selecionado");
               m.setVisible(true);
           }
       }                                            

       private void chbCodigoActionPerformed(java.awt.event.ActionEvent evt) {                                          
           if (chbCodigo.isSelected()) {
               chbMarca.setSelected(false);
               chbNome.setSelected(false);
               txtNome.setText("");
               txtMarca.setText("");
               txtNome.setEnabled(false);
               txtMarca.setEnabled(false);
               txtCodigo.setEnabled(true);
           }
           else {
               txtCodigo.setEnabled(false);
           }
       }                                         

       private void chbMarcaActionPerformed(java.awt.event.ActionEvent evt) {                                         
           if (chbMarca.isSelected()) {
               chbCodigo.setSelected(false);
               txtCodigo.setText("");
               txtCodigo.setEnabled(false);
               txtMarca.setEnabled(true);
           }
           else {
               txtMarca.setEnabled(false);
           }
       }                                        

       private void chbNomeActionPerformed(java.awt.event.ActionEvent evt) {                                        
           if (chbNome.isSelected()) {
               chbCodigo.setSelected(false);
               txtCodigo.setEnabled(false);
               txtCodigo.setText("");
               txtNome.setEnabled(true);
           }
           else {
               txtNome.setEnabled(false);
           }
       }
   
        // Variables declaration - do not modify                     
        private javax.swing.JButton btnConsultar;
        private javax.swing.JButton btnOk;
        private javax.swing.JCheckBox chbCodigo;
        private javax.swing.JCheckBox chbMarca;
        private javax.swing.JCheckBox chbNome;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable tblConsultaProduto;
        private javax.swing.JTextField txtCodigo;
        private javax.swing.JTextField txtMarca;
        private javax.swing.JTextField txtNome;
        private javax.swing.JTextField txtPadrao;
        // End of variables declaration               
   }
   
   
   /**
    * Classe interna para um JDialog para consulta de fornecedores
    * 
    * @author Rafael Tavares
    */ 
   public class ConsultaFornecedorModal extends javax.swing.JDialog {
       List<Fornecedor> fornecedores;

       /**
       * Construtor da classeh
       * @param parent
       * @param modal 
       */   
       public ConsultaFornecedorModal(java.awt.Frame parent, boolean modal) {
           super(parent, modal);
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
           initComponents();
           this.setLocationRelativeTo(null);
           this.getContentPane().setBackground(Color.PINK);
           txtPadrao.setVisible(false);
           fornecedores = new ArrayList();
           tblConsultaFuncionario.setSelectionModel(new ForcedListSelectionModel());
           tblConsultaFuncionario.setModel(new ConsultaFornecedorTableModel(fornecedores));
           gerarTabela();

       }

       /**
        * Método para auxiliar a gerar uma nova tabela
        */
       private void gerarTabela() {
           tblConsultaFuncionario.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
           tblConsultaFuncionario.getColumnModel().getColumn(0).setMaxWidth(65);
           tblConsultaFuncionario.getColumnModel().getColumn(3).setMaxWidth(160);
           tblConsultaFuncionario.getColumnModel().getColumn(3).setPreferredWidth(160);
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
           jLabel1 = new javax.swing.JLabel();
           txtCodigo = new javax.swing.JTextField();
           chbCodigo = new javax.swing.JCheckBox();
           txtNome = new javax.swing.JTextField();
           chbNome = new javax.swing.JCheckBox();
           txtCpnj = new javax.swing.JTextField();
           chbCnpj = new javax.swing.JCheckBox();
           jScrollPane1 = new javax.swing.JScrollPane();
           tblConsultaFuncionario = new javax.swing.JTable();
           btnConsultar = new javax.swing.JButton();
           txtPadrao = new javax.swing.JTextField();

           setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
           setTitle("Consulta de Cliente");
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

           jLabel1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
           jLabel1.setText("Fornecedor");

           txtCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtCodigo.setEnabled(false);

           chbCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbCodigo.setText("Código");
           chbCodigo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbCodigoActionPerformed(evt);
               }
           });

           txtNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtNome.setEnabled(false);

           chbNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbNome.setText("Nome");
           chbNome.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbNomeActionPerformed(evt);
               }
           });

           txtCpnj.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtCpnj.setEnabled(false);

           chbCnpj.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           chbCnpj.setText("CNPJ");
           chbCnpj.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   chbCnpjActionPerformed(evt);
               }
           });

           tblConsultaFuncionario.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           tblConsultaFuncionario.setModel(new javax.swing.table.DefaultTableModel(
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
           jScrollPane1.setViewportView(tblConsultaFuncionario);

           btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"))); // NOI18N
           btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
           btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseExited(evt);
               }
               public void mousePressed(java.awt.event.MouseEvent evt) {
                   btnConsultarMousePressed(evt);
               }
               public void mouseReleased(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseReleased(evt);
               }
           });
           btnConsultar.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   btnConsultarActionPerformed(evt);
               }
           });

           txtPadrao.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtPadrao.setFocusable(false);

           javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
           getContentPane().setLayout(layout);
           layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addContainerGap()
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(layout.createSequentialGroup()
                           .addComponent(jScrollPane1)
                           .addContainerGap())
                       .addGroup(layout.createSequentialGroup()
                           .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addGroup(layout.createSequentialGroup()
                                   .addGap(220, 220, 220)
                                   .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGap(248, 248, 248))
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                       .addComponent(chbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                       .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(84, 84, 84)
                                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                               .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                               .addGroup(layout.createSequentialGroup()
                                                   .addGap(74, 74, 74)
                                                   .addComponent(jLabel1))))
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(166, 166, 166)
                                           .addComponent(chbNome)))
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(37, 37, 37)
                                           .addComponent(txtCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                       .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                           .addComponent(chbCnpj)
                                           .addGap(69, 69, 69)))))
                           .addGap(222, 222, 222))))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                   .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(466, 466, 466))
           );
           layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                       .addGroup(layout.createSequentialGroup()
                           .addGap(135, 135, 135)
                           .addComponent(txtPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(33, 33, 33))
                       .addGroup(layout.createSequentialGroup()
                           .addContainerGap()
                           .addComponent(jLabel1)
                           .addGap(18, 18, 18)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(layout.createSequentialGroup()
                                       .addComponent(chbCnpj)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(txtCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(layout.createSequentialGroup()
                                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                           .addComponent(chbCodigo)
                                           .addComponent(chbNome))
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(18, 18, 18)))
                   .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(18, 18, 18)
                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(23, Short.MAX_VALUE))
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

       private void btnConsultarMouseEntered(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseExited(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"));
           btnConsultar.setIcon(i);
       }                                        

       private void btnConsultarMousePressed(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Pressed.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseReleased(java.awt.event.MouseEvent evt) {                                           
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                          

       private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {                                             
           Consulta con = new Consulta();
           Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
           Border bordaPadrao = txtPadrao.getBorder();
           txtCodigo.setBorder(bordaPadrao);
           txtCpnj.setBorder(bordaPadrao);
           txtNome.setBorder(bordaPadrao);
           if (chbCodigo.isSelected()) {
              if (txtCodigo.getText().length() == 0) {
                   txtCodigo.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o login", "Erro - Login não informado");
                   m.setVisible(true);
              }
              else {
                   fornecedores = new ArrayList();
                   fornecedores.add(con.encontrarFornecedor(Integer.parseInt(txtCodigo.getText())));
                   tblConsultaFuncionario.setModel(new ConsultaFornecedorTableModel(fornecedores));
                   gerarTabela();
              }
           }
           else if (chbCnpj.isSelected()) {
               if (txtCpnj.getText().length() == 0) {
                   txtCpnj.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe um CNPJ válido", "Erro - CPF inválido");
                   m.setVisible(true);
               }
               else {
                   fornecedores = new ArrayList();
                   fornecedores.add(con.encontrarFornecedorCnpj(txtCpnj.getText()));
                   tblConsultaFuncionario.setModel(new ConsultaFornecedorTableModel(fornecedores));
                   gerarTabela();
               }
           }
           else if (chbNome.isSelected()) {
               if (txtNome.getText().length() == 0) {
                   txtNome.setBorder(bordaVermelha);
                   MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o nome do fornecedor", "Erro - Nome não fornecido");
                   m.setVisible(true);
               }
               else {                    
                   fornecedores = new ArrayList();
                   fornecedores = con.encontrarFornecedorNome(txtNome.getText());
                   tblConsultaFuncionario.setModel(new ConsultaFornecedorTableModel(fornecedores));
                   gerarTabela();
               }
           }
           else {
               MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Selecione algum filtro de busca", "Erro - Nenhum filtro foi selecionado");
               m.setVisible(true);
           }
       }                                            

       private void chbCodigoActionPerformed(java.awt.event.ActionEvent evt) {                                          
           if (chbCodigo.isSelected()) {
               chbCnpj.setSelected(false);
               chbNome.setSelected(false);
               txtNome.setText("");
               txtCpnj.setText("");
               txtNome.setEnabled(false);
               txtCpnj.setEnabled(false);
               txtCodigo.setEnabled(true);
           }
           else {
               txtCodigo.setEnabled(false);
           }
       }                                         

       private void chbCnpjActionPerformed(java.awt.event.ActionEvent evt) {                                        
           if (chbCnpj.isSelected()) {
               chbCodigo.setSelected(false);
               chbNome.setSelected(false);
               txtCodigo.setText("");
               txtNome.setText("");
               txtCpnj.setEnabled(true);
               txtCodigo.setEnabled(false);
               txtNome.setEnabled(false);
           }
           else {
               txtCpnj.setEnabled(false);
           }
       }                                       

       private void chbNomeActionPerformed(java.awt.event.ActionEvent evt) {                                        
           if (chbNome.isSelected()) {
               chbCodigo.setSelected(false);
               chbCnpj.setSelected(false);
               txtCodigo.setEnabled(false);
               txtCpnj.setEnabled(false);
               txtCodigo.setText("");
               txtCpnj.setText("");
               txtNome.setEnabled(true);
               txtCodigo.setEnabled(false);
               txtCpnj.setEnabled(false);
           }
           else {
               txtNome.setEnabled(false);
           }
       }        
    
        // Variables declaration - do not modify                     
        private javax.swing.JButton btnConsultar;
        private javax.swing.JButton btnOk;
        private javax.swing.JCheckBox chbCnpj;
        private javax.swing.JCheckBox chbCodigo;
        private javax.swing.JCheckBox chbNome;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable tblConsultaFuncionario;
        private javax.swing.JTextField txtCodigo;
        private javax.swing.JTextField txtCpnj;
        private javax.swing.JTextField txtNome;
        private javax.swing.JTextField txtPadrao;
        // End of variables declaration    
   }
   
   /**
    * Classe interna para um JDialog para consulta de fornecimento
    * 
    * @author Rafael Tavares
    */ 
   public class ConsultaFornecimentoModal extends javax.swing.JDialog {
       Compra fornecimento;
       List<Produto> produtos;

       /**
       * Construtor da classe
       * @param parent
       * @param modal 
       */   
       public ConsultaFornecimentoModal(java.awt.Frame parent, boolean modal) {
            super(parent, modal);
            ImageIcon img = new ImageIcon(MenuInicial.class.getResource("/images/background/mini-icon.png"));
            this.setIconImage(img.getImage());
            initComponents();
            this.setLocationRelativeTo(null);
            this.getContentPane().setBackground(Color.PINK);
            tblConsultaFornecimento.setSelectionModel(new ForcedListSelectionModel());
            tblConsultaFornecimento.setModel(new CadastroProdutoFornecimentoTableModel(null, null, null));
            txtCodigoFornecedor.setDisabledTextColor(Color.BLACK);
            txtData.setDisabledTextColor(Color.BLACK);
            txtNomeFornecedor.setDisabledTextColor(Color.BLACK);
            txtValorTotal.setDisabledTextColor(Color.BLACK);
            txtCodigo.setDisabledTextColor(Color.BLACK);
            gerarTabela();

       }

       /**
        * Método para auxiliar a gerar uma nova tabela
        */
       private void gerarTabela() {
           tblConsultaFornecimento.getTableHeader().setFont(new Font("Courie", Font.BOLD, 15));
           tblConsultaFornecimento.getColumnModel().getColumn(0).setMaxWidth(65);
           tblConsultaFornecimento.getColumnModel().getColumn(5).setMaxWidth(130);
           tblConsultaFornecimento.getColumnModel().getColumn(5).setPreferredWidth(130);
           tblConsultaFornecimento.getColumnModel().getColumn(6).setMaxWidth(130);
           tblConsultaFornecimento.getColumnModel().getColumn(6).setPreferredWidth(130);
           tblConsultaFornecimento.getColumnModel().getColumn(7).setMaxWidth(130);
           tblConsultaFornecimento.getColumnModel().getColumn(7).setPreferredWidth(130);
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
           jLabel1 = new javax.swing.JLabel();
           txtCodigo = new javax.swing.JTextField();
           jScrollPane1 = new javax.swing.JScrollPane();
           tblConsultaFornecimento = new javax.swing.JTable();
           btnConsultar = new javax.swing.JButton();
           jLabel2 = new javax.swing.JLabel();
           txtData = new javax.swing.JTextField();
           jLabel3 = new javax.swing.JLabel();
           txtValorTotal = new javax.swing.JTextField();
           jLabel4 = new javax.swing.JLabel();
           txtCodigoFornecedor = new javax.swing.JTextField();
           jLabel5 = new javax.swing.JLabel();
           txtNomeFornecedor = new javax.swing.JTextField();

           setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
           setTitle("Consulta de Cliente");
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

           jLabel1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
           jLabel1.setText("Fornecimento");

           txtCodigo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N

           tblConsultaFornecimento.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           tblConsultaFornecimento.setModel(new javax.swing.table.DefaultTableModel(
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
           jScrollPane1.setViewportView(tblConsultaFornecimento);

           btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"))); // NOI18N
           btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
           btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseExited(evt);
               }
               public void mousePressed(java.awt.event.MouseEvent evt) {
                   btnConsultarMousePressed(evt);
               }
               public void mouseReleased(java.awt.event.MouseEvent evt) {
                   btnConsultarMouseReleased(evt);
               }
           });
           btnConsultar.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                   btnConsultarActionPerformed(evt);
               }
           });

           jLabel2.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           jLabel2.setText("Código");

           txtData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtData.setEnabled(false);

           jLabel3.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           jLabel3.setText("Data");

           txtValorTotal.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtValorTotal.setEnabled(false);

           jLabel4.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           jLabel4.setText("Valor Total");

           txtCodigoFornecedor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtCodigoFornecedor.setEnabled(false);

           jLabel5.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           jLabel5.setText("Fornecedor");

           txtNomeFornecedor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
           txtNomeFornecedor.setEnabled(false);

           javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
           getContentPane().setLayout(layout);
           layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addContainerGap()
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(layout.createSequentialGroup()
                           .addComponent(jScrollPane1)
                           .addContainerGap())
                       .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                           .addGap(0, 211, Short.MAX_VALUE)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGap(466, 466, 466))
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                       .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                       .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGap(18, 18, 18)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                       .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                       .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGap(18, 18, 18)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                       .addGroup(layout.createSequentialGroup()
                                           .addComponent(txtCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                           .addComponent(txtNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                   .addGap(178, 178, 178))
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addComponent(jLabel1)
                                   .addGap(485, 485, 485))
                               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                   .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGap(473, 473, 473))))))
           );
           layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                   .addContainerGap()
                   .addComponent(jLabel1)
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                       .addGroup(layout.createSequentialGroup()
                           .addComponent(jLabel5)
                           .addGap(27, 27, 27))
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                               .addComponent(jLabel2)
                               .addGap(3, 3, 3)
                               .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                           .addGroup(layout.createSequentialGroup()
                               .addComponent(jLabel3)
                               .addGap(3, 3, 3)
                               .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                       .addGroup(layout.createSequentialGroup()
                           .addComponent(jLabel4)
                           .addGap(3, 3, 3)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                               .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addComponent(txtCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addComponent(txtNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                   .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                   .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(18, 18, 18)
                   .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

       private void btnConsultarMouseEntered(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseExited(java.awt.event.MouseEvent evt) {                                         
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"));
           btnConsultar.setIcon(i);
       }                                        

       private void btnConsultarMousePressed(java.awt.event.MouseEvent evt) {                                          
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Pressed.png"));
           btnConsultar.setIcon(i);
       }                                         

       private void btnConsultarMouseReleased(java.awt.event.MouseEvent evt) {                                           
           ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
           btnConsultar.setIcon(i);
       }                                          

       private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {                                             
           Consulta con = new Consulta();
           Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
           Border bordaPadrao = txtValorTotal.getBorder();
           txtCodigo.setBorder(bordaPadrao);
           if (txtCodigo.getText().length() == 0) {
                txtCodigo.setBorder(bordaVermelha);
                MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Informe o código", "Erro - Código não informado");
                m.setVisible(true);
           }
           else {
               try{
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                fornecimento = con.encontrarCompra(Integer.parseInt(txtCodigo.getText()));
                txtCodigoFornecedor.setText(Integer.toString(fornecimento.getFornecedor().getCodigo()));
                txtData.setText(df.format(fornecimento.getData()));
                txtNomeFornecedor.setText(fornecimento.getFornecedor().getNome());
                txtValorTotal.setText(String.format("%.2f", (fornecimento.getValorTotal())));
                produtos = new ArrayList();
                for (int i = 0; i < fornecimento.getProdutos().size(); i++) {
                    produtos.add(con.encontrarProduto(fornecimento.getProdutos().get(i)));
                }
                tblConsultaFornecimento.setModel(new CadastroProdutoFornecimentoTableModel(produtos, fornecimento.getQuantidade(), fornecimento.getValores()));
                gerarTabela();
               } catch (NullPointerException e) {
                    MensagemOkModal m = new MensagemOkModal((Frame) this.getParent(), true, "Fornecimento não encontrado", "Erro - Fornecimento não encontrado");
                    m.setVisible(true);
                    txtCodigoFornecedor.setText("");
                    txtData.setText("");
                    txtNomeFornecedor.setText("");
                    txtValorTotal.setText("");
                    txtCodigo.setText("");
                    tblConsultaFornecimento.setModel(new CadastroProdutoFornecimentoTableModel(null, null, null));
               }
           }
       }                

       // Variables declaration - do not modify                     
       private javax.swing.JButton btnConsultar;
       private javax.swing.JButton btnOk;
       private javax.swing.JLabel jLabel1;
       private javax.swing.JLabel jLabel2;
       private javax.swing.JLabel jLabel3;
       private javax.swing.JLabel jLabel4;
       private javax.swing.JLabel jLabel5;
       private javax.swing.JScrollPane jScrollPane1;
       private javax.swing.JTable tblConsultaFornecimento;
       private javax.swing.JTextField txtCodigo;
       private javax.swing.JTextField txtCodigoFornecedor;
       private javax.swing.JTextField txtData;
       private javax.swing.JTextField txtNomeFornecedor;
       private javax.swing.JTextField txtValorTotal;
       // End of variables declaration                   
   }
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarProdutoVenda;
    private javax.swing.JButton btnAdicionarSerivocVenda;
    private javax.swing.JButton btnAgendarServico;
    private javax.swing.JButton btnAlterarServico;
    private javax.swing.JButton btnCadastrarCliente;
    private javax.swing.JButton btnCadastrarFornecedor;
    private javax.swing.JButton btnCadastrarFuncionario;
    private javax.swing.JButton btnCadastrarProduto;
    private javax.swing.JButton btnCadastrarServico;
    private javax.swing.JButton btnCadastroFornecimentoAddProduto;
    private javax.swing.JButton btnCadastroFornecimentoConsultarFornecedor;
    private javax.swing.JButton btnCadastroFornecimentoConsultarProduto;
    private javax.swing.JButton btnCadastroFornecimentoRemoverProduto;
    private javax.swing.JButton btnCancelarServico;
    private javax.swing.JButton btnConsultarServico;
    private javax.swing.JButton btnFinalizarFornecimento;
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
    private javax.swing.JComboBox<String> cmbCadastroFornecedorEstado;
    private javax.swing.JComboBox<String> cmbCadastroFuncionarioEstado;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JFormattedTextField ftxtCadastrarProdutoEstoque;
    private javax.swing.JFormattedTextField ftxtCadastrarProdutoEstoqueMin;
    private javax.swing.JFormattedTextField ftxtCadastrarProdutoQtdUnitaria;
    private javax.swing.JFormattedTextField ftxtCadastrarProdutoValor;
    private javax.swing.JFormattedTextField ftxtCadastroClienteCpf;
    private javax.swing.JFormattedTextField ftxtCadastroClienteData;
    private javax.swing.JFormattedTextField ftxtCadastroClienteTelefone;
    private javax.swing.JFormattedTextField ftxtCadastroFornecedorCnpj;
    private javax.swing.JFormattedTextField ftxtCadastroFornecedorNumero;
    private javax.swing.JFormattedTextField ftxtCadastroFornecedorTelefone;
    private javax.swing.JFormattedTextField ftxtCadastroFuncionarioCpf;
    private javax.swing.JFormattedTextField ftxtCadastroFuncionarioNumero;
    private javax.swing.JFormattedTextField ftxtCadastroFuncionarioTelefone;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCadastroFornecimentoValorTotal;
    private javax.swing.JLabel lblDataAte;
    private javax.swing.JLabel lblValorAte;
    private javax.swing.JLabel lblValorTotalVenda;
    private javax.swing.JPanel pnlAgenda;
    private javax.swing.JPanel pnlCadastroCliente;
    private javax.swing.JPanel pnlCadastroFornecedor;
    private javax.swing.JPanel pnlCadastroFornecimento;
    private javax.swing.JPanel pnlCadastroFuncionario;
    private javax.swing.JPanel pnlCadastroProduto;
    private javax.swing.JPanel pnlServico;
    private javax.swing.JPanel pnlSubMenu;
    private javax.swing.JPanel pnlVenda;
    private javax.swing.JPasswordField ptxtCadastroFuncionarioSenha;
    private javax.swing.JTable tblAgenda;
    private javax.swing.JTable tblCadastroFornecimentoProduto;
    private javax.swing.JTable tblProdutoVenda;
    private javax.swing.JTable tblServico;
    private javax.swing.JTable tblServicoVenda;
    private javax.swing.JTextField txtCadastrarProdutoCodigo;
    private javax.swing.JTextField txtCadastrarProdutoMarca;
    private javax.swing.JTextField txtCadastrarProdutoNome;
    private javax.swing.JTextField txtCadastrarProdutoUnidade;
    private javax.swing.JTextField txtCadastroClienteCodigo;
    private javax.swing.JTextField txtCadastroClienteEmail;
    private javax.swing.JTextField txtCadastroClienteNome;
    private javax.swing.JTextField txtCadastroFornecedorCidade;
    private javax.swing.JTextField txtCadastroFornecedorCodigo;
    private javax.swing.JTextField txtCadastroFornecedorComplemento;
    private javax.swing.JTextField txtCadastroFornecedorEmail;
    private javax.swing.JTextField txtCadastroFornecedorNome;
    private javax.swing.JTextField txtCadastroFornecedorRua;
    private javax.swing.JTextField txtCadastroFornecimentoCodigo;
    private javax.swing.JTextField txtCadastroFornecimentoFornecedorCidade;
    private javax.swing.JTextField txtCadastroFornecimentoFornecedorCnpj;
    private javax.swing.JTextField txtCadastroFornecimentoFornecedorCodigo;
    private javax.swing.JTextField txtCadastroFornecimentoFornecedorEstado;
    private javax.swing.JTextField txtCadastroFornecimentoFornecedorNome;
    private javax.swing.JTextField txtCadastroFornecimentoFornecedorTelefone;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoCodigo;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoMarca;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoNome;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoQtdUnitaria;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoQuantidade;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoValor;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoValorTotal;
    private javax.swing.JTextField txtCadastroFornecimentoProdutoValorVenda;
    private javax.swing.JTextField txtCadastroFuncionarioCidade;
    private javax.swing.JTextField txtCadastroFuncionarioComplemento;
    private javax.swing.JTextField txtCadastroFuncionarioLogin;
    private javax.swing.JTextField txtCadastroFuncionarioNome;
    private javax.swing.JTextField txtCadastroFuncionarioRua;
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
