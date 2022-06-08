package org.uem.dam.GestorFarmacia.contract;

public enum UsersContract {
	UID, USER, PSSWD, PERMISSION;

	public static String[] getAllCols() {
		UsersContract[] resultContractVals = UsersContract.values();
		String[] cols = new String[resultContractVals.length - 1];
		for (int i = 0; i < resultContractVals.length - 1; i++) {
			cols[i] = resultContractVals[i].toString();
		}
		return cols;
	}

	public static String[] getAuthCols() { // cols needed for authenticating users
		String[] cols = new String[UsersContract.values().length - 1];
		cols[0] = UsersContract.USER.toString();
		cols[1] = UsersContract.PSSWD.toString();
		return cols;
	}
}
