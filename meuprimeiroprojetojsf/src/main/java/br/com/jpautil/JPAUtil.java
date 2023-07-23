package br.com.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {//Classe responsável por fazer a administração do acesso ao BD

	private static EntityManagerFactory factory = null;

	static {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static Object getEntityId(Object entity) {
		Object id = factory.getPersistenceUnitUtil().getIdentifier(entity); 
		return id;
	}
}
