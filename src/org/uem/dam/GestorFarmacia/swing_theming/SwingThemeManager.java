package org.uem.dam.GestorFarmacia.swing_theming;

import java.awt.Color;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class SwingThemeManager {

	public static void onApplicationStart() {
		
		// fist, setup the theme library
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			applyThemeOverrides();
		} catch (Exception exception) {
			System.err.println("LaF look&feel was not initialized");
		}
		
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
