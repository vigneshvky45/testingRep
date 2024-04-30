package stepDefinition;

public class Product {
	private String name;
    private int amountInStock;
    private double price;
    
    public Product(String name,int amountInStock, double price)
    {
    	this.name = name;
    	this.amountInStock = amountInStock;
    	this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getAmountInStock() {
        return amountInStock;
    }
    public double getPrice() {
        return price;
    }
}
