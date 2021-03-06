package org.uem.dam.gestor_farmacia.model;

public record SystemUser(
		int userId,
		String name,
		String psswd,
		boolean admin) implements DBItem, RootItem {

	@Override
	public String[] getAttributes() {
		return new String[] {
				Integer.toString(userId),
				name,
				psswd,
				Boolean.toString(admin) };
	}

}
