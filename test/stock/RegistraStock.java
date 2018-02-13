/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import model.bean.Produto;
import model.bean.Stock;
import model.dao.StockDAO;

/**
 *
 * @author horaciocome1
 */
public class RegistraStock {
    
    // registrar 1000Stocks aleatorios
    
    public static void main(String[] args) {
        int n = 10, numeroDeProdutos; // tudo ficticio
        Produto produto = new Produto();
        Stock stock = new Stock();
        StockDAO dao = new StockDAO();
        Random aleatorio = new Random();
        int[] qtdArmazenada = {3, 6, 12, 7, 2, 22, 4, 11, 17, 326}; // quantidades quaisquer
        for (int i = 0; i < n; i++) {
            stock.setDataCompra(Calendar.getInstance());
            List<Produto> produtos = new ArrayList<>();
            numeroDeProdutos = aleatorio.nextInt(100);
            for (int j = 0; j < numeroDeProdutos; j++) { // produtos a meter na lista de cada stock
                produto.setId(aleatorio.nextInt(numeroDeProdutos));
                produto.setQtd(aleatorio.nextInt(qtdArmazenada.length - 1));
                produtos.add(produto);
            }
            stock.setProdutos(produtos);
            dao.add(stock);
        }
    }
    
}
