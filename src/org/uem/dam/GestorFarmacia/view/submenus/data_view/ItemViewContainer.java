package org.uem.dam.GestorFarmacia.view.submenus.data_view;

import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.view.ComponentView;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;

import net.miginfocom.swing.MigLayout;

public class ItemViewContainer extends DefaultSubmenu implements ComponentView {

	public ItemViewContainer() {
		setLayout(new MigLayout("", "[grow]", "[grow,center]"));
		this.add(new ItemDataPanel(), "growx,aligny top"); // FIXME placeholder
	}

	@Override
	public void updateListeners(ActionListener controller) {

	}

}
