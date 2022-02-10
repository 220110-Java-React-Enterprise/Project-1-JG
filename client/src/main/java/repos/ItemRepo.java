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


    /**
     * Default constructor that initializes a Repository to handle item transactions.
     */
    public ItemRepo() {
        repo = new Repository();

        // try to connect to the new repo
        try {
            repo.startConnection(DatabaseConnector.getConnectionString());
        } catch (SQLException e) {
            FileLogger.getFileLogger().log(e);
        }
    }


    /**
     * Adds an item to the database.
     * @param item item to add
     */
    public void createItem(Item item) {
        try {
            repo.create(item);
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException e) {
            FileLogger.getFileLogger().log(e);
        }
    }


    /**
     * Retrieves an item from the database.
     * @param item
     * @return
     * TODO update this javadoc to match new readItem()
     */
    public List<Item> readItem(Item item) {
        // array list for returning all accessories
        List<Item> itemList = new ArrayList<>();

        try {
            // list of objects received from repo's generic read
            List<Object> list = repo.read(item);

            // cast each generic object into an item
            for (Object o : list) {
                itemList.add((Item) o);
            }
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException e) {
            FileLogger.getFileLogger().log(e);
        }

        return itemList;
    }


    /**
     * Updates an item in the database.
     *   The id should be consistent between the two items.
     * @param item item to update
     */
    public void updateItem(Item item) {
        try {
            repo.update(item);
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException e) {
            FileLogger.getFileLogger().log(e);
        }
    }


    /**
     * Deletes an item from the database.
     * @param item item to delete
     */
    public void deleteItem(Item item) {
        try {
            repo.delete(item);
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException e) {
            FileLogger.getFileLogger().log(e);
        }
    }
}
