
package service;

import entity.ItemPedido;
import repository.ItemPedidoRepository;

public class ItemPedidoService {
    ItemPedidoRepository itemPedidoRepository = new ItemPedidoRepository();
    
     public ItemPedido salvarProduto(ItemPedido itemPedido, int idPedido) {
        return itemPedidoRepository.salvarItemPedido(itemPedido, idPedido);
    }
}
