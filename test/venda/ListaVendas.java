package venda;

import java.util.List;
import model.bean.Produto;
import model.bean.Venda;
import model.dao.VendaDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author horaciocome1
 */
public class ListaVendas {
    
    public static void main(String[] args) {
        VendaDAO dao = new VendaDAO();
        List<Venda> vendas = dao.getAll();
        for (Venda venda : vendas) {
            System.out.println(venda.getData());
            if (venda.isEstado())
                System.out.println("Venda paga");
            else
                System.out.println("Venda nao paga");
            System.out.println("Em nome de " + venda.getOrdenante() + ", num total de " 
                    + String.valueOf(venda.getTotal()));
            System.out.println("Nota: " + venda.getNota());
            List<Produto> produtos = venda.getProdutos();
            for (Produto produto : produtos) {
                System.out.println(produto.getNome() + " : " + produto.getDesc());
                System.out.println("Quantidade" + produto.getQtd());
                System.out.println(" == === = === == ");
            }
            System.out.println("");
        }
    }
    
}
