package org.uem.dam.GestorFarmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.uem.dam.GestorFarmacia.control.MainController;
import org.uem.dam.GestorFarmacia.model.DBItem;
import org.uem.dam.GestorFarmacia.view.submenus.insertion.ArticleInsertPanel;

public class InsertArticleControl extends DefaultSubcontrol implements ActionListener {

	private final ArticleInsertPanel articleInsertPanel;

	public InsertArticleControl(MainController mainController,
			ArticleInsertPanel articleInsertPanel) {
		super(mainController);
		this.articleInsertPanel = articleInsertPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO test functionality
		String action = e.getActionCommand();
		switch (action) {
		case ArticleInsertPanel.ACTION_ADD: {
			DBItem item = articleInsertPanel.getInputItem();
			System.out.println(item);
		}
		case ArticleInsertPanel.ACTION_CLEAR: {
			articleInsertPanel.clearFields();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

}
