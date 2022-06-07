package org.uem.dam.GestorFarmacia.control.subcontrol;

public abstract class DefaultSubcontrol {

	public void parseAction(String action) {
		System.err.println(String.format("%s does not implement method parseAction", this.getClass().getSimpleName()));
	}

}
