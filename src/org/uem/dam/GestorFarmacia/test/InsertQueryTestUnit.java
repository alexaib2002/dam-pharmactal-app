package org.uem.dam.GestorFarmacia.test;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;

class InsertQueryTestUnit extends SQLQueryBuilder {

	@Test
	void emptyColumnCreationTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			buildInsertQuery("EMPTY_TABLE", new String[] {});
		});
	}

	@Test
	void bothArgumentsEmptiedCreationTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			buildInsertQuery("", new String[] {});
		});
	}

	@Test
	void nulledArgumentsCreationTest() {
		assertThrows(NullPointerException.class, () -> {
			buildInsertQuery(null, null);
		});
	}

}
