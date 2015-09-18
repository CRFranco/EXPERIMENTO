package br.com.senior.research.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe utilitária para transformar uma string em uma data no formato
 * existente nos dados de input da aplicação
 * 
 * @author Cristiano Roberto Franco
 *
 */
class DateUtils {

	/**
	 * Metodo que converte uma string em um objeto do tipo Date, conforme o
	 * formatador
	 * 
	 * @param dataStr
	 * @return TODO a String de formatação deve vir como parâmetro
	 */
	static Date stringToDate(String dataStr) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		Date data = null;
		try {
			data = dateFormat.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

}
