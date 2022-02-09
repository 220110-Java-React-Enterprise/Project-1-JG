package pojos;

import annotations.Column;
import annotations.Table;
import enums.SQLType;

@Table(tableName = "accessories")
public class Accessory extends Item {
    @Column(type = SQLType.VARCHAR)
    private String description;

    public Accessory() {
    }

    public Accessory(Integer id, String name,Boolean inStock,Double price, String platform, String description) {
        super(id, name, inStock, price, platform);
        this.description = description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
