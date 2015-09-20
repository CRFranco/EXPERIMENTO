package br.com.senior.research.persistence.mongodb;

import java.util.List;

import org.bson.Document;

import br.com.senior.research.domain.Persistible;
import br.com.senior.research.domain.TaxiTrajectory;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Classe que interage com o MongoDB diretamente, desacoplando o mecanismo de
 * persistência do resto da aplicacao
 * 
 * @author Cristiano Roberto Franco
 *
 */
public class MongoTaxiTrajectoryDAO implements
		Persistible<List<TaxiTrajectory>> {

	private MongoClient mongo;
	private MongoDatabase db;
	private MongoCollection<Document> colecao;

	public MongoTaxiTrajectoryDAO() {
		mongo = new MongoClient("localhost", 27017);

		mongo.dropDatabase("research");// elimina a base de dados anterior

		db = mongo.getDatabase("research");

		colecao = db.getCollection("TaxiTrajectory");
		System.out.println("Mongo");
	}

	/**
	 * Método para persistir a lista de objetos no MongoDB
	 * 
	 * @param list - a   lista de objetos gerada com base nos arquivos do diretorio
	 *            files TODO aplicar o padrão Visitor no processo de
	 *            persistencia
	 */
	@Override
	public void save(List<TaxiTrajectory> list) {
		// no JPA não há maneira de fazer o insertMany sem implementação, então
		// foi utilizado o mesmo princípio aqui para igualdade de
		// condições através do insertOne
		for (TaxiTrajectory t : list) {

			colecao.insertOne(new Document().append("taxiID", t.getTaxiID())
					.append("DateTime", t.getDateTime())
					.append("longitude", t.getLongitude())
					.append("latitude", t.getLatitude()));
		}
	}

	/**
	 * Método para buscar um conjunto aleatório de objetos no MYSQL
	 * 
	 * @param list -  a lista de objetos gerada aleatoriamente TODO aplicar o padrão
	 *            Visitor no processo de query
	 */
	@Override
	public void query(List<TaxiTrajectory> list) {
		FindIterable<Document> iterable = null;
		MongoCollection<Document> colecao = db.getCollection("TaxiTrajectory");
		

		for (TaxiTrajectory t : list) {
			iterable = colecao.find(new Document().append("longitude",
					t.getLongitude()).append("latitude", t.getLatitude()));
			
			}
		}

}
