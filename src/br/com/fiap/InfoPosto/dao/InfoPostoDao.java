package br.com.fiap.InfoPosto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.fiap.model.InfoPosto;
import br.com.fiap.model.TipoPlug;

public class InfoPostoDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("InfoPosto");
	EntityManager manager = factory.createEntityManager();
	
	public void inserir (InfoPosto info) {

		manager.getTransaction().begin();
		manager.persist(info);
		manager.getTransaction().commit();
	}
	
	public List<InfoPosto> listarTodosPostos () {
		String jpql = "SELECT i FROM InfoPosto i";
		TypedQuery<InfoPosto> query = manager.createQuery(jpql , InfoPosto.class);
		
		return query.getResultList();
	
	}
	
	public void apagar(InfoPosto info) {
		manager.getTransaction().begin();
		manager.remove(info);
		manager.getTransaction().commit();
	}
	
	public void inserir (TipoPlug plugs) {

		manager.getTransaction().begin();
		manager.persist(plugs);
		manager.getTransaction().commit();
	}
	
	public List<TipoPlug> listarTodosPlug () {
		String jpql = "SELECT i FROM TipoPlug i";
		TypedQuery<TipoPlug> query = manager.createQuery(jpql , TipoPlug.class);
		
		return query.getResultList();
	
	}
}
