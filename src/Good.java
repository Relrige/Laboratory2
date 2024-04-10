public class Good {
    private String name;
    private String description;
    private String producer;
    private int amount;
    private double price;

    public Good (String name, String description, String producer, int amount, double price){
        this.name = name.trim();
        this.description = description;
        this.producer = producer;
        this.amount = amount;
        this.price = price;
    }

    public String toString(){
        return name + " (" + description + "). Виробник - " + producer + ". Кількість - " + amount + " одиниць. Ціна - " + price + " грн.";
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getProducer() {
        return producer;
    }
    public int getAmount() {
        return amount;
    }
    public double getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void changeAmount(int amount) {
        this.amount += amount;
    }
    public void changePrice(double price) {
        this.price += price;
    }

}
