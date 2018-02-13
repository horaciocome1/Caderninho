/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author horaciocome1
 * para saber que produtos foram armazenados em determinado stock
 */
public class Stock_Produto {
    
    private int id, idStock, idProduto, qtdArmazenada; // qtdArmazenada = qtdComprada

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtdArmazenada() {
        return qtdArmazenada;
    }

    public void setQtdArmazenada(int qtdArmazenada) {
        this.qtdArmazenada = qtdArmazenada;
    }
    
    
    
}
