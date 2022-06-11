package org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective;

import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JLabel;

import org.uem.dam.GestorFarmacia.view.submenus.DefaultInteractableSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.data_panel.ArticleDataPanel;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.data_panel.RefreshableDataPanel;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.data_panel.MedDataPanel;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.data_panel.ProvidersDataPanel;

import net.miginfocom.swing.MigLayout;

public class ItemInspectorContainer extends DefaultInteractableSubmenu<ActionListener> {

	private static final long serialVersionUID = 1L;

	private ArticleDataPanel articleDataPanel;
	private MedDataPanel medDataPanel;
	private ProvidersDataPanel providersDataPanel;

	private HashMap<String, RefreshableDataPanel<?>> panelReference;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("hidemode 3", "[grow]", "[][top][top][top]"));
		JLabel titleLbl = new JLabel("Inspector");
		add(titleLbl, "cell 0 0,alignx center");
		this.articleDataPanel = new ArticleDataPanel();
		this.add(this.articleDataPanel, "cell 0 1,growx,aligny center");
		this.medDataPanel = new MedDataPanel();
		this.add(this.medDataPanel, "cell 0 2,growx,aligny center");
		this.providersDataPanel = new ProvidersDataPanel();
		this.add(this.providersDataPanel, "cell 0 3,growx,aligny center");
	}

	@Override
	public void initAttributes() {
		panelReference = new HashMap<>();
		panelReference.put("ARTICLES", articleDataPanel);
		panelReference.put("MEDS", medDataPanel);
		panelReference.put("PROVIDERS", providersDataPanel);
	}

	@Override
	public void updateListeners(ActionListener controller) {
		// TODO
	}

	public void updateInspectorData() {
		// TODO implement functionality
	}

	public RefreshableDataPanel<?> getDataPanel(String table) {
		return panelReference.get(table);
	}

	public void changeOverlay(String tableName) {
		onOverlayUpdate();
		switch (tableName) {
		case "ARTICLES": {
			articleDataPanel.setVisible(true);
			break;
		}
		case "MEDS": {
			medDataPanel.setVisible(true);
			break;
		}
		case "PROVIDERS": {
			providersDataPanel.setVisible(true);
			break;
		}
		default: {
			System.err.println(String.format("Couldn't match table passed to ItemInspector %s", tableName));
		}
		}
		refreshLayout();
	}

	private void onOverlayUpdate() {
		articleDataPanel.setVisible(false);
		medDataPanel.setVisible(false);
		providersDataPanel.setVisible(false);
	}

	private void refreshLayout() {
		this.repaint();
		this.revalidate();
	}
}
