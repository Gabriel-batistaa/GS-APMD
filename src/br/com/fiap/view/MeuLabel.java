package br.com.fiap.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;


public class MeuLabel extends JLabel{
	
		private static final long serialVersionUID = 1L;
		private int fontSize = 11;
		
		public MeuLabel (String texto) {
			super(texto);
			init();
	
		}
		
		public MeuLabel (String texto, int fontSize) {
			
			super(texto);
			this.fontSize = fontSize;
			init();	
			
		}

		private void init() {
	
			this.setHorizontalAlignment(LEFT);
			this.setForeground(new Color(26, 108, 163));
			this.setFont(new Font(null, Font.BOLD, this.fontSize));
			
		}
		

		


}
