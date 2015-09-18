package br.com.senior.research.persistence.mysql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.senior.research.domain.Persistible;
import br.com.senior.research.domain.TaxiTrajectory;


/**
 * Classe que interage com o JPA diretamente, desacoplando
 * o mecanismo de persistência do resto da aplicacao
 * @author Cristiano Roberto Franco
 *
 */
public class MySQLTaxiTrajectoryDAO implements Persistible<List<TaxiTrajectory>>{

	private EntityManager em;
	private EntityTransaction tx;
	

	public MySQLTaxiTrajectoryDAO() {
		em = PersistenceUtil.getEntityManager();
		tx = em.getTransaction();
		System.out.println("MySQL");
		
	}
	
	
	/**
	 * Método para persistir a lista de objetos no MYSQL
	 * @param a lista de objetos gerada com base nos arquivos do diretorio files
	 * TODO aplicar o padrão Visitor no processo de persistencia
	 */
	@Override
	public void save(List<TaxiTrajectory> lista) {

		tx.begin();

		for (TaxiTrajectory taxiTrajectory : lista)
			em.persist(taxiTrajectory);
		tx.commit();

	}

	
	/**
	 * Método para buscar um conjunto aleatório de objetos no MYSQL
	 * @param a lista de objetos gerada aleatoriamente
	 * TODO aplicar o padrão Visitor no processo de query
	 */
	@Override
	public void query(List<TaxiTrajectory> aleatoryList) {

		TypedQuery<TaxiTrajectory> query = em.createQuery(
				"select p from TaxiTrajectory p where p.longitude "
						+ "= :longitude and p.latitude = :latitude",
				TaxiTrajectory.class);
		
		for (TaxiTrajectory t : aleatoryList) {
					
			query.setParameter("longitude", t.getLongitude());
			query.setParameter("latitude", t.getLatitude());

			query.getSingleResult();
			
		}
	}
}
