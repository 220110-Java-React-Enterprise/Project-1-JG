package utils;

public class GlobalStore {
    private static DataObject obj;

    public static DataObject getDataObject() {
		return obj;
	}

    public static void setDataObject(DataObject newObj) {
		obj = newObj;
	}

}
