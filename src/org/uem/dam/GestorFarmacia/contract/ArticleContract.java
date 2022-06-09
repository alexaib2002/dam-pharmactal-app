package org.uem.dam.GestorFarmacia.contract;

public enum ArticleContract {
	AID, 
	PID, 
	NAME, 
	PRICE, 
	STOCK;
	
	public static String[] getAllCols() {
		ArticleContract[] resultContractVals = ArticleContract.values();
		String[] cols = new String[resultContractVals.length];
		for (int i = 0; i < resultContractVals.length; i++) {
			cols[i] = resultContractVals[i].toString();
		}
		return cols;
	}
}
