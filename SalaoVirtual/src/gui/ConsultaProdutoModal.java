/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.tableModel.ForcedListSelectionModel;
import gui.tableModel.ConsultaProdutoTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import salaovirtual.Produto;
import salaovirtual.access.Consulta;

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
        btnOk.setIcon(i);
    }//GEN-LAST:event_btnOkMouseEntered

    private void btnOkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK.png"));
        btnOk.setIcon(i);
    }//GEN-LAST:event_btnOkMouseExited

    private void btnOkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Pressed.png"));
        btnOk.setIcon(i);
    }//GEN-LAST:event_btnOkMousePressed

    private void btnOkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/okcancel/botaoOK_Hover.png"));
        btnOk.setIcon(i);
    }//GEN-LAST:event_btnOkMouseReleased

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnConsultarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
        btnConsultar.setIcon(i);
    }//GEN-LAST:event_btnConsultarMouseEntered

    private void btnConsultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio.png"));
        btnConsultar.setIcon(i);
    }//GEN-LAST:event_btnConsultarMouseExited

    private void btnConsultarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Pressed.png"));
        btnConsultar.setIcon(i);
    }//GEN-LAST:event_btnConsultarMousePressed

    private void btnConsultarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultaMedio_Hover.png"));
        btnConsultar.setIcon(i);
    }//GEN-LAST:event_btnConsultarMouseReleased

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
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
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void chbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbCodigoActionPerformed
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
    }//GEN-LAST:event_chbCodigoActionPerformed

    private void chbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMarcaActionPerformed
        if (chbMarca.isSelected()) {
            chbCodigo.setSelected(false);
            txtCodigo.setText("");
            txtCodigo.setEnabled(false);
            txtMarca.setEnabled(true);
        }
        else {
            txtMarca.setEnabled(false);
        }
    }//GEN-LAST:event_chbMarcaActionPerformed

    private void chbNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbNomeActionPerformed
        if (chbNome.isSelected()) {
            chbCodigo.setSelected(false);
            txtCodigo.setEnabled(false);
            txtCodigo.setText("");
            txtNome.setEnabled(true);
        }
        else {
            txtNome.setEnabled(false);
        }
    }//GEN-LAST:event_chbNomeActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(ConsultaProdutoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaProdutoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaProdutoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaProdutoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConsultaProdutoModal dialog = new ConsultaProdutoModal(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        
        
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    // End of variables declaration//GEN-END:variables
}
