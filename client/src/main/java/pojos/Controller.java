package pojos;

import annotations.Column;
import annotations.Table;
import enums.SQLType;

@Table(tableName = "controllers")
public class Controller extends Item {
    @Column(type = SQLType.VARCHAR)
    private String maker;

    @Column(type = SQLType.INT)
    private Integer numberOfButtons;

    public Controller(Integer id, String name, Boolean inStock, Double price, String platform, String maker, Integer numberOfButtons) {
        super(id, name, inStock, price, platform);
        this.maker = maker;
        this.numberOfButtons = numberOfButtons;
    }

    public Controller() {
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setNumberOfButtons(int numberOfButtons) {
        this.numberOfButtons = numberOfButtons;
    }

    public String getMaker() {
        return maker;
    }

    public int getNumberOfButtons() {
        return numberOfButtons;
    }

}
