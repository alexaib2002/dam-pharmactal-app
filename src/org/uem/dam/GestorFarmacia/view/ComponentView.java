package org.uem.dam.GestorFarmacia.view;

import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.event.ChangeListener;

public interface ComponentView {

	public void initComponents();

	public void initAttributes();

	public void updateListeners(ActionListener controller);

}
