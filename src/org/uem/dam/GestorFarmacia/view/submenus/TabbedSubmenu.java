package org.uem.dam.GestorFarmacia.view.submenus;

import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.uem.dam.GestorFarmacia.contract.ArticleContract;
import org.uem.dam.GestorFarmacia.contract.MedContract;
import org.uem.dam.GestorFarmacia.contract.ProviderContract;
import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.DBItem;

import net.miginfocom.swing.MigLayout;

public class TabbedSubmenu extends DefaultSubmenu {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, TableSubmenu> tabContainerPointer;
	private JTabbedPane tabbedPane;	

	public TabbedSubmenu() {
		setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));
	}

	@Override
	public void initComponents() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "cell 0 0,grow");
	}

	@Override
	public void initAttributes() {
		tabContainerPointer = new LinkedHashMap<>();
		String[] cols;
		TableSubmenu tableSubmn;
		for (String tableName : TableContract.getUITables()) {
			cols = getTableColumns(tableName);
			tableSubmn = new TableSubmenu(cols);
			tableSubmn.setName(tableName);
			tabbedPane.add(tableSubmn);
			tabContainerPointer.put(tableName, tableSubmn);
		}

	}

	public LinkedHashMap<String, TableSubmenu> getTabContainerPointer() {
		return tabContainerPointer;
	}

	private String[] getTableColumns(String tableName) {
		String[] cols;

		Object[] columnEnumValue = null;
		switch (tableName.toUpperCase()) {
		case "ARTICLES": {
			columnEnumValue = ArticleContract.values();
			break;
		}
		case "MEDS": {
			columnEnumValue = MedContract.values();
			break;
		}
		case "PROVIDERS": {
			columnEnumValue = ProviderContract.values();
			break;
		}
		}
		cols = new String[columnEnumValue.length];
		int i = 0;
		for (Object columnValue : columnEnumValue) {
			cols[i++] = columnValue.toString();
		}

		return cols;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	@Override
	public void updateListeners(ActionListener controller) {
		tabbedPane.addChangeListener(((MainController) controller).getTabbedControl());
	}

}
