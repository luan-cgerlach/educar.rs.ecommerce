/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.ItemPedido;
import entity.Pedido;
import java.time.LocalDate;

/**
 *
 * @author Mauri
 */
public class PedidoController extends javax.swing.JFrame {

    public PedidoController(Pedido pedido) {
        initComponents();
        preencheCabecalho(pedido);
        preencheTabela(pedido);
    }

    private void preencheCabecalho(Pedido pedido) {
        if (pedido.getId() == 0) {
            jtfCliente.setText(pedido.getCliente().getNome());
            jtfData.setText(LocalDate.now().toString());
            
            Double valorTotalPedido = 0.0;
            for(ItemPedido ip: pedido.getItensPedidos()){
                valorTotalPedido += ip.getValor();
            }
            jtfValorTotalPedido.setText(valorTotalPedido+"");
            
        } else {
            jtfId.setText(pedido.getId()+"");
            jtfCliente.setText(pedido.getCliente().getNome());
            jtfData.setText(pedido.getData().toString());
            jtfValorTotalPedido.setText(pedido.getValorTotal()+"");
        }

    }

    private void preencheTabela(Pedido pedido) {
        int i = 0;
        for (ItemPedido ip : pedido.getItensPedidos()) {
            jtItensPedido.setValueAt(ip.getProduto().getNome(), i, 0);
            jtItensPedido.setValueAt(ip.getQuantidade(), i, 1);
            jtItensPedido.setValueAt(ip.getProduto().getPreco(), i, 2);
            jtItensPedido.setValueAt(ip.getValor(), i, 3);
            i++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlLogin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfData = new javax.swing.JTextField();
        jtfCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfValorTotalPedido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtItensPedido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jlLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlLogin.setText("PEDIDO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ID:");

        jtfId.setEditable(false);
        jtfId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfId.setToolTipText("");
        jtfId.setName(""); // NOI18N
        jtfId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfIdMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Data:");

        jtfData.setEditable(false);
        jtfData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfData.setToolTipText("");
        jtfData.setName(""); // NOI18N
        jtfData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfDataMouseClicked(evt);
            }
        });

        jtfCliente.setEditable(false);
        jtfCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfCliente.setToolTipText("");
        jtfCliente.setName(""); // NOI18N
        jtfCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfClienteMouseClicked(evt);
            }
        });
        jtfCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfClienteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nome do Cliente:");

        jtfValorTotalPedido.setEditable(false);
        jtfValorTotalPedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfValorTotalPedido.setToolTipText("");
        jtfValorTotalPedido.setName(""); // NOI18N
        jtfValorTotalPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfValorTotalPedidoMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Valor total do Pedido:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        jtItensPedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtItensPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Quantidade", "Preço", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtItensPedido);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfValorTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlLogin)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfValorTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfIdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdMouseClicked

    private void jtfDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfDataMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDataMouseClicked

    private void jtfClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfClienteMouseClicked

    private void jtfValorTotalPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfValorTotalPedidoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfValorTotalPedidoMouseClicked

    private void jtfClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfClienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlLogin;
    private javax.swing.JTable jtItensPedido;
    private javax.swing.JTextField jtfCliente;
    private javax.swing.JTextField jtfData;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfValorTotalPedido;
    // End of variables declaration//GEN-END:variables
}
