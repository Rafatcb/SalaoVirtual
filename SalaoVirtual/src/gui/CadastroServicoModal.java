/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;

/**
 * 
 *
 * @author rafae
 */
public class CadastroServicoModal extends javax.swing.JDialog {

    /**
     * Cria um novo JDialog para o CadastroServicoModal
     */
    public CadastroServicoModal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(Color.PINK);

        try {
            MaskFormatter mask = new MaskFormatter("##/##/##");
            mask.install(ftxfData);
            //ftxfData = new javax.swing.JFormattedTextField(mask);
            //ftxfData.set
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setLocationRelativeTo(null);
    }
    
    public CadastroServicoModal(java.awt.Frame parent, boolean modal, String mensagem) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txfNomeServico = new javax.swing.JTextField();
        txfCodigoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnConsultarCliente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txfCodigoservico = new javax.swing.JTextField();
        btnConsultarFuncionario = new javax.swing.JButton();
        txtLoginFuncionario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ftxfValor = new javax.swing.JFormattedTextField();
        ftxfData = new javax.swing.JFormattedTextField();
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

        txfNomeServico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N

        txfCodigoCliente.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N

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

        txfCodigoservico.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txfCodigoservico.setFocusable(false);

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

        jLabel6.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel6.setText("Valor");

        ftxfValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        ftxfValor.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxfValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxfValorFocusLost(evt);
            }
        });

        ftxfData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        ftxfData.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        ftxfData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxfDataFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxfDataFocusLost(evt);
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
                .addComponent(ftxfData, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(txfCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnConsultarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txfCodigoservico, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(ftxfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                        .addComponent(ftxfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(txfNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txfCodigoservico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftxfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(txfCodigoCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Hover.png"));
        btnCadastrar.setIcon(i);
    }//GEN-LAST:event_btnCadastrarMouseEntered

    private void btnCadastrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico.png"));
        btnCadastrar.setIcon(i);
    }//GEN-LAST:event_btnCadastrarMouseExited

    private void btnCadastrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Pressed.png"));
        btnCadastrar.setIcon(i);
    }//GEN-LAST:event_btnCadastrarMousePressed

    private void btnCadastrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCadastroServico_Hover.png"));
        btnCadastrar.setIcon(i);
    }//GEN-LAST:event_btnCadastrarMouseReleased

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro_Hover.png"));
        btnCancelar.setIcon(i);
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro.png"));
        btnCancelar.setIcon(i);
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro_Pressed.png"));
        btnCancelar.setIcon(i);
    }//GEN-LAST:event_btnCancelarMousePressed

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoCancelarCadastro_Hover.png"));
        btnCancelar.setIcon(i);
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConsultarFuncionarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarFuncionarioMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
        btnConsultarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnConsultarFuncionarioMouseEntered

    private void btnConsultarFuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarFuncionarioMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
        btnConsultarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnConsultarFuncionarioMouseExited

    private void btnConsultarFuncionarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarFuncionarioMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
        btnConsultarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnConsultarFuncionarioMousePressed

    private void btnConsultarFuncionarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarFuncionarioMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
        btnConsultarFuncionario.setIcon(i);
    }//GEN-LAST:event_btnConsultarFuncionarioMouseReleased

    private void btnConsultarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarClienteMouseEntered
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
        btnConsultarCliente.setIcon(i);
    }//GEN-LAST:event_btnConsultarClienteMouseEntered

    private void btnConsultarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarClienteMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar.png"));
        btnConsultarCliente.setIcon(i);
    }//GEN-LAST:event_btnConsultarClienteMouseExited

    private void btnConsultarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarClienteMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Pressed.png"));
        btnConsultarCliente.setIcon(i);
    }//GEN-LAST:event_btnConsultarClienteMousePressed

    private void btnConsultarClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarClienteMouseReleased
        ImageIcon i = new ImageIcon(getClass().getResource("/images/cadastro/botaoConsultar_Hover.png"));
        btnConsultarCliente.setIcon(i);
    }//GEN-LAST:event_btnConsultarClienteMouseReleased

    private void btnConsultarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarFuncionarioActionPerformed

    private void btnConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarClienteActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void ftxfDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxfDataFocusLost
        
    }//GEN-LAST:event_ftxfDataFocusLost

    private void ftxfDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxfDataFocusGained
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##/##/##");
            mask.install(ftxfData);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxfDataFocusGained

    private void ftxfValorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxfValorFocusLost
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("R$ ###.##");
            mask.install(ftxfValor);
        } catch (ParseException ex) {
            //Logger.getLogger(CadastroServicoModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ftxfValorFocusLost

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
            java.util.logging.Logger.getLogger(CadastroServicoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroServicoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroServicoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroServicoModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroServicoModal dialog = new CadastroServicoModal(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultarCliente;
    private javax.swing.JButton btnConsultarFuncionario;
    private javax.swing.JFormattedTextField ftxfData;
    private javax.swing.JFormattedTextField ftxfValor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txfCodigoCliente;
    private javax.swing.JTextField txfCodigoservico;
    private javax.swing.JTextField txfNomeServico;
    private javax.swing.JTextField txtLoginFuncionario;
    // End of variables declaration//GEN-END:variables
}
