/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import model.bean.Produto;
import model.bean.Venda;
import model.dao.VendaDAO;

/**
 *
 * @author horaciocome1
 */
public class RegistarVendas {
    
    // realizar n vendas aleatorias
    
    public static void main(String[] args) {
        int n = 10, numeroDeProdutos; // tudo ficticio
        Produto produto = new Produto();
        Venda venda = new Venda();
        VendaDAO dao = new VendaDAO();
        Random aleatorio = new Random();
        int[] qtd = {3, 6, 12, 7, 2, 22, 4, 11, 17, 326}; // quantidades quaisquer
        String[] ordenante = {"Azevedo Tunder", "Plasticos Beluluane", "Moz Binario", "Lirca Cirta"};
        String[] nota = {"Foi para o vizinho da frente", "Foi para o primo", "parcelas"};
        float[] total = {1260f, 350f, 5607f, 49f, 1.50f, 18094f};
        for (int i = 0; i < n; i++) {
            venda.setData(Calendar.getInstance());
            venda.setEstado(aleatorio.nextBoolean());
            venda.setOrdenante(ordenante[aleatorio.nextInt(ordenante.length - 1)]);
            venda.setNota(nota[nota.length - 1]);
            venda.setTotal(total[aleatorio.nextInt(total.length - 1)]);
            List<Produto> produtos = new ArrayList<>();
            numeroDeProdutos = aleatorio.nextInt(100);
            for (int j = 0; j < numeroDeProdutos; j++) { // produtos a meter na lista de cada venda
                produto.setId(aleatorio.nextInt(numeroDeProdutos));
                produto.setQtd(aleatorio.nextInt(qtd.length - 1));
                produtos.add(produto);
            }
            venda.setProdutos(produtos);
            dao.add(venda);
        }
    }
    
}
// LER ANTES
// estava a criar o string maker para o objecto venda
// quando voltar vou implementar na classe confirmarVenda bye