package br.com.fiap.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.InfoPosto.dao.InfoPostoDao;
import br.com.fiap.model.InfoPosto;
import br.com.fiap.model.TipoPlug;
import br.com.fiap.view.CheckBox;
import br.com.fiap.view.MainScreen;


public class BotaoListener implements ActionListener {

	private MainScreen view;
	private InfoPostoDao dao = new InfoPostoDao();

	public BotaoListener(MainScreen view) {
		this.view = view;
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		InfoPosto info = new InfoPosto();
		info.setNome(view.getTxtNome().getText());
		info.setRua(view.getTxtRua().getText());
		info.setBairro(view.getTxtBairro().getText());
		info.setNumero(view.getTxtNumero().getText());
		info.setCidade(view.getTxtCidade().getText());
		info.setEstado(view.getEditTam().getSelectedItem().toString());
		info.setPreco(new BigDecimal(view.getTxtPreco().getText()));
		info.setAvaliacao((long)view.getStarRater().getSelection()>5?5:(long)view.getStarRater().getSelection());
		
		dao.inserir(info);
		
		boolean check = false;
		
		for(CheckBox opcao:view.getListaDeCheckBox()){
			if(opcao.isSelected()) {
				check = true;
				TipoPlug plug = new TipoPlug();
				plug.setPlugs(opcao.getText());
				plug.setPosto(info.getId());
				dao.inserir(plug);
			}
		}
		
		if(check == false) {
			dao.apagar(info);
			return;
		}
		
		view.carregarDados();
		
		JOptionPane.showMessageDialog(null, "O cadastro foi concluído com sucesso");
		
		List<InfoPosto> lista = dao.listarTodosPostos();
		lista.forEach(System.out::println);
		
		
		
	}

}
