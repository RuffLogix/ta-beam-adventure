package item;

public abstract class Item {
    private int price;
    private String name;
    public Item(int price, String name){
        setName(name);
        setPrice(price);
    }
    public void use(){

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = Math.max(0,price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
