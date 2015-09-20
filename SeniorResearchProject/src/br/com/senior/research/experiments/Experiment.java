package br.com.senior.research.experiments;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.senior.research.domain.Persistible;
import br.com.senior.research.domain.TaxiTrajectory;
import br.com.senior.research.utils.Chronometer;
import br.com.senior.research.utils.FileUtils;


/**
 * Classe que funciona como orquestrador do experimento com o MySQL e MongoDB
 * @author Cristiano Roberto Franco
 *
 */
public class Experiment {
	
	private List<TaxiTrajectory> lista = new ArrayList<TaxiTrajectory>();
	private Persistible<List<TaxiTrajectory>> tDAO;
	
	/**
	 * Construtor que recebe por injeção o DAO específico para o banco de dados
	 * @param tDAO dao injetado para o banco de dados específico
	 */
	public Experiment(Persistible<List<TaxiTrajectory>> tDAO){
		this.tDAO = tDAO;
	}
	
	
	/**
	 * Método que cria uma lista com objetos do tipo 
	 * TaxiTrajectory com base nos arquivos dentro do diretório files do projeto
	 * e registra os tempos de persistencia de toda a lista
	 */
	public void write() {
		FileUtils fu = new FileUtils();
		String[] arquivos = fu.getFileNames();
		for (int i = 0; i < arquivos.length; i++) {
			try {
				lista.addAll(fu.readFile(arquivos[i]));
			} catch (FileNotFoundException e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.out.println(lista.size()+" trajectories");
		Chronometer.start();
		tDAO.save(lista);
		Chronometer.stop();
		System.out.println(Chronometer.elapsedTimeInMiliseconds()+ " milisseconds -> write operation.");
	}

	/**
	 * Método cuja função é gerar uma lista de TaxiTrajectory aleatória
	 * e registrar os tempos de busca para cada objeto.
	 */
	public void read(){
		List<TaxiTrajectory> aleatoryList = new ArrayList<TaxiTrajectory>();
		Random generator = new Random();
		for(int i=0; i < 10; i++){
			
			int index = generator.nextInt(lista.size());
			aleatoryList.add(lista.get(index));
			
		}
		Chronometer.start();
		tDAO.query(aleatoryList);		
		Chronometer.stop();
		System.out.println(Chronometer.elapsedTimeInMiliseconds()+ " milisseconds -> read operation.");
		System.out.println("_________________");
	}
	
	

}
