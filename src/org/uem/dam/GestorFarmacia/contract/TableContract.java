package org.uem.dam.GestorFarmacia.contract;

public enum TableContract {
	PROVIDERS(ProviderContract.class),
	ARTICLES(ArticleContract.class),
	MEDS(MedContract.class),
	USERS(UsersContract.class);

	private Class<? extends Enum<?>> assocClass;

	private TableContract(Class<? extends Enum<?>> assocClass) {
		this.assocClass = assocClass;
	}

	public static Class<? extends Enum<?>> getTableFromString(String tableName) {
		for (TableContract table : TableContract.values()) {
			if (table.toString().equals(tableName)) {
				return table.assocClass;
			}
		}
		return null;
	}
}
