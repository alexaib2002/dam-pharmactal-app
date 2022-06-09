package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.uem.dam.GestorFarmacia.contract.ArticleContract;
import org.uem.dam.GestorFarmacia.contract.MedContract;
import org.uem.dam.GestorFarmacia.contract.ProviderContract;
import org.uem.dam.GestorFarmacia.contract.TableContract;
import org.uem.dam.GestorFarmacia.contract.UsersContract;
import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.Article;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.persist.DBPersistence;
import org.uem.dam.GestorFarmacia.utils.SQLQueryBuilder;
import org.uem.dam.GestorFarmacia.view.submenus.TabbedSubmenu;

// FIXME reminder, give this class a look, seems there are lots of things that could be improved
public class TabbedSubmnControl extends DefaultSubcontrol implements ChangeListener {

	private TabbedSubmenu tabbedSubmn;
	private DBPersistence persistence;
	private JTabbedPane tabbedPane;

	public TabbedSubmnControl(MainController mainController, TabbedSubmenu tabbedSubmn) {
		super(mainController);
		this.tabbedSubmn = tabbedSubmn;
		this.tabbedPane = tabbedSubmn.getTabbedPane();
		this.persistence = mainController.getDbPersistence();
	}

	@Override
	public void parseAction(String action) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		try {
			String selectedTable = tabbedPane.getSelectedComponent().getName();
			ArrayList<DBItem> queryResult = null;
			// FIXME check if code can be optimized
			switch (selectedTable.toUpperCase()) {
			case "ARTICLES": {				
				String[] cols = ArticleContract.getAllCols();
				queryResult = persistence.executeSelectArticles((con, pstmt) -> {
					String query = SQLQueryBuilder.buildSelectQuery(
							selectedTable, 
							cols,
							null, null, true);
					pstmt = con.prepareStatement(query);
					return pstmt;
				}, cols.length);
				break;
			}
			case "MEDS": {
				String[] cols = MedContract.getAllCols();
				queryResult = persistence.executeSelectMeds((con, pstmt) -> {
					String query = SQLQueryBuilder.buildSelectQuery(
							selectedTable, 
							cols,
							null, null, true);
					pstmt = con.prepareStatement(query);
					return pstmt;
				}, cols.length);
				break;
			}
			case "PROVIDERS": {
				String[] cols = ProviderContract.getAllCols();
				queryResult = persistence.executeSelectProviders((con, pstmt) -> {
					String query = SQLQueryBuilder.buildSelectQuery(
							selectedTable, 
							cols,
							null, null, true);
					pstmt = con.prepareStatement(query);
					return pstmt;
				}, cols.length);
				break;
			}
			}
			tabbedSubmn.getTabContainerPointer().get(selectedTable).updateTable(queryResult);
		} catch (NullPointerException npe) {
			System.err.println("Fatal error, cannot fetch data from DDBB");
			npe.printStackTrace();
		}		
	}

}
