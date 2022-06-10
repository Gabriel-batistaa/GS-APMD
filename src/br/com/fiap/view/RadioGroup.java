package br.com.fiap.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioGroup extends JPanel{ 

	private static final long serialVersionUID = 1L;
	private List<String> opcoes;
	private List<CheckBox> checkBox = new ArrayList<>();
	
	private ButtonGroup grupo = new ButtonGroup(); 
	
	public RadioGroup (List<String> opcoes) {
		super();
		this.opcoes = opcoes;
		init();
		
	}
	
	public void init() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		opcoes.forEach(opcao -> {
		CheckBox radio = new CheckBox(opcao);
		this.add(radio);
		checkBox.add(radio);
		});
		
	}

	public List<CheckBox> getOpcoes() {
		return checkBox;
	}
}
