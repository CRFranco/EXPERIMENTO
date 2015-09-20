package br.com.senior.research.domain;


/**
 * Interface que serve como abstração para os métodos de persistência
 * @author Cristiano Roberto Franco
 *
 * @param <T> qualquer entidade a ser persistida
 */
public interface Persistible <T>{

	
	public void save(T t);
	
	public void query(T t);
}
