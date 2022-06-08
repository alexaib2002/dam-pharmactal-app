package org.uem.dam.GestorFarmacia.model;

public record Medicine(
		int articleId,
		int medId,
		int mass,
		String unit,
		boolean requiresPresc
		) implements DBItem {
	
	@Override
	public String[] getAttributes() {
		return new String[] { 
				Integer.toString(articleId), 
				Integer.toString(medId),
				Integer.toString(mass),
				unit,
				Boolean.toString(requiresPresc)
		};
	}

}
