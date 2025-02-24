package model;

import java.time.LocalDateTime;

public class Order {
	private int id;
	private String order_no;
	private int user_id;
	private int total_price;
	private String status;
	private LocalDateTime created_at;

	public Order() {
		this.created_at = LocalDateTime.now();
	}

	public Order(int id, String orderNo, int userId, int totalPrice, String status, LocalDateTime createdAt) {
		this.id = id;
		this.order_no = orderNo;
		this.user_id = userId;
		this.total_price = totalPrice;
		this.status = status;
		this.created_at = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return order_no;
	}

	public void setOrderNo(String orderNo) {
		this.order_no = orderNo;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int userId) {
		this.user_id = userId;
	}

	public int getTotalPrice() {
		return total_price;
	}

	public void setTotalPrice(int totalPrice) {
		this.total_price = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.created_at = createdAt;
	}
}