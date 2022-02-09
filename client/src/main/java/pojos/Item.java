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

    public Item() {
    }

    public Item(Integer id, String name, Boolean inStock, Double price, String platform) {
        this.item_id = id;
        this.name = name;
        this.inStock = inStock;
        this.price = price;
        this.platform = platform;
    }

    public Integer getId() {
        return item_id;
    }

    public void setId(Integer id) {
        this.item_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
