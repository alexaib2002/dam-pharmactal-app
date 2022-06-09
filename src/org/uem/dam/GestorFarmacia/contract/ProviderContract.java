package org.uem.dam.GestorFarmacia.contract;

public enum ProviderContract {
	PID, 
	NAME, 
	PHONE, 
	ADDRESS;
	
	public static String[] getAllCols() {
		ProviderContract[] resultContractVals = ProviderContract.values();
		String[] cols = new String[resultContractVals.length];
		for (int i = 0; i < resultContractVals.length; i++) {
			cols[i] = resultContractVals[i].toString();
		}
		return cols;
	}
}
