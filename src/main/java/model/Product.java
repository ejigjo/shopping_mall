package model;

public class Product {
	private int id;
	private String pro_no;
	private String name;
	private String category;
	private int price;
	private String brand;
	private int stock;
	private String image_url;
	private String description;

	public Product() {
	}

	public Product(int id, String pro_no, String name, String category, int price, String brand, int stock,
			String image_url, String description) {
		this.id = id;
		this.pro_no = pro_no;
		this.name = name;
		this.category = category;
		this.price = price;
		this.brand = brand;
		this.stock = stock;
		this.image_url = image_url;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProNo() {
		return pro_no;
	}

	public void setProNo(String pro_no) {
		this.pro_no = pro_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return image_url;
	}

	public void setImageUrl(String imageUrl) {
		this.image_url = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
