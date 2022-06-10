package br.com.fiap.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.fiap.view.MainScreen;

public class BotaoCancelar implements ActionListener{
	
	private MainScreen view;
	
	
	public BotaoCancelar(MainScreen view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
