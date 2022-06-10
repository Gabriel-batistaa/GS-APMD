package br.com.fiap.controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.fiap.view.MainScreen;

public class ListaListener implements ListSelectionListener{
	
	private MainScreen view;

	public ListaListener(MainScreen view) {
		this.view = view;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if(e.getValueIsAdjusting())
			view.extrairEnd();
		
	}

	
}
