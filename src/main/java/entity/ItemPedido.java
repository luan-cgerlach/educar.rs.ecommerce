
package entity;

public class ItemPedido {
    private int id;
    private double valor;
    private int quantidade;
    private Produto produto;

    public ItemPedido() {
    }
   
    public ItemPedido(int id, double valor, int quantidade, Produto produto) {
        this.id = id;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
    }
    
    public ItemPedido(double valor, int quantidade, Produto produto) {
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "id=" + id + ", valor=" + valor + ", quantidade=" + quantidade + ", produto=" + produto + '}';
    }
    
    
}
