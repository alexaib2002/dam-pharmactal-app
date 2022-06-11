package org.uem.dam.GestorFarmacia.view.submenus.data_view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

import org.uem.dam.GestorFarmacia.contract.ArticleContract;
import org.uem.dam.GestorFarmacia.contract.MedContract;
import org.uem.dam.GestorFarmacia.contract.ProviderContract;
import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.control.listener.ItemListContnListener;
import org.uem.dam.GestorFarmacia.model.Article;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.model.Medicine;
import org.uem.dam.GestorFarmacia.model.Provider;
import org.uem.dam.GestorFarmacia.persist.DBItemMap;
import org.uem.dam.GestorFarmacia.persist.DBPersistence;
import org.uem.dam.GestorFarmacia.utils.ContractUtils;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultSubmenu;

import net.miginfocom.swing.MigLayout;

public class DataViewSubmenu extends DefaultSubmenu<MainController> {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, DataContainer> tabContainerPointer;
	private JTabbedPane tabbedPane;
	private ItemInspectorContainer itemInspectorContainer;
	private JSplitPane splitPane;

	private ChangeListener updateManager;

	public DataViewSubmenu() {
		setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));
	}

	@Override
	public void initComponents() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setMinimumSize(new Dimension(200, 0));

		itemInspectorContainer = new ItemInspectorContainer();
		itemInspectorContainer.setMinimumSize(itemInspectorContainer.getMinimumSize());

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, itemInspectorContainer);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(1.0);
		add(splitPane, "growx");
	}

	@Override
	public void initAttributes() {
		tabContainerPointer = new LinkedHashMap<>();
		for (String tableName : ContractUtils.getAllCols(TableContract.class)) {
			if (tableName.equals(TableContract.USERS.toString())) {
				continue;
			}
			ItemListContainer itemListContainer = new ItemListContainer();
			itemListContainer.setName(tableName);
			tabbedPane.add(itemListContainer);
			tabContainerPointer.put(tableName, itemListContainer);
		}

	}

	@Override
	public void updateListeners(MainController controller) {
		DBPersistence persistence = controller.getDbPersistence();
		DBItemMap dbItemMap = controller.getDbItemMap();
		this.updateManager = (event) -> {
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
		};

		tabbedPane.addChangeListener(updateManager);
		updateManager.stateChanged(null); // fire first run so dbItemMap is loaded with data

		for (Entry<String, DataContainer> pointerEntry : tabContainerPointer.entrySet()) {
			if (pointerEntry.getValue() instanceof ItemListContainer) {
				ItemListContainer itmListContainer = (ItemListContainer) pointerEntry.getValue();
				String tableName = pointerEntry.getKey();
				itmListContainer.updateListeners(
						// TODO get proper submenu for passing to listener
						new ItemListContnListener(tableName, controller.getDbItemMap(), itmListContainer.getList(),
								itemInspectorContainer.getDataPanel(tableName)));
			}
		}
		updateManager.stateChanged(null); // fire another time so listeners can retrieve data
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

}
