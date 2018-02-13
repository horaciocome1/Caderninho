/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import strings.Strings;

/**
 *
 * @author horaciocome1
 * 
 * controla como sao preenchidos os campos de texto
 * de modo que esteja tudo em conforme com o que o banco de dados espera
 * 
 */
public class InputManager {
        Strings strings;

    public InputManager() {
        this.strings = new Strings();
    }
    
    public void nomeProduto(javax.swing.JTextField txt, javax.swing.JLabel lbl) {
        // este metodo gere as alteracoes no campo para nome do produto
        if (txt.getText().isEmpty())
            lbl.setText(strings.obrigatorio);
        else {
            lbl.setText("");
            if (txt.getText().length() > 20)
                txt.setText(txt.getText().substring(0, 19)); // o banco de dados foi modelado para receber 20caracteres
        }
    }
    
    public float preco(javax.swing.JFormattedTextField ftxt) {
        // este metodo eh responsavel por garantir que os precos fiquem no formato esperado pelo banco
        // 6,2
        if (!(ftxt.getText().equalsIgnoreCase(strings.precoVazio))) {
            char vet[] = ftxt.getText().trim().substring(0, 8).toCharArray(); // 0,234.67 os indices
            for (int i = 1; i < vet.length - 1; i++) { // comeca da virgula ao penultimo
    //            isto e para eliminar a virgula de que separa as centenas dos milhares
                vet[i] = vet[i + 1];
            }
            return Float.parseFloat(String.valueOf(vet, 0, 7));
        } else
            return -1f; // sem preco introduzido
    }
    
    public void preco(javax.swing.JFormattedTextField ftxt, javax.swing.JLabel lbl) {
        // este metodo gere as alteracoes nos campos de preco
        if (ftxt.getText().equalsIgnoreCase(strings.precoVazio)) {
            lbl.setText(strings.obrigatorio);
            ftxt.setText("000000");
            lbl.setText("");
        } else
            lbl.setText("");
    }
    
}
