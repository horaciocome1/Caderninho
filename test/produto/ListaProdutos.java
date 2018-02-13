/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto;

import java.util.List;
import model.bean.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author horaciocome1
 */
public class ListaProdutos {
    
    // busca todos os produtos
    
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.getLike("tomate");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " : " + produto.getDesc());
            System.out.println("Adquirido a " + produto.getpCompra() + ", vendido a " + produto.getpVenda());
            System.out.println(" == === = === == ");
        }
    }
    
}
