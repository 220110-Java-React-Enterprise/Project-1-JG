package pojos;

import annotations.Column;
import annotations.Table;
import enums.SQLType;

@Table(tableName = "games")
public class Game extends Item {
    @Column(type = SQLType.VARCHAR)
    private String genre;

    @Column(type = SQLType.VARCHAR)
    private String developer;

    @Column(type = SQLType.VARCHAR)
    private String publisher;

    @Column(type = SQLType.INT)
    private Integer year;

    public Game(String name, Boolean inStock, Double price, String platform, String genre, String developer, String publisher, Integer year) {
        super(name, inStock, price, platform);
        this.genre = genre;
        this.developer = developer;
        this.publisher = publisher;
        this.year = year;
    }

    public Game() {
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }


}
