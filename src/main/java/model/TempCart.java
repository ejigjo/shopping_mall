package model;

public class TempCart {
	private int productId;
	private int quantity;
	private int price;
	private int cartId;

	public TempCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TempCart(int productId, int quantity, int price ,int cartId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

}
