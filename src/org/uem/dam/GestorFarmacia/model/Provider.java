package org.uem.dam.GestorFarmacia.model;

public record Provider(
		int providerId,
		String name,
		String tel,
		String address
		) implements DBItem {
	
	@Override
	public String[] getAttributes() {
		return new String[] { 
				Integer.toString(providerId),
				name,
				tel,
				address,
		};
	}

}
