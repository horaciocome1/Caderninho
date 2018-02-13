/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strings;

/**
 *
 * @author horaciocome1
 */
public class Strings {
    
    public String preenchaCampo; // alerta que um campo deve ser preenchido
    public String obrigatorio; // retorna um asterix  
    public String precoVazio; // a condicao do jformatedtextfield sem nenhum numero introduzido
    public String confirmar_cadProduto; // mensagem para o dialogo

    public Strings() {
        this.preenchaCampo = "Preencha este campo!";
        this.obrigatorio = "*";
        this.precoVazio = " ,   .   MT";
        this.confirmar_cadProduto = "Tem certeza que pretende cadastrar este produto? Pode voltar e editar.";
    }
    
}
