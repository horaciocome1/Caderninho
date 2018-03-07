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

/**
 *
 * @author horaciocome1
 */
public class ProdutoDAO {
    
    public void add(Produto produto) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into produto (nome, descricao, pgueva, pvenda)"
                    + " values (?, ?, ?, ?)");
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDesc());
            stmt.setFloat(3, produto.getpCompra());
            stmt.setFloat(4, produto.getpVenda());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt);
        }
    }
    
    public List<Produto> getAll() {
        // so o id e o nome
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select id, nome from produto");
            rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                produtos.add(p);
            }
            return produtos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
    public List<Produto> getLike(String produto) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from produto where nome LIKE ?");
            stmt.setString(1, "%" + produto + "%");
            rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {                
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDesc(rs.getString("descricao"));
                p.setpCompra(rs.getFloat("pgueva"));
                p.setpVenda(rs.getFloat("pvenda"));
                //p.setQtd(rs.getInt("qtdDisponivel"));
                produtos.add(p);
            }
            return produtos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
    public Produto getOne(int idProduto) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from produto where id = ?");
            stmt.setInt(1,
                    idProduto);
            rs = stmt.executeQuery();
            Produto p = new Produto();
            if (rs.first()) {
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDesc(rs.getString("descricao"));
//                p.setQtd(rs.getInt("qtdDisponivel"));
                p.setpCompra(rs.getFloat("pgueva"));
                p.setpVenda(rs.getFloat("pvenda"));
            }
            return p;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
    public void delete(int idProduto) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con =cf.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from produto where id = ?");
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException();
        } finally {
            cf.closeConnection(con, stmt);
        }
    }
    
    public void update(Produto p) {
        // metodo que altera ou atualiza uma tupla
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update produto set nome = ?, descricao = ?, pgueva = ?, pvenda = ? where id = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDesc());
            stmt.setFloat(3, p.getpCompra());
            stmt.setFloat(4, p.getpVenda());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt);
        }
    }
    
}
