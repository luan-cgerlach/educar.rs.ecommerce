
package service;

import entity.ItemPedido;
import entity.Pedido;
import entity.Produto;
import repository.ClienteRepository;
import repository.ItemPedidoRepository;
import repository.PedidoRepository;
import repository.ProdutoRepository;

public class PedidoService {
 private final ItemPedidoRepository itemPedidoRepository = new ItemPedidoRepository();
    private final PedidoRepository pedidoRepository = new PedidoRepository();
    private final ProdutoRepository produtoRepository = new ProdutoRepository();
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public Pedido salvarPedido(Pedido pedido) {
        double valorTotal = 0;
        for (ItemPedido ip : pedido.getItensPedidos()) {
            valorTotal += ip.getValor();
        }
        pedido.setValorTotal(valorTotal);
        Pedido pedidoSalvo = pedidoRepository.salvarPedido(pedido);
        for (ItemPedido ip : pedido.getItensPedidos()) {
            this.itemPedidoRepository.salvarItemPedido(ip, pedidoSalvo.getId());
            this.produtoRepository.atualizarEstoque(ip.getProduto().getId(), ip.getQuantidade());
        }
        return pedidoSalvo;
    }

    public Pedido buscarPedido(int id) {
        Pedido pedido = this.pedidoRepository.buscarPedido(id);
        pedido.setCliente(this.clienteRepository.buscarCliente(pedido.getCliente().getId()));
        pedido.setItensPedidos(this.itemPedidoRepository.buscarItensPedido(id));
        Produto produto;
        for (ItemPedido ip : pedido.getItensPedidos()) {
            produto = new Produto();
            produto = this.produtoRepository.buscarProduto(ip.getProduto().getId());
            ip.setProduto(produto);
        }
        return pedido;
    }

}
