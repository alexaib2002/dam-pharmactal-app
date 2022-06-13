package org.uem.dam.gestor_farmacia.model;

public record Medicine(
		Article article,
		int medId,
		int mass,
		String unit,
		boolean requiresPresc) implements DBItem, RootItem {

	@Override
	public String[] getAttributes() {
		return new String[] {
				Integer.toString(article.articleId()),
				Integer.toString(medId),
				Integer.toString(mass),
				unit,
				Boolean.toString(requiresPresc) };
	}

	@Override
	public String name() {
		return article.name();
	}

}
