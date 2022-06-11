package org.uem.dam.GestorFarmacia.utils;

public abstract class ErrorUtils {
	
	private static final String ERR_FATAL = "Error fatal, no se puede continuar la ejecucion del programa:";
	
	public static void onFatalErrorException(String mssg) {
		System.err.println(ERR_FATAL);
		System.err.println(mssg);		
		System.exit(-1);
	}

}
