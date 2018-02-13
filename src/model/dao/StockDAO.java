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
import model.bean.Stock;

/**
 *
 * @author horaciocome1
 */
public class StockDAO {
    
    public void add(Stock stock) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into stock (dataCompra)"
                    + " values (?)");
            stmt.setDate(1, new Date(stock.getDataCompra().getTimeInMillis()));
            if (!stmt.execute())
                System.out.println("Erro em StockDAO ao fazer ADD");
            else {
                int idStock = getAll().get(getAll().size() - 1).getId(); // o id do ultimo da lista
                StockProdutoDAO spdao = new StockProdutoDAO();
                spdao.add(stock.getProdutos(), idStock); // cruzar os produtos com stock
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt);
        }
    }
    
    public List<Stock> getAll() {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from stock");
            rs = stmt.executeQuery();
            List<Stock> stocks = new ArrayList<>();
            while (rs.next()) {                
                Stock s = new Stock();
                s.setId(rs.getInt("id"));
                Calendar dataCompra = Calendar.getInstance();
                dataCompra.setTime(rs.getDate("dataCompra"));
                s.setDataCompra(dataCompra);
                StockProdutoDAO spdao = new StockProdutoDAO();
                s.setProdutos(spdao.getProdutosFromStock(rs.getInt("id")));
                stocks.add(s);
            }
            return stocks;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            cf.closeConnection(con, stmt, rs);
        }
    }
    
}
