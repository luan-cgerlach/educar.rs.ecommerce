package controller;

import entity.Cliente;
import entity.ItemPedido;
import entity.Pedido;
import entity.Produto;
import entity.TipoUsuario;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import service.PedidoService;
import service.ProdutoService;

public final class ProdutoController extends javax.swing.JFrame {

    private ProdutoService produtoService = new ProdutoService();
    Pedido pedido = new Pedido();
    Cliente cliente = new Cliente();

    public ProdutoController() {
        inicializarComponentesJFrame();
        this.setLocationRelativeTo(null);
        jbComprar.setVisible(false);
    }

    public ProdutoController(Cliente cliente) {
        inicializarComponentesJFrame();
        this.setLocationRelativeTo(null);
        this.cliente = cliente;
        this.pedido = new Pedido();
        permissoesClientes();
        this.pedido.setCliente(this.cliente);
    }

    public void inicializarComponentesJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        gerarPaginasDaTabela();
    }

    private void permissoesClientes() {
        jbNovo.setVisible(false);
        jbEditar.setVisible(false);
        jbExcluir.setVisible(false);
        jbComprar.setVisible(true);
    }

    private void preencheTabelaCompleta(List<Produto> produtos) {
        int i = 0;
        for (Produto p : produtos) {
            jtListaProdutos.setValueAt(p.getId(), i, 0);
            jtListaProdutos.setValueAt(p.getNome(), i, 1);
            jtListaProdutos.setValueAt(p.getDescricao(), i, 2);
            jtListaProdutos.setValueAt(p.getPreco(), i, 3);
            jtListaProdutos.setValueAt(p.getEstoque(), i, 4);
            i++;
        }
    }

    private void preencheUmValorDaTabela(Produto produto, int linha) {
        jtListaProdutos.setValueAt(produto.getId(), linha, 0);
        jtListaProdutos.setValueAt(produto.getNome(), linha, 1);
        jtListaProdutos.setValueAt(produto.getDescricao(), linha, 2);
        jtListaProdutos.setValueAt(produto.getPreco(), linha, 3);
        jtListaProdutos.setValueAt(produto.getEstoque(), linha, 4);
    }

    private void gerarPaginasDaTabela() {
        int paginas = Integer.parseInt(jlPagina.getText());
        limparTabela();
        preencheTabelaCompleta(produtoService.buscarProdutos(paginas - 1));
    }

    private void limparTabela() {
        for (int i = 1; i < jtListaProdutos.getRowCount(); i++) {
            jtListaProdutos.setValueAt(null, i, 0);
            jtListaProdutos.setValueAt(null, i, 1);
            jtListaProdutos.setValueAt(null, i, 2);
            jtListaProdutos.setValueAt(null, i, 3);
            jtListaProdutos.setValueAt(null, i, 4);
        }
    }

    private void atualizarTabela() {
        limparTabela();
        jlPagina.setText("1");
        gerarPaginasDaTabela();
    }

    private Produto lerDadosDaLinhaSelecionada() {
        int linha = jtListaProdutos.getSelectedRow();
        Produto produto = new Produto(
                Integer.parseInt(jtListaProdutos.getValueAt(linha, 0).toString()),
                jtListaProdutos.getValueAt(linha, 1).toString(),
                jtListaProdutos.getValueAt(linha, 2).toString(),
                Double.parseDouble(
                        jtListaProdutos.getValueAt(linha, 3).toString()),
                Integer.parseInt(jtListaProdutos.getValueAt(linha, 4).toString()));
        return produto;
    }

    private Boolean verificarSeAlgumaLinhaEstaSelecionada() {
        return ((jtListaProdutos.getSelectedRow() < 0) || (jtListaProdutos.getValueAt(jtListaProdutos.getSelectedRow(), 0) == null));
    }

    private boolean verificarSeADadosSuficientesParaNovaPagina() {
        if (jtListaProdutos.getValueAt(19, 1) != null) {
            return true;
        }
        return false;
    }

    // Funções dos Jbuttons
    private void excluirProduto() {
        if ((verificarSeAlgumaLinhaEstaSelecionada())) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um registro para Excluir");
        } else if (JOptionPane.showConfirmDialog(null, "Voce tem certeza que deseja excluir o produto?\n" + lerDadosDaLinhaSelecionada()) == 0) {
            produtoService.excluirProduto(lerDadosDaLinhaSelecionada().getId());
            atualizarTabela();
        }
    }

    private void editarProduto() {
        if ((verificarSeAlgumaLinhaEstaSelecionada())) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um registro para Editar");
        } else {
            new NovoProdutoController(lerDadosDaLinhaSelecionada(), jtListaProdutos.getSelectedRow()).setVisible(true);
        }
    }

    private void localizarProdutos() {
        try {
            if (cliente.getTipo() == TipoUsuario.ADMIN) {
                localizarProdutosPorID();
            } else {
                localizarProdutosPorDescricao();
            }
        } catch (HeadlessException | NumberFormatException ex) {
            System.out.println("deu ruin");
        }
    }

    private void localizarProdutosPorID() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do Produto: "));
        ProdutoService produtoService = new ProdutoService();
        Produto produto = produtoService.buscarProduto(id);
        if (produto != null) {
            limparTabela();
            preencheUmValorDaTabela(produto, 0);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado com essa descrição.");
        }
    }

    private void localizarProdutosPorDescricao() {
        String descricao = JOptionPane.showInputDialog("Informe o ID do Produto: ");
        ProdutoService produtoService = new ProdutoService();
        List<Produto> produtos = produtoService.buscarProdutoPorDescricao(descricao);
        if (!produtos.isEmpty()) {
            limparTabela();
            preencheTabelaCompleta(produtos);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado com essa descrição.");
        }
    }

    private void passarPagina() {
        if (verificarSeADadosSuficientesParaNovaPagina() == true) {
            int PaginaAtual = Integer.parseInt(jlPagina.getText());
            jlPagina.setText((PaginaAtual + 1) + "");
            gerarPaginasDaTabela();
        }
    }

    private void voltarPagina() {
        int PaginaAtual = Integer.parseInt(jlPagina.getText());
        if (PaginaAtual > 1) {
            jlPagina.setText((PaginaAtual - 1) + "");
            gerarPaginasDaTabela();
        }
    }

    private void adicionarItemAoCarrinho() {
        if (verificarSeAlgumaLinhaEstaSelecionada()) {
            JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada");
        } else {
            Produto produto = lerDadosDaLinhaSelecionada();
            int quantidade = obterQuantidadeDoItem();
            int quantidadeNoPedido = quantidadeNoPedido(produto.getId());

            if (quantidadeNoPedido == 0) {
                adicionarNovoItem(produto, quantidade);
            } else {
                incrementarQuantidadeItem(produto, quantidade + quantidadeNoPedido);
            }
        }
    }

    private void adicionarNovoItem(Produto produto, int quantidade) {
        if (verificaDisponibilidadeEmEstoque(produto, quantidade)) {
            adicionarItemPedidoAoPedido(quantidade, produto);
        } else {
            JOptionPane.showMessageDialog(null, "Não há tantos produtos disponiveis");
        }
    }

    private void incrementarQuantidadeItem(Produto produto, int quantidade) {
        if (verificaDisponibilidadeEmEstoque(produto, quantidade)) {
            for (ItemPedido ip : pedido.getItensPedidos()) {
                if (ip.getProduto().getId() == produto.getId()) {
                    ip.setQuantidade(quantidade);
                    ip.setValor(ip.getProduto().getPreco() * quantidade);
                }
            }
        }
    }

    private int quantidadeNoPedido(int idProduto) {
        for (ItemPedido ip : pedido.getItensPedidos()) {
            if (ip.getProduto().getId() == idProduto) {
                return ip.getQuantidade();
            }
        }
        return 0;
    }

    private int obterQuantidadeDoItem() {
        return Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade que voce deseja: "));
    }

    private boolean verificaDisponibilidadeEmEstoque(Produto produto, int quantidade) {
        return quantidade >= 1 && produto.getEstoque() >= quantidade;
    }

    private void adicionarItemPedidoAoPedido(int quantidade, Produto produto) {
        pedido.getItensPedidos().add(new ItemPedido(quantidade * produto.getPreco(), quantidade, produto));
        jbComprar.setText("Comprar(" + pedido.getItensPedidos().size() + ")");
    }

    private void finalizarPedido() {
        PedidoService pedidoService = new PedidoService();
        pedidoService.salvarPedido(pedido);
        atualizarTabela();
        jbComprar.setText("Comprar");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbLocalizar = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jbNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListaProdutos = new javax.swing.JTable();
        jbProximo = new javax.swing.JButton();
        jbAnterior = new javax.swing.JButton();
        jlPagina = new javax.swing.JLabel();
        jbAtualizar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jmiFinalizarPedido = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Produtos");

        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbLocalizar.setText("Localizar");
        jbLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLocalizarActionPerformed(evt);
            }
        });

        jbComprar.setText("Comprar");
        jbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbComprarActionPerformed(evt);
            }
        });

        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jtListaProdutos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtListaProdutos.setForeground(new java.awt.Color(0, 0, 0));
        jtListaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "DESCRICÃO", "PREÇO", "ESTOQUE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtListaProdutos);

        jbProximo.setText(">>");
        jbProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProximoActionPerformed(evt);
            }
        });

        jbAnterior.setText("<<");
        jbAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnteriorActionPerformed(evt);
            }
        });

        jlPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPagina.setText("1");

        jbAtualizar.setText("Atualizar");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jMenu1.setText("Arquivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pedido");

        jmiFinalizarPedido.setText("Finalizar Pedido");
        jmiFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFinalizarPedidoActionPerformed(evt);
            }
        });
        jMenu3.add(jmiFinalizarPedido);

        jMenuItem3.setText("Ver Carrinho");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Localizar Pedido (Histórico)");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1051, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbProximo, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jbAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jlPagina))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        new NovoProdutoController().setVisible(true);
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        editarProduto();
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        excluirProduto();
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLocalizarActionPerformed
        localizarProdutos();
    }//GEN-LAST:event_jbLocalizarActionPerformed

    private void jbProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProximoActionPerformed
        passarPagina();
    }//GEN-LAST:event_jbProximoActionPerformed

    private void jbAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnteriorActionPerformed
        voltarPagina();
    }//GEN-LAST:event_jbAnteriorActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        adicionarItemAoCarrinho();
    }//GEN-LAST:event_jbComprarActionPerformed

    private void jmiFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFinalizarPedidoActionPerformed
        if (pedido.getItensPedidos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O carrinho esta vazio");
        } else {
            finalizarPedido();
            JOptionPane.showMessageDialog(null, "Pedido finalizado");
        }
    }//GEN-LAST:event_jmiFinalizarPedidoActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new PedidoController(pedido).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            int idPedido = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do Pedido: "));
            PedidoService pedidoService = new PedidoService();
            pedido = pedidoService.buscarPedido(idPedido);
            if (pedido.getCliente().getEmail().equals(this.cliente.getEmail())) {
                new PedidoController(pedido).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nenhum pedido seu com este ID!!!");
            }
        } catch (IllegalArgumentException ilex) {
            JOptionPane.showMessageDialog(null, "Aconteceu algo de errado!!!" + ilex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAnterior;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbLocalizar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbProximo;
    private javax.swing.JLabel jlPagina;
    private javax.swing.JMenuItem jmiFinalizarPedido;
    private javax.swing.JTable jtListaProdutos;
    // End of variables declaration//GEN-END:variables
}
