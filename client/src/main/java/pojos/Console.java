package pojos;

public class Console extends Item {
    private String company;
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