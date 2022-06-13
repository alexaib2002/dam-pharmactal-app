package org.uem.dam.gestor_farmacia.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;

class DeleteQueryTestUnit extends SQLQueryBuilder {

	@Test
	public void nulledArgumentsBuildTest() {
		assertThrows(NullPointerException.class, () -> {
			buildDeleteQuery(null, null);
		});
	}

	@Test
	public void emptyArgumentBuildTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			buildDeleteQuery("", "");
		});
	}

}
