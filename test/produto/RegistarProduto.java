/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto;

import java.util.Random;
import model.bean.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author horaciocome1
 */
public class RegistarProduto {
    
    // armazenar N produtos aleatorios
    
    public static void main(String[] args) {
        int n = 10;
        Produto produto = new Produto();
        Random aleatorio = new Random();
        ProdutoDAO dao = new ProdutoDAO();
        String[] nome = {"Tomate", "Agua Limpopo", "Coco", "Alface", "Feijao"};
        String[] desc = {"Tomate verde. Vendidos em grupos de 3.",
                "1.5L de agua engarrafada do Limpopo",
                "Coco medio proveniente de Maxixe",
                "Molho de alface do Mulauze",
                "2.5Kg de feijao nacional"};
        float[] pCompra = {280f, 35,50f, 35f, 20f, 72,20f};
        float[] pVenda = {294,50f, 40f, 39f, 23,50f, 97f};
        int position;
        for (int i = 0; i < n; i++) {
            position = aleatorio.nextInt(nome.length - 1);
            produto.setNome(nome[position]);
            produto.setDesc(desc[position]);
            produto.setpCompra(pCompra[position]);
            produto.setpVenda(pVenda[position]);
            dao.add(produto);
        }
    }
    
}
