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
import org.uem.dam.GestorFarmacia.utils.ContractUtils;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;

import net.miginfocom.swing.MigLayout;

public class DataViewSubmenu extends DefaultSubmenu {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, TableSubmenu> tabContainerPointer;
	private JTabbedPane tabbedPane;
	JSplitPane splitPane;

	private ChangeListener updateManager;

	public DataViewSubmenu() {
		setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));
	}

	@Override
	public void initComponents() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		ItemViewContainer itemView = new ItemViewContainer();

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
		for (String tableName : ContractUtils.getAllCols(TableContract.class)) {
			if (tableName.equals(TableContract.USERS.toString())) {
				continue;
			}
			cols = ContractUtils.getAllCols(TableContract.getTableFromString(tableName));
			tableSubmn = new TableSubmenu(cols);
			tableSubmn.setName(tableName);
			tabbedPane.add(tableSubmn);
			tabContainerPointer.put(tableName, tableSubmn);
		}

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
				String[] cols;
				// FIXME check if code can be optimized
				switch (selectedTable.toUpperCase()) {
				case "ARTICLES": {
					cols = ContractUtils.getAllCols(ArticleContract.class);
					queryResult = persistence.executeSelectArticles((con, pstmt) -> {
						String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
						pstmt = con.prepareStatement(query);
						return pstmt;
					}, cols.length);
					break;
				}
				case "MEDS": {
					cols = ContractUtils.getAllCols(MedContract.class);
					queryResult = persistence.executeSelectMeds((con, pstmt) -> {
						String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
						pstmt = con.prepareStatement(query);
						return pstmt;
					}, cols.length);
					break;
				}
				case "PROVIDERS": {
					cols = ContractUtils.getAllCols(ProviderContract.class);
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
