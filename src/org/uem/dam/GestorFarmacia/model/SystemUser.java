package org.uem.dam.GestorFarmacia.model;

public record SystemUser(
		int userId, 
		String userName, 
		String psswd, 
		boolean admin
		) implements DBItem {
	
	@Override
	public String[] getAttributes() {
		return new String[] { 
				Integer.toString(userId), 
				userName,
				psswd,
				Boolean.toString(admin)
		};
	}

}
