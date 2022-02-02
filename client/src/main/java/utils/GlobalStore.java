package utils;

import pojos.*;

public class GlobalStore {
    private static DataObject obj;
	private static Accessory accessory;
	private static Console console;
	private static Controller controller;
	private static Game game;

	public static Console getConsole() {
		return console;
	}

	public static void setConsole(Console console) {
		GlobalStore.console = console;
	}

	public static Controller getController() {
		return controller;
	}

	public static void setController(Controller controller) {
		GlobalStore.controller = controller;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		GlobalStore.game = game;
	}

	public static Accessory getAccessory() {
		return accessory;
	}

	public static void setAccessory(Accessory accessory) {
		GlobalStore.accessory = accessory;
	}

	public static DataObject getDataObject() {
		return obj;
	}

    public static void setDataObject(DataObject newObj) {
		obj = newObj;
	}

}
