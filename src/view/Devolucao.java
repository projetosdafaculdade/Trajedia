package view;

import controller.AlugarController;
import javax.swing.table.DefaultTableModel;
import model.vo.Locacao;
import util.SelectOptions;

public class Devolucao extends javax.swing.JDialog {

    Locacao locacao;
    AlugarController alugarController;

    public Devolucao(java.awt.Frame parent, boolean modal, Locacao locacao) {
        super(parent, modal);
        initComponents();
        this.locacao = locacao;
        carregar();
        alugarController = new AlugarController();
    }

    public void listarNaTabelaRoupa() {
        DefaultTableModel model = (DefaultTableModel) tableRoupas.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableRoupas.getModel();
        for (model.vo.Roupa roupa : locacao.getRoupas()) {
            Object[] linha = {
                roupa.getIdRoupa(),
                roupa.getNome(),
                roupa.getVlr(),
                roupa.getCategoria().getNome()
            };
            model.addRow(linha);
        }
    }

    public void listarNaTabelaTraje() {
        DefaultTableModel model = (DefaultTableModel) tableTrajes.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; qtd > i; i++) {
            model.removeRow(qtd - i - 1);
        }
        model = (DefaultTableModel) tableTrajes.getModel();
        for (model.vo.Traje traje : locacao.getTrajes()) {
            Object[] linha = {
                traje.getIdTraje(),
                traje.getNome(),
                traje.getDesconto(),
                traje.getValorTraje()
            };
            model.addRow(linha);
        }
    }

    public void carregar() {
        jtfCliente.setText(locacao.getCliente().getIdCliente() + " - " + locacao.getCliente().getNome());
        jtfData.setText(String.valueOf(locacao.getDataEvento()));
        lblValorTotal.setText(String.valueOf(locacao.getVlrTotal()));

        listarNaTabelaRoupa();
        listarNaTabelaTraje();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAlugar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnAdicionarRoupa = new javax.swing.JButton();
        jtfData = new javax.swing.JTextField();
        btnRemoverRoupa = new javax.swing.JButton();
        btnRemoverTraje = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAdicionarTraje = new javax.swing.JButton();
        lblValorTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRoupas = new javax.swing.JTable();
        jtfCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTrajes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAlugar.setText("Devolução");
        btnAlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlugarActionPerformed(evt);
            }
        });

        jLabel7.setText("Para a data:");

        btnAdicionarRoupa.setText("+");
        btnAdicionarRoupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarRoupaActionPerformed(evt);
            }
        });

        jtfData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfDataKeyReleased(evt);
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Valor total:");

        btnAdicionarTraje.setText("+");
        btnAdicionarTraje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTrajeActionPerformed(evt);
            }
        });

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

        jtfCliente.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Roupas:");

        jLabel6.setText("Cliente:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdicionarRoupa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoverRoupa))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdicionarTraje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoverTraje))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValorTotal)
                        .addComponent(jLabel1))
                    .addComponent(btnAlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlugarActionPerformed
        alugarController.realizarDevolucao(locacao);
        this.dispose();
    }//GEN-LAST:event_btnAlugarActionPerformed

    private void btnAdicionarRoupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarRoupaActionPerformed

    }//GEN-LAST:event_btnAdicionarRoupaActionPerformed

    private void jtfDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDataKeyReleased

    }//GEN-LAST:event_jtfDataKeyReleased

    private void btnRemoverRoupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverRoupaActionPerformed

    }//GEN-LAST:event_btnRemoverRoupaActionPerformed

    private void btnRemoverTrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverTrajeActionPerformed

    }//GEN-LAST:event_btnRemoverTrajeActionPerformed

    private void btnAdicionarTrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTrajeActionPerformed

    }//GEN-LAST:event_btnAdicionarTrajeActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Devolucao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Devolucao dialog = new Devolucao(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton btnAdicionarRoupa;
    private javax.swing.JButton btnAdicionarTraje;
    private javax.swing.JButton btnAlugar;
    private javax.swing.JButton btnRemoverRoupa;
    private javax.swing.JButton btnRemoverTraje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jtfCliente;
    private javax.swing.JTextField jtfData;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tableRoupas;
    private javax.swing.JTable tableTrajes;
    // End of variables declaration//GEN-END:variables
}
