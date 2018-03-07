/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

import javax.swing.JOptionPane;

/**
 *
 * @author horaciocome1
 */
public class teste {
    
    public static void main(String []args){
		String ola;
		ola = "Ola Mundo";
//		System.out.print(ola);
		
		int x, y, soma;
		x = 4;
		y = 3;
//		Scanner s = new Scanner();
                x = Integer.parseInt(JOptionPane.showInputDialog("Introduza o primeiro nr"));
                y = Integer.parseInt(JOptionPane.showInputDialog("Introduza o segundo nr"));
		soma = x + y;
//                System.out.println(soma);
                JOptionPane.showMessageDialog(null, soma);
                
                
                if (true) {
                    
                } else if (true) {
            
                } else {
                    
                }
                
	}
    
}
