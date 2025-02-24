package model;

public class OrderItem {
	private int id;
	private String order_items_no;
	private int order_id;
	private int product_id;
	private int quantity;
	private int price;

	public OrderItem() {
	}

	public OrderItem(int id, String orderItemsNo, int orderId, int productId, int quantity, int price) {
		this.id = id;
		this.order_items_no = orderItemsNo;
		this.order_id = orderId;
		this.product_id = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderItemsNo() {
		return order_items_no;
	}

	public void setOrderItemsNo(String order_items_no) {
		this.order_items_no = order_items_no;
	}

	public int getOrderId() {
		return order_id;
	}

	public void setOrderId(int orderId) {
		this.order_id = orderId;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
