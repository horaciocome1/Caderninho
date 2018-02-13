/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Produto;
import model.bean.Stock_Produto;

/**
 *
 * @author horaciocome1
 */
public class StockProdutoDAO {
    
    public void add(List<Produto> produtos, int idStock) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        for (Produto produto : produtos) {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("insert into stock_produto (idStock, idProduto, qtdComprada)"
                        + " values (?, ?, ?)");
                stmt.setInt(1, idStock);
                stmt.setInt(2, produto.getId());
                stmt.setInt(3, produto.getQtd()); 
                boolean sucesso = stmt.execute();
                if (sucesso)
                    stmt.close();
                else
                    System.out.println("Erro em StockProdutoDAO ao fazer ADD");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                cf.closeConnection(con, stmt);
            }
        }
    }
    
    public List<Stock_Produto> getAll() {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from stock_produto");
            rs = stmt.executeQuery();
            List<Stock_Produto> list = new ArrayList<>();
            while (rs.next()) {                
                Stock_Produto sp = new Stock_Produto();
                sp.setId(rs.getInt("id"));
                sp.setIdStock(rs.getInt("idStock"));
                sp.setIdProduto(rs.getInt("idProduto"));
                sp.setQtdArmazenada(rs.getInt("qtdComprada"));
                list.add(sp);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
    public List<Produto> getProdutosFromStock(int idStock) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from stock_produto"
                    + " where idStock = ?");
            stmt.setInt(1, idStock);
            rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {                
                Produto p = new Produto();
                p.setId(rs.getInt("idProduto"));
                p.setQtd(rs.getInt("qtdArmazenada"));
                ProdutoDAO pdao = new ProdutoDAO();
                p.setNome(pdao.getOne(rs.getInt("idProduto")).getNome());
                p.setDesc(pdao.getOne(rs.getInt("idProduto")).getDesc());
                p.setpCompra(pdao.getOne(rs.getInt("idProduto")).getpCompra());
                p.setpVenda(pdao.getOne(rs.getInt("idProduto")).getpVenda());
                produtos.add(p);
            }
            return produtos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
}
