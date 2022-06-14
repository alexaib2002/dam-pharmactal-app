package org.uem.dam.gestor_farmacia.view.submenus.data_view.list_perspective.data_panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.uem.dam.gestor_farmacia.control.subcontrol.UpdateItemPanelControl;
import org.uem.dam.gestor_farmacia.model.DBItem;
import org.uem.dam.gestor_farmacia.view.DefaultComponent;
import org.uem.dam.gestor_farmacia.view.InteractableView;
import org.uem.dam.gestor_farmacia.view.submenus.FetchableSubmenu;

import net.miginfocom.swing.MigLayout;

public abstract class UpdateDataDefaultPanel extends DefaultComponent
		implements FetchableSubmenu<DBItem>, InteractableView<UpdateItemPanelControl> {

	public static final String ACTION_UPDATE = "NOACTION";

	private static final long serialVersionUID = 1L;

	protected JButton updateBtn;
	protected JPanel editPanel;

	@Override
	public void initComponents() {
		setLayout(new MigLayout("", "[grow,right]", "[grow][]"));
		editPanel = new JPanel();
		this.add(editPanel, "cell 0 0,grow");
		updateBtn = new JButton();
		this.add(updateBtn, "cell 0 1,growx");
	}

	@Override
	public abstract DBItem getInputItem();

	@Override
	public void updateListeners(UpdateItemPanelControl controller) {
		updateBtn.addActionListener(controller);
	}

}
