package org.uem.dam.GestorFarmacia.view.submenus;

import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.view.ComponentView;

import net.miginfocom.swing.MigLayout;

public class ItemViewSubmenu extends DefaultSubmenu implements ComponentView {

	public ItemViewSubmenu() {
		setLayout(new MigLayout("", "[grow]", "[grow,center]"));
		this.add(new ItemDataPanelSubmenu(), "growx,aligny top");
	}

	@Override
	public void updateListeners(ActionListener controller) {

	}

}
