package entity;

public class Produto {
    
      private int id;
    private String nome;
    private String descricao;
    private Double preco;
    private int estoque;

    public Produto() {
    }
    
    public Produto(int id){
        this.id = id;
    }

    public Produto(String nome, String descricao, Double preco, int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    public Produto(int id, String nome, String descricao, Double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", estoque=" + estoque + '}';
    }
    
}
