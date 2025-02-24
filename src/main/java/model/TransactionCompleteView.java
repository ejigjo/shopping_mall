package model;

public class TransactionCompleteView {
	private String order_no;
	private int total_price;
	private String user_name;
	private String pro_name;
	private String brand;
	private int quantity;
	private int price;

	public TransactionCompleteView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionCompleteView(String order_no, int total_price, String user_name, String pro_name, String brand,
			int quantity, int price) {
		super();
		this.order_no = order_no;
		this.total_price = total_price;
		this.user_name = user_name;
		this.pro_name = pro_name;
		this.brand = brand;
		this.quantity = quantity;
		this.price = price;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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
