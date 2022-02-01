package utils;

import pojos.Accessory;
import pojos.Item;

public class GlobalStore {
    private static DataObject obj;
	private static Item item;
	private static Accessory accessory;

	public static Accessory getAccessory() {
		return accessory;
	}

	public static void setAccessory(Accessory accessory) {
		GlobalStore.accessory = accessory;
	}

	public static Item getItm() {
		return item;
	}

	public static void setItm(Item itm) {
		GlobalStore.item = itm;
	}

	public static DataObject getDataObject() {
		return obj;
	}

    public static void setDataObject(DataObject newObj) {
		obj = newObj;
	}

}
