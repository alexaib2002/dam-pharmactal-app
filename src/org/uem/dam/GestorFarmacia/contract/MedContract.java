package org.uem.dam.GestorFarmacia.contract;

public enum MedContract {
	AID, 
	MID, 
	MASS, 
	UNIT, 
	REQUIRES_PRESCRIPTION;
	
	public static String[] getAllCols() {
		MedContract[] resultContractVals = MedContract.values();
		String[] cols = new String[resultContractVals.length];
		for (int i = 0; i < resultContractVals.length; i++) {
			cols[i] = resultContractVals[i].toString();
		}
		return cols;
	}
}
