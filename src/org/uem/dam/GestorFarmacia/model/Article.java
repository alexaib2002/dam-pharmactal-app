package org.uem.dam.GestorFarmacia.model;

public record Article(
		int articleId, 
		int providerId, 
		String name, 
		float price, 
		int stock
		) implements DBItem {

	@Override
	public String[] getAttributes() {
		return new String[] { 
				Integer.toString(articleId), 
				Integer.toString(providerId),
				name,
				Float.toString(price),
				Integer.toString(stock)
		};
	}

}
