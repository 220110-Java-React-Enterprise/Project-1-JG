package pojos;

import annotations.Column;
import annotations.Table;
import enums.SQLType;

@Table(tableName = "items")
public class Item {
    @Column(type = SQLType.INT, primaryKey = true, nonNull = true)
    private Integer item_id;

    @Column(type = SQLType.VARCHAR)
    private String name;

    @Column(type = SQLType.BOOL)
    private Boolean inStock;

    @Column(type = SQLType.NUMERIC)
    private Double price;

    @Column(type = SQLType.VARCHAR)
    private String platform;

    @Column(type = SQLType.VARCHAR)
    private String description;


    /**
     * Empty constructor.
     */
    public Item() {
    }


    /**
     * Parameterized constructor
     * @param id            id of item
     * @param name          name of item
     * @param inStock       is the item in stock?
     * @param price         price of item
     * @param platform      what platform is the item on?
     * @param description   description of item
     */
    public Item(Integer id, String name, Boolean inStock, Double price, String platform, String description) {
        this.item_id = id;
        this.name = name;
        this.inStock = inStock;
        this.price = price;
        this.platform = platform;
        this.description = description;
    }


    /**
     * Gets id of item.
     * @return id of item
     */
    public Integer getItemId() {
        return item_id;
    }


    /**
     * Sets item_id for item.
     * @param item_id new id to set for item
     */
    public void setItemId(Integer item_id) {
        this.item_id = item_id;
    }

    
    /**
     * Gets name of item.
     * @return name of item
     */
    public String getName() {
        return name;
    }

    
    /**
     * Sets name for item.
     * @param name new name to set for item
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * Gets whether the item is in stock or not.
     * @return true/false - whether or not the item is in stock
     */
    public Boolean isInStock() {
        return inStock;
    }

    
    /**
     * Sets whether the item is in stock or not.
     * @param inStock whether or not the item should be in stock
     */
    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    
    /**
     * Gets price of item.
     * @return price of item
     */
    public Double getPrice() {
        return price;
    }

    
    /**
     * Sets price for item.
     * @param price new price to set for item
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    
    /**
     * Gets platform of item.
     * @return platform of item
     */
    public String getPlatform() {
        return platform;
    }

    
    /**
     * Sets platform for item.
     * @param platform new platform to set for item
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    
    /**
     * Gets description of item.
     * @return description of item
     */
    public String getDescription() {
        return this.description;
    }

    
    /**
     * Sets description for item.
     * @param description new description to set for item
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
