/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.bean.Produto;
import model.bean.Stock;
import model.dao.ProdutoDAO;
import model.dao.StockDAO;

/**
 *
 * @author horaciocome1
 */
public class ConfirmarCadastro extends javax.swing.JFrame {

    /**
     * Creates new form ConfirmarCadastro
     * @param produto
     */
    private static Produto produto;
    private static Stock stock;
    
    public ConfirmarCadastro(Produto produto) {
        ConfirmarCadastro.produto = produto;
        initComponents();
        lbl.setText("Tem certeza que pretende cadastrar este produto? Pode voltar e editar.");
    }
    
    public ConfirmarCadastro(Stock stock) {
        ConfirmarCadastro.stock = stock;
        initComponents();
        lbl.setText("Tem certeza que pretende cadastrar este stock? Pode voltar e editar.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl = new javax.swing.JLabel();
        btCadastrar = new javax.swing.JButton();
        btVoltar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Confirma Cadastro de Produto");
        setAlwaysOnTop(true);

        lbl.setText("Tem a certeza que pretende cadastrar este produto? Pode voltar e editar.");

        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ic_check_black_18dp.png"))); // NOI18N
        btCadastrar.setToolTipText("Confirmar cadastro.");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btVoltar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ic_chevron_left_black_18dp.png"))); // NOI18N
        btVoltar1.setToolTipText("Voltar e editar.");
        btVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVoltar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCadastrar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCadastrar)
                    .addComponent(btVoltar1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltar1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btVoltar1ActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        if (ConfirmarCadastro.produto != null) {
            ProdutoDAO dao = new ProdutoDAO();
            dao.add(ConfirmarCadastro.produto);
        } else if (ConfirmarCadastro.stock != null) {
            StockDAO dao = new StockDAO();
            dao.add(stock);
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

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
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfirmarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (produto != null)
                    new ConfirmarCadastro(produto).setVisible(true);
                else if (stock != null)
                    new ConfirmarCadastro(stock).setVisible(true);
                    
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btVoltar1;
    private javax.swing.JLabel lbl;
    // End of variables declaration//GEN-END:variables
}
