package org.uem.dam.gestor_farmacia.utils;

import java.util.Arrays;

public abstract class ContractUtils {

	public static String[] getAllCols(Class<? extends Enum<?>> E) {
		return Arrays.stream(E.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

}
