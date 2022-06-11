package org.uem.dam.GestorFarmacia.model;

public record SystemUser(
		int userId,
		String name,
		String psswd,
		boolean admin) implements DBItem, NonForeignItem {

	@Override
	public String[] getAttributes() {
		return new String[] {
				Integer.toString(userId),
				name,
				psswd,
				Boolean.toString(admin) };
	}

}
