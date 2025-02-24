package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	
	public List<Product> getProducts();

	boolean updateProduct(int id,int quantity);

	Product getProduct(int id);

	List<Product> selectProductByName(String name);

}
