package repos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConnectionException;
import exceptions.MalformedTableException;
import pojos.Item;
import utils.DatabaseConnector;
import utils.FileLogger;

public class ItemRepo {
    private Repository repo;


    public ItemRepo() {
        repo = new Repository();

        // try to connect to the new repo
        try {
            repo.startConnection(DatabaseConnector.getConnectionString());
        } catch (SQLException e) {
            FileLogger.getFileLogger().log(e);
        }
    }


    public void createItem(Item item) {
        try {
            repo.create(item);
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException e) {
            FileLogger.getFileLogger().log(e);
        }
    }


    public Item readItem(Item item) {
        // array list for returning all accessories
        List<Item> itemList = new ArrayList<>();
        Item list=null;
        try {
            // list of objects received from repo's generic read
            list = (Item)repo.read(item);

            // cast each generic object into an item
           // for (Object o : list) {
             //   itemList.add((Item) o);
           // }
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException | NoSuchMethodException e) {
            FileLogger.getFileLogger().log(e);
        }

        return list;
    }


    public void updateItem(Item item) {
        try {
            repo.update(item);
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException e) {
            FileLogger.getFileLogger().log(e);
        }
    }


    public void deleteItem(Item item) {
        try {
            repo.delete(item);
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException e) {
            FileLogger.getFileLogger().log(e);
        }
    }
}
