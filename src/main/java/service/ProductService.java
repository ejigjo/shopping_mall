package service;

import java.util.List;

import model.Product;

public interface ProductService {
	public List<Product> getProducts();

	boolean updateProduct(int id,int quantity);

	Product getProduct(int id);

	List<Product> selectProductByName(String name);

}
