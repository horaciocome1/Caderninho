/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.util.List;
import model.bean.Produto;
import model.bean.Stock;
import model.dao.StockDAO;

/**
 *
 * @author horaciocome1
 */
public class ListaStocks {
    
    public static void main(String[] args) {
        StockDAO dao = new StockDAO();
        List<Stock> stocks = dao.getAll();
        for (Stock stock : stocks) {
            System.out.println(stock.getDataCompra());
            List<Produto> produtos = stock.getProdutos();
            for (Produto produto : produtos) {
                System.out.println(produto.getNome() + " : " + produto.getDesc());
                System.out.println("Adquirido a " + produto.getpCompra() + ", vendido a " + produto.getpVenda());
                System.out.println("Quantidade" + produto.getQtd());
                System.out.println(" == === = === == ");
            }
            System.out.println("");
        }
    }
    
}
