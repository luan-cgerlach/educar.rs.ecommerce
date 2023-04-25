
package repository;

import entity.ItemPedido;
import entity.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;
import resources.Util;

public class ItemPedidoRepository {
    private final Util util = new Util();
    Connection conn;
    PreparedStatement ppst;
    
    public ItemPedido salvarItemPedido(ItemPedido itemPedido, int idPedido) {
        conn = util.conexao();
        String sql = "INSERT INTO item_pedido("
                + "valor,"
                + "quantidade,"
                + "id_pedido,"
                + "id_produto) "
                + "VALUES(?,?,?,?)";
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setDouble(1, itemPedido.getValor());
            ppst.setInt(2, itemPedido.getQuantidade());
            ppst.setInt(3, idPedido);
            ppst.setInt(4, itemPedido.getProduto().getId());
            ppst.executeUpdate();
            ppst.close();
            conn.close();
        } catch (PSQLException ex){
            JOptionPane.showMessageDialog(null, "Não foi possível salvar o item de Pedido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    public List<ItemPedido> buscarItensPedido(int idPedido) {
       try {
            String sql = "SELECT * FROM item_pedido where id_pedido = ?";
            conn = util.conexao();
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, idPedido);
            ResultSet rs = ppst.executeQuery();
            List<ItemPedido> itensPedido = new ArrayList<>();
            while (rs.next()) {
                ItemPedido ItemPedido = new ItemPedido(rs.getInt(1), rs.getDouble(2), rs.getInt(3), new Produto(rs.getInt(5)));
                itensPedido.add(ItemPedido);
            }
            return itensPedido;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco.");
        }
        return null;
    }
    
}

