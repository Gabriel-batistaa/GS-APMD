package br.com.fiap.view;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;


public class CheckBox extends JCheckBox{

	private static final long serialVersionUID = 1L;

	public CheckBox(String texto) {
		super(texto);
		init();
	}
	
	private void init() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	} 
	
}
