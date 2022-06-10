package br.com.fiap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoPlug {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long posto;
	private String plugs;
	
	public String getPlugs() {
		return plugs;
	}
	public void setPlugs(String plugs) {
		this.plugs = plugs;
	}
	public Long getPosto() {
		return posto;
	}
	public void setPosto(Long posto) {
		this.posto = posto;
	}
	
}
