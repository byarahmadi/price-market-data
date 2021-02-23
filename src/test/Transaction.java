package test;

public class Transaction {
	private String productId;
	private long quantity;
	private double price;
	public Transaction( long quantity, double price) {
	//	this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
	public String getProductId() {
		return productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
