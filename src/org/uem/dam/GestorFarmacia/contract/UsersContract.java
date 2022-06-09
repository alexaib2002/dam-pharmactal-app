package org.uem.dam.GestorFarmacia.contract;

public enum UsersContract {
	UID, USERNAME, PSSWD, PERMISSION;

	public static String[] getAllCols() {
		UsersContract[] resultContractVals = UsersContract.values();
		String[] cols = new String[resultContractVals.length];
		for (int i = 0; i < resultContractVals.length; i++) {
			cols[i] = resultContractVals[i].toString();
		}
		return cols;
	}

	public static String[] getAuthCols() { // cols needed for authenticating users
		String[] cols = new String[2];
		cols[0] = UsersContract.USERNAME.toString();
		cols[1] = UsersContract.PSSWD.toString();
		return cols;
	}
}
