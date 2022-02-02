package pojos;

public abstract class Item {
    private String name;
    private Boolean inStock;
    private Double price;
    private String platform;

    public Item() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Item(String name, Boolean inStock, Double price, String platform) {
        this.name = name;
        this.inStock = inStock;
        this.price = price;
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
