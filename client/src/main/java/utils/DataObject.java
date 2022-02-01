package utils;

public class DataObject {
    private int num;
    private String str;
    private boolean bool;
   private int gameCount;


    public DataObject() {

    }


    public int getNum() {
        return this.num;
    }


    public void setNum(int num) {
        this.num = num;
    }


    public String getStr() {
        return this.str;
    }


    public void setStr(String str) {
        this.str = str;
    }


    public boolean isBool() {
        return this.bool;
    }

    
    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }
}
