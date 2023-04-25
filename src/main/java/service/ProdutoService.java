
package service;

import entity.Produto;
import java.util.List;
import repository.ProdutoRepository;

public class ProdutoService {
    private final ProdutoRepository produtoRepository = new ProdutoRepository();

    public List<Produto> buscarProdutos(int pagina) {
        return produtoRepository.buscarProdutos(pagina);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.salvarProduto(produto);
    }

    public Produto buscarProduto(int id) {
        return produtoRepository.buscarProduto(id);
    }
    
    public List<Produto> buscarProdutoPorDescricao(String descricao){
        return produtoRepository.buscarProdutosPorDescricao(descricao);
    }

    public Produto editarProduto(Produto produto) {
        return produtoRepository.editarProduto(produto);
    }

    public Produto excluirProduto(int id) {
        return produtoRepository.excluirProduto(id);
    }

}
