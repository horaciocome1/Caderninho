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
import model.bean.Venda_Produto;

/**
 *
 * @author horaciocome1
 */
public class VendaProdutoDAO {
    
    public void add(List<Produto> produtos, int idVenda) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        for (Produto produto : produtos) {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("insert into venda_produto (idVenda, idProduto, qtd)"
                        + " values (?, ?, ?)");
                stmt.setInt(1, idVenda);
                stmt.setInt(2, produto.getId());
                stmt.setInt(3, produto.getQtd()); // aqui a qdtdivponivel e utilizada como qtdVendidada
                stmt.execute();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                cf.closeConnection(con, stmt);
            }
        }
    }
    
    public List<Venda_Produto> getAll() {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from venda_produto");
            rs = stmt.executeQuery();
            List<Venda_Produto> list = new ArrayList<>();
            while (rs.next()) {                
                Venda_Produto vp = new Venda_Produto();
                vp.setId(rs.getInt("id"));
                vp.setIdVenda(rs.getInt("idVenda"));
                vp.setIdProduto(rs.getInt("idProduto"));
                vp.setQtdVendida(rs.getInt("qtd"));
                list.add(vp);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
    public List<Produto> getProdutosFromVenda(int idVenda) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from venda_produto"
                    + " where idVenda = ?");
            stmt.setInt(1, idVenda);
            rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {                
                Produto p = new Produto();
                p.setId(rs.getInt("idProduto"));
                p.setQtd(rs.getInt("qtdVendida"));
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
