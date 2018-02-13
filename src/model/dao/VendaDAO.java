/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.bean.Venda;

/**
 *
 * @author horaciocome1
 */
public class VendaDAO {
    
    public void add(Venda venda) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into venda (data, ordenante, estado, nota, total)"
                    + " values (?, ?, ?, ?, ?)");
            stmt.setDate(1, new Date(venda.getData().getTimeInMillis()));
            stmt.setString(2, venda.getOrdenante());
            stmt.setBoolean(3, venda.isEstado());
            stmt.setString(4, venda.getNota());
            stmt.setFloat(5, venda.getTotal());
            stmt.execute();
            int idVenda = getAll().get(getAll().size() - 1).getId();
            VendaProdutoDAO vpdao = new VendaProdutoDAO();
            vpdao.add(venda.getProdutos(), idVenda);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt);
        }
    }
    
    public List<Venda> getAll() {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from venda");
            rs = stmt.executeQuery();
            List<Venda> vendas = new ArrayList<>();
            while (rs.next()) {                
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                Calendar dataVenda = Calendar.getInstance();
                dataVenda.setTime(rs.getDate("data"));
                v.setData(dataVenda);
                v.setOrdenante(rs.getString("ordenante"));
                v.setEstado(rs.getBoolean("estado"));
                v.setNota(rs.getString("nota"));
                v.setTotal(rs.getFloat("total"));
                VendaProdutoDAO vpdao = new VendaProdutoDAO();
                v.setProdutos(vpdao.getProdutosFromVenda(rs.getInt("id")));
                vendas.add(v);
            }
            return vendas;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
}
