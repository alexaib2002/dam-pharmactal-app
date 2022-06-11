package org.uem.dam.GestorFarmacia.utils;

import java.util.Arrays;

public abstract class ContractUtils {

	public static String[] getAllCols(Class<? extends Enum<?>> E) {
		return Arrays.stream(E.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

}
