package br.com.senior.research.utils;


/**
 * A class Chronometer é responsável por calcular o tempo gasto, em milisegundos (ms),
 * em uma operação.
 * 
 * A classe é formada por métodos estáticos de inicio e término de contagem e o tempo
 * transcorrido pode ser obtido através de outro método, também estático.
 * 
 * @author Lilian Ketlyn
 * @author Rubem Kalebe
 * @version 04.03.2015
 * <a href=https://github.com/jefferssongalvao/ParnatalOnline/blob/master/src/Chronometer.java>Chronometer</a>
 * 
 *  
*/
public final class Chronometer {

	private static long startValue;
	private static long stopValue;
	private static long timeDiff;

	public static void start() {
		startValue = System.currentTimeMillis();
		stopValue = 0;
		timeDiff = 0;
	}

	public static void stop() {
		stopValue = System.currentTimeMillis();
		timeDiff = stopValue - startValue;
	}

	public static long elapsedTimeInMiliseconds() {
		return timeDiff;
	}
	
	public static double elapsedTimeInSeconds() {
		return timeDiff/1000;
	}
}