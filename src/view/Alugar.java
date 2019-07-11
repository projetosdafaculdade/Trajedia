package view;

import controller.AlugarController;


public class Alugar extends javax.swing.JFrame {
    AlugarController alugarController; 
    public Alugar() {
        initComponents();
        alugarController = new AlugarController(this, jtfData,btnAdicionarRoupa, btnAdicionarTraje, btnAlugar, btnRemoverRoupa, btnSelecionarCliente, jtfCliente, jtfDesconto, lblValorTotal, tableRoupas, tableTrajes, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRoupas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTrajes = new javax.swing.JTable();
        btnAdicionarRoupa = new javax.swing.JButton();
        btnRemoverRoupa = new javax.swing.JButton();
        btnRemoverTraje = new javax.swing.JButton();
        btnAdicionarTraje = new javax.swing.JButton();
        jtfDesconto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSelecionarCliente = new javax.swing.JButton();
        btnAlugar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jtfData = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alugar");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Valor total:");

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotal.setText("R$ 0,00");

        tableRoupas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Roupa", "Valor", "Categoria"
            }
        ));
        jScrollPane1.setViewportView(tableRoupas);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Roupas:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Trajes:");

        tableTrajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Roupa", "Desconto %", "Valor"
            }
        ));
        jScrollPane2.setViewportView(tableTrajes);

        btnAdicionarRoupa.setText("+");
        btnAdicionarRoupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarRoupaActionPerformed(evt);
            }
        });

        btnRemoverRoupa.setText("-");
        btnRemoverRoupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverRoupaActionPerformed(evt);
            }
        });

        btnRemoverTraje.setText("-");
        btnRemoverTraje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverTrajeActionPerformed(evt);
            }
        });

        btnAdicionarTraje.setText("+");
        btnAdicionarTraje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTrajeActionPerformed(evt);
            }
        });

        jtfDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDescontoKeyReleased(evt);
            }
        });

        jLabel3.setText("Desconto(R$):");

        jtfCliente.setEditable(false);

        jLabel6.setText("Cliente:");

        btnSelecionarCliente.setText("...");
        btnSelecionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarClienteActionPerformed(evt);
            }
        });

        btnAlugar.setText("Alugar");
        btnAlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlugarActionPerformed(evt);
            }
        });

        jLabel7.setText("Para a data:");

        jtfData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDataKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSelecionarCliente)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAdicionarRoupa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoverRoupa))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAdicionarTraje)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoverTraje))
                            .addComponent(btnAlugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarRoupa)
                    .addComponent(btnRemoverRoupa))
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarTraje)
                    .addComponent(btnRemoverTraje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(btnSelecionarCliente))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAlugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblValorTotal)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarRoupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarRoupaActionPerformed
        alugarController.adicionarRoupa();
    }//GEN-LAST:event_btnAdicionarRoupaActionPerformed

    private void btnRemoverRoupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverRoupaActionPerformed
    alugarController.removerRoupa();      
    }//GEN-LAST:event_btnRemoverRoupaActionPerformed

    private void btnAdicionarTrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTrajeActionPerformed
    alugarController.adicionarTraje();
    }//GEN-LAST:event_btnAdicionarTrajeActionPerformed

    private void btnRemoverTrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverTrajeActionPerformed
          alugarController.removerTraje();

    }//GEN-LAST:event_btnRemoverTrajeActionPerformed

    private void btnSelecionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarClienteActionPerformed
    alugarController.escolherCliente();
    }//GEN-LAST:event_btnSelecionarClienteActionPerformed

    private void btnAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlugarActionPerformed
    alugarController.alugar();
    }//GEN-LAST:event_btnAlugarActionPerformed

    private void jtfDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDescontoKeyReleased
      alugarController.atualizarMoney();
    }//GEN-LAST:event_jtfDescontoKeyReleased

    private void jtfDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDataKeyReleased
      alugarController.formatarData();
    }//GEN-LAST:event_jtfDataKeyReleased

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alugar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarRoupa;
    private javax.swing.JButton btnAdicionarTraje;
    private javax.swing.JButton btnAlugar;
    private javax.swing.JButton btnRemoverRoupa;
    private javax.swing.JButton btnRemoverTraje;
    private javax.swing.JButton btnSelecionarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jtfCliente;
    private javax.swing.JTextField jtfData;
    private javax.swing.JTextField jtfDesconto;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tableRoupas;
    private javax.swing.JTable tableTrajes;
    // End of variables declaration//GEN-END:variables
}
