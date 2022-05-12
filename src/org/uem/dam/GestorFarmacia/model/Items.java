package org.uem.dam.GestorFarmacia.model;

public record Items(
		int id,
		int id_prov,
		String name,
		double price,
		int stock
		) {

}
