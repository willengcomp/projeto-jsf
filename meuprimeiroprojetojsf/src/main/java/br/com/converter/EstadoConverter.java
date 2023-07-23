package br.com.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Estados;
import br.com.jpautil.JPAUtil;

@FacesConverter(forClass = Estados.class, value = "estadosConverter")
public class EstadoConverter implements Converter, Serializable{

	@Override/*Retorna objeto inteiro*/
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		if(!codigoEstado.equals("--[Selecione]--")) {
			Estados estados = (Estados) entityManager.find(Estados.class, Long.parseLong(codigoEstado));
			return estados;
		} else {
			return null;
		}
		
	}

	@Override/*retorna apenas o codigo em string, aqui o objeto vem do servidor para a tela*/
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		
		if(estado == null) {
			return null;
		}
		
		if(estado instanceof Estados) {
			return ((Estados) estado).getId().toString();
		} else {
			return estado.toString();
		}
		
	}

}
