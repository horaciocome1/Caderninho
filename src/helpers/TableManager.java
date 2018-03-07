/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Produto;
import model.bean.Stock;
import model.bean.Venda;
import model.dao.ProdutoDAO;

/**
 *
 * @author horaciocome1
 * 
 * esta class encapsula a gestao de tabelas
 * adiciona itens a tabela
 * retorna o objecto do item seleccionado
 * aumenta qtd 
 */
public class TableManager {
    
    public DefaultTableModel rowModel(javax.swing.JTable tabela) {
        // este metodo serve para definir o modelo para as tabelas
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        tabela.setRowSorter(new TableRowSorter(modelo));
        return modelo;
    }
    
    public void updateProdutos(javax.swing.JTable tabela, String nome) {
        // este metodo carrega os dados do banco para a tabela tbProduto
        rowModel(tabela).setRowCount(0);
        ProdutoDAO pdao = new ProdutoDAO();
        for (Produto produto : pdao.getLike(nome)) {
            rowModel(tabela).addRow(new Object[]{produto.getId(), produto.getNome()});
        }
    }
    
    public void preencherTable1_stock(javax.swing.JTable tabela, List<Stock> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        lista.forEach((stock) -> {
            modelo.addRow(new Object[]{
                stock.getId(),
                stock.getDataCompra()
            });
        });
    }
    
    public void preencherTable2_stock(javax.swing.JTable tabela, List<Produto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        lista.forEach((produto) -> {
            modelo.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getQtd(),
            });
        });
    }
    
    public void preencherTable1_cadStock(javax.swing.JTable tabela, List<Produto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        lista.forEach((produto) -> {
            modelo.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
            });
        });
        
    }
    
    public void preencherTable2_cadStock(javax.swing.JTable tabela, List<Produto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        lista.forEach((produto) -> {
            modelo.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getQtd(),
            });
        });
    }
    
    public void preencherTable1_venda(javax.swing.JTable tabela, List<Venda> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        String pago;
        for (Venda venda : lista) {
            if (venda.isEstado())
                pago = "YES";
            else
                pago = "NO";
            modelo.addRow(new Object[]{
                venda.getId(),
                venda.getData(),
                venda.getTotal(),
                pago
            });
        }
    }
    public void preencherTable2_venda(javax.swing.JTable tabela, List<Produto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        lista.forEach((produto) -> {
            modelo.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getQtd(),
            });
        });
    }
    
    public void preencherTable1_cadVenda(javax.swing.JTable tabela, List<Produto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        lista.forEach((Produto produto) -> {
            if (produto.getQtd() > 0) // so produtos disponiveis
                modelo.addRow(new Object[]{
                    produto.getId(),
                    produto.getpVenda(),
                    produto.getNome(),
                    produto.getQtd(),
                });
            
        });
        
    }
    public void preencherTable2_cadVenda(javax.swing.JTable tabela, List<Produto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        lista.forEach((produto) -> {
            modelo.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getQtd(),
            });
        });
    }
    
}