package org.uem.dam.gestor_farmacia.model;

public record Article(
		int articleId,
		int providerId,
		String name,
		double price,
		int stock) implements DBItem, RootItem {

	@Override
	public String[] getAttributes() {
		return new String[] {
				Integer.toString(articleId),
				Integer.toString(providerId),
				name,
				Double.toString(price),
				Integer.toString(stock) };
	}

}
