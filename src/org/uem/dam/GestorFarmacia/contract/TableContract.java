package org.uem.dam.GestorFarmacia.contract;

public enum TableContract {
	PROVIDERS, ARTICLES, MEDS, USERS;

	public static String[] getUITables() {
		TableContract[] selfValues = TableContract.values();
		String[] tables = new String[selfValues.length - 1];
		int i = 0;
		for (TableContract tableContractValue : selfValues) {
			if (!tableContractValue.equals(TableContract.USERS)) {
				tables[i++] = tableContractValue.toString();
			}
		}
		return tables;
	}
}
