package org.uem.dam.gestor_farmacia.control.subcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.uem.dam.gestor_farmacia.control.MainController;
import org.uem.dam.gestor_farmacia.view.submenus.welcome.GreetSubmenu;

public class GreetSubcontrol extends DefaultSubcontrol implements ActionListener {

	public GreetSubcontrol(MainController mainController) {
		super(mainController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(GreetSubmenu.ACTION_CONTINUE) ) {
			mainFrame.setSubmenuView(mainFrame.getDataViewSubmn());
		}

	}

}
