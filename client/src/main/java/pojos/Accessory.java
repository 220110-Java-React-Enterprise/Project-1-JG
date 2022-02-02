package pojos;

import annotations.Column;
import enums.SQLType;

public class Accessory extends Item {
    @Column(type = SQLType.VARCHAR)
    private String description;

    public Accessory() {
    }

    public Accessory(String name,Boolean inStock,Double price, String platform, String description) {
        super(name, inStock, price, platform);
        this.description = description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
