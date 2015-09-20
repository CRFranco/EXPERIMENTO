package br.com.senior.research.persistence.mysql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilitária para o JPA
 * @author Cristiano Roberto Franco
 *
 */
public class PersistenceUtil {
	
	private static EntityManagerFactory emf;
	
	private PersistenceUtil() {
		super();
	}
	/**
	 * Singleton do EntityManager usado pelo JPA
	 * @return entity manager
	 */
	public static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("research");
		}
		return emf.createEntityManager();
	}
	
	public static void close(EntityManager em) {
		if (em != null) {
			em.close();
		}
	}
	
	public static void close() {
		if (emf != null) {
			emf.close();
		}
	}
}