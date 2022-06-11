package org.uem.dam.GestorFarmacia.view.submenus;

import org.uem.dam.GestorFarmacia.view.BuildableView;

public abstract class DefaultMenu implements BuildableView {

	public DefaultMenu() {
		initComponents();
		initAttributes();
	}

	@Override
	abstract public void initComponents();

	@Override
	public void initAttributes() {
	}

}
