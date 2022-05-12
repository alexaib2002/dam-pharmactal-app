package org.uem.dam.GestorFarmacia.model;

public record SystemUser(
		int id,
		String name,
		String psswd,
		String permission
	) {

}
