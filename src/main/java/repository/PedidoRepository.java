
package repository;

import entity.Cliente;
import entity.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import resources.Util;

public class PedidoRepository {
    private final Util util = new Util();
    Connection conn;
    PreparedStatement ppst;
    
   public Pedido salvarPedido(Pedido pedido) {
        conn = util.conexao();
        String sql = "INSERT INTO pedido("
                + "valor_total,"
                + "id_cliente) "
                + "VALUES(?,?) RETURNING id";
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setDouble(1, pedido.getValorTotal());
            ppst.setInt(2, pedido.getCliente().getId());
            ResultSet rs = ppst.executeQuery();
            while(rs.next()){
                pedido.setId(rs.getInt(1));
            }
            ppst.close();
            conn.close();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return pedido;
    }
   
   public Pedido buscarPedido(int id) {
        try {
            String sql = "SELECT * FROM pedido where id = ?";
            conn = util.conexao();
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, id);
            System.out.println(sql);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                return new Pedido(rs.getInt(1), LocalDate.parse(rs.getString(2)),rs.getDouble(3),
                        new Cliente(rs.getInt(4)), null);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco.");
        }
        return null;
    }
}
