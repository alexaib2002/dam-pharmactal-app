package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.uem.dam.GestorFarmacia.contract.ArticleContract;
import org.uem.dam.GestorFarmacia.contract.MedContract;
import org.uem.dam.GestorFarmacia.contract.ProviderContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.Article;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.model.Medicine;
import org.uem.dam.GestorFarmacia.model.Provider;
import org.uem.dam.GestorFarmacia.persist.DBItemMap;
import org.uem.dam.GestorFarmacia.utils.ContractUtils;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.UpdatableDataContainer;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.DataViewSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective.ItemInspectorContainer;

public class DataViewContnListener extends DefaultSubcontrol implements ChangeListener {

	// FIXME maybe this class could be abstracted to InteractableControl, or
	// someting like that

	private DBItemMap dbItemMap;

	private LinkedHashMap<String, UpdatableDataContainer> tabContainerPointer;
	private JTabbedPane tabbedPane;
	private ItemInspectorContainer itemInspectorContainer;

	public DataViewContnListener(MainController mainController,
			DataViewSubmenu dataViewSubmn) {
		super(mainController);
		this.dbItemMap = mainController.getDbItemMap();
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
