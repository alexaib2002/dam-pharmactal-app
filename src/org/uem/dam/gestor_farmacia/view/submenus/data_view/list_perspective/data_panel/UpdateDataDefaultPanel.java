package org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.uem.dam.gestor_farmacia.control.subcontrol.UpdateItemPanelControl;
import org.uem.dam.gestor_farmacia.model.DBItem;
import org.uem.dam.gestor_farmacia.view.DefaultComponent;
import org.uem.dam.gestor_farmacia.view.InteractableView;
import org.uem.dam.gestor_farmacia.view.submenus.FetchableSubmenu;

import net.miginfocom.swing.MigLayout;

public abstract class UpdateDataDefaultPanel extends DefaultComponent
		implements FetchableSubmenu<DBItem>, InteractableView<UpdateItemPanelControl<?>> {

	public static final String NAME_BTN_UPDATE = "UpdateButton";
	public static final String NAME_BTN_REMOVE = "RemoveButton";

	private static final long serialVersionUID = 1L;

	protected JButton updateBtn;
	protected JButton removeBtn;
	protected JPanel editPanel;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow,right][grow]", "[grow][]"));
		editPanel = new JPanel();
		this.add(editPanel, "cell 0 0 2 1,grow");

		// FIXME this shouldn't be added on nested panels, fix separating delete button
		// to RootDataPanel Class

		Insets buttonMargins = new Insets(10, 10, 10, 10);

		updateBtn = new JButton();
		updateBtn.setIcon(new ImageIcon(this.getClass().getResource("/refreshIcon.png")));
		updateBtn.setMargin(buttonMargins);
		updateBtn.setName(NAME_BTN_UPDATE);
		this.add(updateBtn, "cell 0 1,alignx center");

		removeBtn = new JButton();
		removeBtn.setIcon(new ImageIcon(this.getClass().getResource("/deleteIcon.png")));
		removeBtn.setMargin(buttonMargins);
		removeBtn.setName(NAME_BTN_REMOVE);
		this.add(removeBtn, "cell 1 1,alignx center");
	}

	@Override
	public abstract DBItem getInputItem();

	@Override
	public void updateListeners(UpdateItemPanelControl<?> controller) {
		updateBtn.addActionListener(controller);
		removeBtn.addActionListener(controller);
	}

}
