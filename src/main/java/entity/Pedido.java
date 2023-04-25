
package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDate data;
    private double valorTotal;
    private Cliente cliente;
    private List<ItemPedido> itemPedido;

    public Pedido() {
        this.itemPedido = new ArrayList<>();
    }
    
    public Pedido(int id, LocalDate data, double valorTotal, Cliente cliente, List<ItemPedido> itensPedidos) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.itemPedido = itensPedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItensPedidos() {
        return itemPedido;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itemPedido = itensPedidos;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", data=" + data + ", valorTotal=" + valorTotal + ", cliente=" + cliente + ", itensPedidos=" + itemPedido + '}';
    }
    
    
}
