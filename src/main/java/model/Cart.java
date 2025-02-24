package model;

import java.time.LocalDateTime;

public class Cart {
	private int id;
	private String cart_no;
	private int user_id;
	private int product_id;
	private int quantity;
	private LocalDateTime created_at;

	public Cart() {
	}

	public Cart(int id, String cart_no, int user_id, int product_id, int quantity, LocalDateTime createdAt) {
		this.id = id;
		this.cart_no = cart_no;
		this.user_id = user_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.created_at = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCartNo() {
		return cart_no;
	}

	public void setCartNo(String cartNo) {
		this.cart_no = cartNo;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int userId) {
		this.user_id = userId;
	}

	public int getProductId() {
		return product_id;
	}

	public void setProductId(int productId) {
		this.product_id = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.created_at = createdAt;
	}
}
