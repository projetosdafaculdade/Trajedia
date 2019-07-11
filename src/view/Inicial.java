package view;

import controller.InicialController;
import controller.RelatorioController;

public class Inicial extends javax.swing.JFrame {

    InicialController inicialController;
    RelatorioController relatorioController;
    public final int idFuncionario;

    public Inicial(int idFuncionario) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        inicialController = new InicialController();
        this.idFuncionario = idFuncionario;
        relatorioController = new RelatorioController();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnRoupa = new javax.swing.JMenuItem();
        btnCliente = new javax.swing.JMenuItem();
        btnTraje = new javax.swing.JMenuItem();
        btnFuncionario = new javax.swing.JMenuItem();
        btnFornecedor = new javax.swing.JMenuItem();
        btnCategoria = new javax.swing.JMenuItem();
        btnFornecedor1 = new javax.swing.JMenuItem();
        btnFornecedor2 = new javax.swing.JMenuItem();
        btnAgendar = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        btnTrajesRelatorio = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trajedia");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("Cadastros");

        btnRoupa.setText("Roupa");
        btnRoupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoupaActionPerformed(evt);
            }
        });
        jMenu1.add(btnRoupa);

        btnCliente.setText("Cliente");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });
        jMenu1.add(btnCliente);

        btnTraje.setText("Traje");
        btnTraje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrajeActionPerformed(evt);
            }
        });
        jMenu1.add(btnTraje);

        btnFuncionario.setText("Funcionario");
        btnFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionarioActionPerformed(evt);
            }
        });
        jMenu1.add(btnFuncionario);

        btnFornecedor.setText("Fornecedor");
        btnFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornecedorActionPerformed(evt);
            }
        });
        jMenu1.add(btnFornecedor);

        btnCategoria.setText("Categoria");
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });
        jMenu1.add(btnCategoria);

        btnFornecedor1.setText("Endereco");
        btnFornecedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornecedor1ActionPerformed(evt);
            }
        });
        jMenu1.add(btnFornecedor1);

        btnFornecedor2.setText("Permissões");
        btnFornecedor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFornecedor2ActionPerformed(evt);
            }
        });
        jMenu1.add(btnFornecedor2);

        jMenuBar1.add(jMenu1);

        btnAgendar.setText("Agendar");
        btnAgendar.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                btnAgendarMenuSelected(evt);
            }
        });
        btnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Agendar Agora");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        btnAgendar.add(jMenuItem1);

        jMenuBar1.add(btnAgendar);

        jMenu3.setText("Alugar");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Alugar Agora");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Relatórios");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Clientes");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        btnTrajesRelatorio.setText("Trajes");
        btnTrajesRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrajesRelatorioActionPerformed(evt);
            }
        });
        jMenu4.add(btnTrajesRelatorio);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRoupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoupaActionPerformed
        inicialController.abrirRoupa();
    }//GEN-LAST:event_btnRoupaActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        inicialController.abrirCliente();
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnTrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrajeActionPerformed
        inicialController.abrirTraje();
    }//GEN-LAST:event_btnTrajeActionPerformed

    private void btnFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionarioActionPerformed
        inicialController.abrirFuncionario();
    }//GEN-LAST:event_btnFuncionarioActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        inicialController.abrirCategoria();
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedorActionPerformed
        inicialController.abrirFornecedor();
    }//GEN-LAST:event_btnFornecedorActionPerformed

    private void btnFornecedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedor1ActionPerformed
        inicialController.abrirEndereco();
    }//GEN-LAST:event_btnFornecedor1ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void btnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarActionPerformed
        inicialController.agendar();
    }//GEN-LAST:event_btnAgendarActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        inicialController.alugar();
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void btnFornecedor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedor2ActionPerformed
        inicialController.permissoes();
    }//GEN-LAST:event_btnFornecedor2ActionPerformed

    private void btnAgendarMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_btnAgendarMenuSelected

    }//GEN-LAST:event_btnAgendarMenuSelected

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        inicialController.alugar();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       relatorioController.cliente();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnTrajesRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrajesRelatorioActionPerformed
     relatorioController.trajesRoupas();
    }//GEN-LAST:event_btnTrajesRelatorioActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicial(new Integer(0)).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu btnAgendar;
    private javax.swing.JMenuItem btnCategoria;
    private javax.swing.JMenuItem btnCliente;
    private javax.swing.JMenuItem btnFornecedor;
    private javax.swing.JMenuItem btnFornecedor1;
    private javax.swing.JMenuItem btnFornecedor2;
    private javax.swing.JMenuItem btnFuncionario;
    private javax.swing.JMenuItem btnRoupa;
    private javax.swing.JMenuItem btnTraje;
    private javax.swing.JMenuItem btnTrajesRelatorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
