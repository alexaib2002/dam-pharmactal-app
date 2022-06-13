package org.uem.dam.gestor_farmacia.model;

public record Provider(
		int providerId,
		String name,
		String phone,
		String address) implements DBItem, RootItem {

	@Override
	public String[] getAttributes() {
		return new String[] {
				Integer.toString(providerId),
				name,
				phone,
				address, };
	}

}
