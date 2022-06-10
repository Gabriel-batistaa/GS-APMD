package br.com.fiap.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.InfoPosto.dao.InfoPostoDao;
import br.com.fiap.controller.BotaoCancelar;
import br.com.fiap.controller.BotaoConsulta;
import br.com.fiap.controller.BotaoGerarMapa;
import br.com.fiap.controller.BotaoListener;
import br.com.fiap.controller.ListaListener;
import br.com.fiap.model.InfoPosto;
import br.com.fiap.model.TipoPlug;


//Definindo as variáveis globais

public class MainScreen {

	private JTextField txtNome = new JTextField(12);
	private JTextArea txtEndereco = new JTextArea(0, 0);
	private JTextField txtRua = new JTextField(12);
	private JTextField txtNumero = new JTextField(12);
	private JTextField txtBairro = new JTextField(12);
	private JTextField txtCidade = new JTextField(12);
	private JTextField txtPreco = new JTextField(12);
	private StarRater starRater = new StarRater(5);
	private List<CheckBox> listaDeCheckBox = new ArrayList<>();
	
	String [] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

	List<InfoPosto>lista = new ArrayList<InfoPosto>();

	public JComboBox<String> editTam = new JComboBox<String>(estados);
	
	public JComboBox<String> getEditTam() {
		return editTam;
	}

	String[] colunas = {"Nome", "Rua", "Numero", "Bairro", "Cidade", "Estado", "Preco", "Plugs", "Avaliação"};
	DefaultTableModel tableModel = new DefaultTableModel(colunas,0);
	JTable tabela = new JTable(tableModel);
	
	
	
	public List<CheckBox> getListaDeCheckBox() {
		return listaDeCheckBox;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextArea getTxtEndereco() {
		return txtEndereco;
	}

	public JTextField getTxtRua() {
		return txtRua;
	}
	
	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public JTextField getTxtBairro() {
		return txtBairro;
	}

	public JTextField getTxtCidade() {
		return txtCidade;
	}
	
	public String[] getEstados() {
		return estados;
	}
	
	public JTextField getTxtPreco() {
		return txtPreco;
	}
	
	public StarRater getStarRater() {
		return starRater;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

    public MainScreen() {
        init();
    }

    
 // Configura a Aba Incial 
    
    private void init() {
    	
        JFrame janela = new JFrame("Postos de abastecimentos de carros elétricos");	
        
        JPanel imagem = setImagem();
		
		JPanel configField = setConfigField();
		
		JPanel opcoes = setBotoes();
		
		editTam.setPreferredSize(new Dimension(135,20));

		JPanel cadastro = new JPanel();
		cadastro.setLayout(new GridLayout());
		cadastro.add(imagem);	
		cadastro.add(configField);
		cadastro.add(opcoes);
		
		ListaListener listaListener = new ListaListener(this);
		tabela.getSelectionModel().addListSelectionListener(listaListener);

		JTabbedPane abas = new JTabbedPane();
		abas.add("Cadastro", cadastro);
		abas.add("Banco de dados", setAbaBD());
		carregarDados();
		
		janela.add(abas);
		janela.setSize(550, 425);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		
		
    }

 // Carrega os ID's dos postos e os tipos de plgus e faz a checagem entre eles
    
    public void carregarDados() {
		tableModel.setRowCount(0);
		lista = new InfoPostoDao().listarTodosPostos();
		List<TipoPlug> listaDePlugs = new InfoPostoDao().listarTodosPlug();
		listaDePlugs.forEach(plug -> {
			System.out.println(plug.getPlugs());
		});
		lista.forEach(posto -> {
			listaDePlugs.forEach(plug -> {
				if(plug.getPosto() == posto.getId())
					posto.setPlugs(posto.getPlugs().concat(plug.getPlugs()+ ","));
			});
		});
		
		lista.forEach(InfoPosto -> tableModel.addRow(InfoPosto.getData()));	
	}
	 
	public static void main(String[] args) {
		new MainScreen();
	}

	///////////////////////////////
	//Definindo  paineis
	
	// Configura a capa do posto 
	private JPanel setImagem() {
		JPanel imagem = new JPanel();
		ImageIcon capa = new ImageIcon("C:/Users/gbdc5/Downloads/posto_eletrico.jpg");
		ImageIcon novaCapa = new ImageIcon(capa.getImage().getScaledInstance(285, 350, Image.SCALE_DEFAULT));
		imagem.add(new JLabel(novaCapa));
		return imagem;
	}

	// Configura os botões
	private JPanel setBotoes() {
		List<String> tiposPlug = new ArrayList<>();
		tiposPlug.add("tipo1");
		tiposPlug.add("tipo2");
		tiposPlug.add("CSS2");
		tiposPlug.add("CHAdeMO");
		
		JPanel addopcoes = new JPanel();
		addopcoes.add(new MeuLabel("Tipo de plug disponível"));
		
		
		RadioGroup opcoes = new RadioGroup(tiposPlug);
		listaDeCheckBox = opcoes.getOpcoes();
		opcoes.add(new MeuLabel("Avaliação"));
		opcoes.add(starRater);
		addopcoes.add(opcoes);
		return addopcoes;
	}

	// Configura o formulário central
	
	
	private JPanel setConfigField() {
	
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		JPanel configField = new JPanel();
		configField.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		configField.add(new MeuLabel("Nome"));
		configField.add(txtNome);
		
		configField.add(new MeuLabel("Endereco:"));
		configField.add(txtEndereco);
		
		configField.add(new MeuLabel("Rua"));
		configField.add(txtRua);
		
		configField.add(new MeuLabel("Número"));
		configField.add(txtNumero);
		
		configField.add(new MeuLabel("Bairro"));
		configField.add(txtBairro);
		
		configField.add(new MeuLabel("Cidade"));
		configField.add(txtCidade);
		
		configField.add(new MeuLabel("Estado"));
		configField.add(editTam);
		
		configField.add(new MeuLabel("Preço do kWh"));
		configField.add(txtPreco);
				
		JPanel botoes = new JPanel();	
		botoes.add(btnSalvar);
		botoes.add(btnCancelar);
		
		BotaoListener cadastroListener = new BotaoListener(this);
		btnSalvar.addActionListener(cadastroListener);
		
		BotaoCancelar listener = new BotaoCancelar(this);
		btnCancelar.addActionListener(listener);
		
		configField.add(botoes);
		return configField;
			
	}
	
	// Configura o a Aba do Banco de Dados
	
	private JPanel setAbaBD() {
		
		JScrollPane tamanhoTab = new JScrollPane(tabela);
		tamanhoTab.setPreferredSize(new Dimension (525, 312));
		
		JPanel configAbaBD = new JPanel();
		
	
		JButton btnOrdEst = new JButton("Ordenar por Estado");
		JButton btnMapa = new JButton("Gerar um mapa do posto (Selecione um item)");
		
		BotaoGerarMapa botaoGerarMapa = new BotaoGerarMapa(this);
		btnMapa.addActionListener(botaoGerarMapa);
		
		JPanel botoesBD = new JPanel();	
		botoesBD.add(btnOrdEst);
		botoesBD.add(btnMapa);
		
		configAbaBD.add(botoesBD);
		configAbaBD.add(tamanhoTab);
		
		BotaoConsulta listener = new BotaoConsulta(this);
		btnOrdEst.addActionListener(listener);
		
		return configAbaBD;
		
	}

	// Métodos para a integração da API Google Maps
		
	public void ordenar() {
		
		tableModel.setRowCount(0);
		
		for (int i = 0; i<lista.size()-1; i++) {
			for(int j = i+1; j<lista.size(); j++) {
				if(lista.get(i).getData().get(5).compareTo(lista.get(j).getData().get(5))>0) {
					InfoPosto temp = lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
	
		lista.forEach(InfoPosto -> tableModel.addRow(InfoPosto.getData()));	
	}	
	
	String endereco;
	
	public void extrairEnd () {
		int linha = tabela.getSelectedRow();
		endereco = tabela.getModel().getValueAt(linha, 1).toString();
		endereco = endereco.concat("," + tabela.getModel().getValueAt(linha, 5).toString());
		endereco = endereco.replace(" ", "_");
	}
	
	public void gerarMapa() {
		
		
		String url = "https://maps.googleapis.com/maps/api/staticmap?zoom=15&size=" + 600 + "x" + 500 + "&markers=color:red|"+ endereco + "&key=AIzaSyDsR8wA0gau9Ai7YP99QNMbpqjUtcmOTMs"; 
		
		JFrame mapa = new JFrame("Endereço estático");
		mapa.setSize(600, 500);
		mapa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mapa.setVisible(true);
		
		try {
			mapa.add(new JLabel(new ImageIcon(ImageIO.read(new URL(url)))));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

