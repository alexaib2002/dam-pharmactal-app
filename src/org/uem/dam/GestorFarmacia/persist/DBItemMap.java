package org.uem.dam.GestorFarmacia.persist;

import java.util.ArrayList;
import java.util.HashMap;

import org.uem.dam.GestorFarmacia.model.DBItem;

public class DBItemMap extends HashMap<String, ArrayList<DBItem>> {

	private static final long serialVersionUID = 5665530794814554056L;

	@Override
	public ArrayList<DBItem> get(Object key) {
		return super.get(key);
	}

	@Override
	public ArrayList<DBItem> put(String key, ArrayList<DBItem> value) {
		return super.put(key, value);
	}

}
