package pojos;

import annotations.Column;
import enums.SQLType;

public class Console extends Item {
    @Column(type = SQLType.VARCHAR)
    private String company;
    
    @Column(type = SQLType.VARCHAR)
    private String model;

    public Console(String name, Boolean inStock, Double price, String platform, String company, String model) {
        super(name, inStock, price, platform);
        this.company = company;
        this.model = model;
    }

    public Console() {
    }


    public void setCompany(String company) {
        this.company = company;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }


}