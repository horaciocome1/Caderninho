/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author horaciocome1
 * 
 * gere as transicoes entre paineis
 */
public class PanelManager {
    
    public void loadPanel(javax.swing.JPanel pai, javax.swing.JPanel filho) {
        pai.removeAll();
        pai.add(filho);
        pai.repaint();
        pai.revalidate();
    }
    
}
