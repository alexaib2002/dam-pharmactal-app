package org.uem.dam.GestorFarmacia.view.submenus.data_view;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel.ArticleDataPanel;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel.MedDataPanel;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.data_panel.ProvidersDataPanel;

import net.miginfocom.swing.MigLayout;

public class ItemInspectorContainer extends DefaultSubmenu {

	private static final long serialVersionUID = 1L;
	private JLabel lblInspector;

	public ItemInspectorContainer() {
		setLayout(new MigLayout("", "[grow]", "[][grow,center][grow,center][grow,center]"));
		add(getLblInspector(), "cell 0 0,alignx center");
		this.add(new ArticleDataPanel(), "cell 0 1,growx,aligny center"); // FIXME placeholder
		this.add(new MedDataPanel(), "cell 0 2,growx,aligny center"); // FIXME placeholder
		this.add(new ProvidersDataPanel(), "cell 0 3,growx,aligny center"); // FIXME placeholder
	}

	@Override
	public void updateListeners(ActionListener controller) {

	}

	private JLabel getLblInspector() {
		if (lblInspector == null) {
			lblInspector = new JLabel("Inspector");
		}
		return lblInspector;
	}
}
