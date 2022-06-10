package br.com.fiap.model;

import java.math.BigDecimal;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;



@Entity
public class InfoPosto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Nome;
	private String Rua;
	private String Numero;
	private String Bairro;
	private String Estado;
	private String Cidade;
	private BigDecimal Preco;
	private Long Avaliacao;
	
	@Transient
	private String plugs = "";  
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return Nome;
	}
	
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	
	public BigDecimal getPreco() {
		return Preco;
	}
	
	
	public void setPreco(BigDecimal preco) {
		Preco = preco;
	}
	
	public String getRua() {
		return Rua;
	}
	
	
	public void setRua(String rua) {
		Rua = rua;
	}
	
	public String getNumero() {
		return Numero;
	}
	
	
	public void setNumero(String numero) {
		Numero = numero;
	}
	
	public String getBairro() {
		return Bairro;
	}
	
	
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	
	
	public String getCidade() {
		return Cidade;
	}
	
	
	public void setCidade(String cidade) {
		Cidade = cidade;
	}


	public String getEstado() {
		return Estado;
	}
	
	public void setEstado(String estado) {
		Estado = estado;
	}
	
	public long getAvaliacao() {
		return Avaliacao;
	}


	public void setAvaliacao(long avaliacao) {
		Avaliacao = avaliacao;
	}
	
	public String getPlugs() {
		return plugs;
	}

	public void setPlugs(String plugs) {
		this.plugs = plugs;
	}


	public Vector<String> getData() {
	
		Vector<String> data = new Vector<String>();	
		data.add(Nome);
		data.add(Rua);
		data.add(Numero);
		data.add(Bairro);
		data.add(Cidade);
		data.add(Estado);
		data.add(Preco.toString());
		data.add(plugs);
		data.add(Avaliacao.toString());
		
		return data;
		
		
	}


}
