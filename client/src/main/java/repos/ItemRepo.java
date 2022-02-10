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


    public Item[] readAllItems(Item item) {
        List<String> itemList = new ArrayList<>();
        ArrayList<Item> myItemsArr = new ArrayList<>();
        int counter = 0;//used for counting when to cut off each item
        Item itm = new Item();//the tempory item that will be fed into the myitems array
        try {
            itemList = repo.read(item);//runs the read method in the orm
            Integer tempId=null;//temporary values for extracing our item data
            String tempName=null;
            Boolean tempInStock=null;
            Double tempPrice=null;
            String tempPlatform=null;
            String tempDescription=null;
            for(int i=0;i<itemList.size();i++){
                    switch(counter){//switch statement determines what kind of value we're getting from the .read method
                        case 0:
                            tempId = Integer.valueOf(itemList.get(i));//id
                            counter++;
                            break;
                        case 1:
                            tempName = itemList.get(i).toString();//name
                            counter++;
                            break;
                        case 2:
                            tempInStock = Boolean.valueOf(itemList.get(i).toString());//in stock
                            counter++;

                            break;
                        case 3:
                            tempPrice = Double.valueOf(itemList.get(i).toString());//price
                            counter++;
                            break;
                        case 4:
                            tempPlatform = itemList.get(i).toString();
                            itm.setPlatform(itemList.get(i).toString());//platform
                            myItemsArr.add(new Item(tempId,tempName,tempInStock,tempPrice,tempPlatform,tempDescription));
                            counter=0;
                            break;
                        default:
                            break;
                    }
                }
        } catch (SQLException | ConnectionException | MalformedTableException | IllegalAccessException | NoSuchMethodException e) {
            FileLogger.getFileLogger().log(e);
        }
        Item[] myItems = new Item[myItemsArr.size()];//user defined arraysize, the rest will be filled with null values
        for(int i=0;i< myItemsArr.size();i++){
            myItems[i] = myItemsArr.get(i);
        }
        return myItems;//this now returns an array of Item objects. catch is that it's a user defined number of spots, so if we can't fill the array it shows null values
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
