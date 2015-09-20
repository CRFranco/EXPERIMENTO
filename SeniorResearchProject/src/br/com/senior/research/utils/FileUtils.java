package br.com.senior.research.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.senior.research.domain.TaxiTrajectory;

/**
 * Classe utilitária para as operações com os arquivos que contém os 
 * dados de entrada
 * 
 * @author Cristiano Roberto Franco
 *
 */
public class FileUtils {

	

	
	/**
	 * Método para obter um vetor de string com os nomes dos arquivos existentes dentro do
	 * diretorio files. Esta implementação permite que sejam adicionados ou
	 * removidos arquivos do diretorio de forma transparente para a aplicação
	 * 
	 * @return array de strings contendo o nome e caminho dos arquivos
	 */
	public String[] getFileNames(){
		File diretorio = new File("files");
		
		File[] arquivos = diretorio.listFiles();
		String[] nomes = new String[arquivos.length];
		for (int i = 0; i<arquivos.length;i++) {
			nomes[i]=arquivos[i].getAbsolutePath();
			
		}
		
		return nomes;
		
	}

	/**
	 * Método que faz o parse dos dados do arquivo para objetos, gerando uma lista com
	 * todos que forem encontrados nos arquivos dentro do diretorio files
	 * @param fileName - nome do arquivo
	 * @return lista de objetos com as TaxiTrajectory para inserção nos bancos
	 * @throws FileNotFoundException - caso não haja arquivo no diretório
	 */
	public List<TaxiTrajectory> readFile(String fileName) throws FileNotFoundException {
		List<TaxiTrajectory> taxis = new ArrayList<>();

		Scanner scanner = new Scanner(new FileReader(fileName))
				.useDelimiter("[,\n]");

		while (scanner.hasNext()) {
			Long id = scanner.nextLong();
			String dataStr = scanner.next();
			Date date = DateUtils.stringToDate(dataStr);
			String longitudeStr = scanner.next();
			Double longitude = Double.parseDouble(longitudeStr);
			String latitudeStr = scanner.next();
			Double latitude = Double.parseDouble(latitudeStr);

			// TODO trocar por uma factory
			taxis.add(new TaxiTrajectory(id, date, longitude, latitude));
			
		}
		scanner.close();
		return taxis;

	}

}
