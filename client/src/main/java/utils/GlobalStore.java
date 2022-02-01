package utils;

import pojos.Item;

public class GlobalStore {
    private static DataObject obj;
	private static Item itm;

	public static Item getItm() {
		return itm;
	}

	public static void setItm(Item itm) {
		GlobalStore.itm = itm;
	}

	public static DataObject getDataObject() {
		return obj;
	}

    public static void setDataObject(DataObject newObj) {
		obj = newObj;
	}

}
