package org.uem.dam.GestorFarmacia.view;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.uem.dam.GestorFarmacia.model.ComponentView;

public class MainView extends JFrame implements ComponentView {
	
	public MainView() {
		initComponents();
	}
	
	@Override
	public void initComponents() {
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane, "cell 0 0,grow");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMn = new JMenu("File");
		menuBar.add(fileMn);
		
		JMenuItem exitMntm = new JMenuItem("Exit");
		fileMn.add(exitMntm);
	}

}
