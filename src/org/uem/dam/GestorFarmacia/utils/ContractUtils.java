package org.uem.dam.GestorFarmacia.utils;

import java.util.Arrays;

public abstract class ContractUtils {

//	public static <T extends Enum<T>> String[] getAllCols() {
//		T[] resultContractVals = (T[]) T.toArray(new T[T.size()]);
//		String[] cols = new String[resultContractVals.length];
//		for (int i = 0; i < resultContractVals.length; i++) {
//			cols[i] = resultContractVals[i].toString();
//		}
//		return cols;
//	}

	public static String[] getAllCols(Class<? extends Enum<?>> E) {
		return Arrays.stream(E.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

}
