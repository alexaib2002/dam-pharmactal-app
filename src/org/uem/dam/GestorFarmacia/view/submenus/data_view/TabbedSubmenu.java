package org.uem.dam.GestorFarmacia.view.submenus.data_view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

import org.uem.dam.GestorFarmacia.contract.ArticleContract;
import org.uem.dam.GestorFarmacia.contract.MedContract;
import org.uem.dam.GestorFarmacia.contract.ProviderContract;
import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.persist.DBPersistence;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;

import net.miginfocom.swing.MigLayout;

public class TabbedSubmenu extends DefaultSubmenu {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, TableSubmenu> tabContainerPointer;
	private JTabbedPane tabbedPane;
	JSplitPane splitPane;

	private ChangeListener updateManager;

	public TabbedSubmenu() {
		setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));
	}

	@Override
	public void initComponents() {

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// add(tabbedPane, "cell 1 0");

		ItemViewContainer itemView = new ItemViewContainer();
		// add(itemView, "cell 2 0");

		tabbedPane.setMinimumSize(new Dimension(200, 0));
		itemView.setMinimumSize(new Dimension(200, 0));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, itemView);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(1.0);
		add(splitPane, "growx");
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
		DBPersistence persistence = ((MainController) controller).getDbPersistence();
		this.updateManager = (event) -> {
			try {
				String selectedTable = tabbedPane.getSelectedComponent().getName();
				ArrayList<DBItem> queryResult = null;
				// FIXME check if code can be optimized
				switch (selectedTable.toUpperCase()) {
				case "ARTICLES": {
					String[] cols = ArticleContract.getAllCols();
					queryResult = persistence.executeSelectArticles((con, pstmt) -> {
						String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
						pstmt = con.prepareStatement(query);
						return pstmt;
					}, cols.length);
					break;
				}
				case "MEDS": {
					String[] cols = MedContract.getAllCols();
					queryResult = persistence.executeSelectMeds((con, pstmt) -> {
						String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
						pstmt = con.prepareStatement(query);
						return pstmt;
					}, cols.length);
					break;
				}
				case "PROVIDERS": {
					String[] cols = ProviderContract.getAllCols();
					queryResult = persistence.executeSelectProviders((con, pstmt) -> {
						String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
						pstmt = con.prepareStatement(query);
						return pstmt;
					}, cols.length);
					break;
				}
				}
				tabContainerPointer.get(selectedTable).updateTable(queryResult);
			} catch (NullPointerException npe) {
				System.err.println("Fatal error, cannot fetch data from DDBB");
				npe.printStackTrace();
			}
		};
		tabbedPane.addChangeListener(updateManager);
		updateManager.stateChanged(null); // fire first run
	}

}
