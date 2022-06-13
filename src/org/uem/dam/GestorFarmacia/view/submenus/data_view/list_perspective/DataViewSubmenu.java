package org.uem.dam.GestorFarmacia.view.submenus.data_view.list_perspective;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.control.subcontrol.DataViewContnListener;
import org.uem.dam.GestorFarmacia.control.subcontrol.ItemListContnListener;
import org.uem.dam.GestorFarmacia.utils.ContractUtils;
import org.uem.dam.GestorFarmacia.view.submenus.DefaultInteractableSubmenu;
import org.uem.dam.GestorFarmacia.view.submenus.data_view.UpdatableDataContainer;

import net.miginfocom.swing.MigLayout;

public class DataViewSubmenu extends DefaultInteractableSubmenu<MainController> {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, UpdatableDataContainer> tabContainerPointer;
	private JTabbedPane tabbedPane;
	private ItemInspectorContainer itemInspectorContainer;
	private JSplitPane splitPane;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateListeners(MainController controller) {
		ChangeListener updateManager = new DataViewContnListener(controller, this);
		tabbedPane.addChangeListener(updateManager);
		// FIXME implement #17 will fix need of firing
		updateManager.stateChanged(null); // fire first run so dbItemMap is loaded with data

		for (Entry<String, UpdatableDataContainer> pointerEntry : tabContainerPointer.entrySet()) {
			if (pointerEntry.getValue() instanceof ItemListContainer) {
				ItemListContainer itmListContainer = (ItemListContainer) pointerEntry.getValue();
				String tableName = pointerEntry.getKey();
				itmListContainer.updateListeners(
						new ItemListContnListener(controller, tableName, controller.getDbItemMap(),
								itmListContainer.getList(), itemInspectorContainer.getDataPanel(tableName)));
			}
		}
		updateManager.stateChanged(null); // fire another time so listeners can retrieve data
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public LinkedHashMap<String, UpdatableDataContainer> getTabContainerPointer() {
		return tabContainerPointer;
	}

	public ItemInspectorContainer getItemInspectorContainer() {
		return itemInspectorContainer;
	}

}
