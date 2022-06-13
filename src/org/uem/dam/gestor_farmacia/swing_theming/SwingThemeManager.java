package org.uem.dam.gestor_farmacia.swing_theming;

import java.awt.Window;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class SwingThemeManager {
	
	public enum LookAndFeelItem {
		LIGHT,
		DARK
	}

	public static void onApplicationStart() {
		
		// fist, setup the theme library
		switchTheme(LookAndFeelItem.LIGHT);
		applyThemeOverrides();
		
	}
	
	public static void switchTheme(LookAndFeelItem item) {
		try {
			if (item == LookAndFeelItem.LIGHT) {
				UIManager.setLookAndFeel(new FlatLightLaf());
			} else {
				UIManager.setLookAndFeel(new FlatDarculaLaf());
			}
		} catch (Exception exception) {
			System.err.println("LaF was not initialized");
		}
	}
	
	public static void updateChildWindowLAF(Window window) {
		for (Window child : window.getOwnedWindows()) {
			updateChildWindowLAF(child);
		}
		SwingUtilities.updateComponentTreeUI(window);
	}
	
	private static void applyThemeOverrides() {
		
		// add some rounded corners to elements
		UIManager.put("Button.arc", 30);
		UIManager.put("TextComponent.arc", 30);
		// rice some general stuff
		UIManager.put("Component.innerFocusWidth", 2);
		UIManager.put("Component.focusWidth", 1);
		UIManager.put("Component.arrowType", "chevron");
		// show mnemonics by default
		UIManager.put("Component.hideMnemonics", false);
		
	}	

}
