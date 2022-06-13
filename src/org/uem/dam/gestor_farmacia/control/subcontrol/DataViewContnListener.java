package org.uem.dam.gestor_farmacia.control.subcontrol;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.uem.dam.gestor_farmacia.contract.ArticleContract;
import org.uem.dam.gestor_farmacia.contract.MedContract;
import org.uem.dam.gestor_farmacia.contract.ProviderContract;
import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.model.Article;
import org.uem.dam.gestor_farmacia.model.DBItem;
import org.uem.dam.gestor_farmacia.model.Medicine;
import org.uem.dam.gestor_farmacia.model.Provider;
import org.uem.dam.gestor_farmacia.utils.ContractUtils;
import org.uem.dam.gestor_farmacia.utils.SQLQueryBuilder;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.UpdatableDataContainer;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.DataViewSubmenu;
import org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.ItemInspectorContainer;

public class DataViewContnListener extends InteractableControl implements ChangeListener {

	private final LinkedHashMap<String, UpdatableDataContainer> tabContainerPointer;
	private final JTabbedPane tabbedPane;
	private final ItemInspectorContainer itemInspectorContainer;

	public DataViewContnListener(MainController mainController,
			DataViewSubmenu dataViewSubmn) {
		super(mainController);
		this.tabbedPane = dataViewSubmn.getTabbedPane();
		this.itemInspectorContainer = dataViewSubmn.getItemInspectorContainer();
		this.tabContainerPointer = dataViewSubmn.getTabContainerPointer();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		try {
			String selectedTable = tabbedPane.getSelectedComponent().getName();
			ArrayList<DBItem> queryResult = null;
			String[] cols;
			// FIXME make method on ContractUtils to get class for table, so code can be
			// optimized
			switch (selectedTable.toUpperCase()) {
			case "ARTICLES": {
				cols = ContractUtils.getAllCols(ArticleContract.class);
				queryResult = persistence.executeSelect((con, pstmt) -> {
					String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
					pstmt = con.prepareStatement(query);
					return pstmt;
				}, Article.class, cols.length);
				break;
			}
			case "MEDS": {
				cols = ContractUtils.getAllCols(MedContract.class);
				queryResult = persistence.executeSelect((con, pstmt) -> {
					String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
					pstmt = con.prepareStatement(query);
					return pstmt;
				}, Medicine.class, cols.length);
				break;
			}
			case "PROVIDERS": {
				cols = ContractUtils.getAllCols(ProviderContract.class);
				queryResult = persistence.executeSelect((con, pstmt) -> {
					String query = SQLQueryBuilder.buildSelectQuery(selectedTable, cols, null, null, true);
					pstmt = con.prepareStatement(query);
					return pstmt;
				}, Provider.class, cols.length);
				break;
			}
			}
			dbItemMap.put(selectedTable, queryResult);
			tabContainerPointer.get(selectedTable).updateContent(queryResult);
			itemInspectorContainer.changeOverlay(selectedTable.toUpperCase());

		} catch (NullPointerException npe) {
			System.err.println("Fatal error, cannot fetch data from DDBB");
			npe.printStackTrace();
		}

	}

}
