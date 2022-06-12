package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.view.submenus.insertion.InsertArticlePanel;

public class InsertArticleControl extends DefaultSubcontrol implements ActionListener {

	private final InsertArticlePanel articleInsertPanel;

	public InsertArticleControl(MainController mainController,
			InsertArticlePanel articleInsertPanel) {
		super(mainController);
		this.articleInsertPanel = articleInsertPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO test functionality
		String action = e.getActionCommand();
		switch (action) {
		case InsertArticlePanel.ACTION_ADD: {
			DBItem item = articleInsertPanel.getInputItem();
			System.out.println(item);
		}
		case InsertArticlePanel.ACTION_CLEAR: {
			articleInsertPanel.clearFields();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

}
