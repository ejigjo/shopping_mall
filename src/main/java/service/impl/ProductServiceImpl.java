package service.impl;

import java.util.List;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService{
	private ProductDao productDao = new ProductDaoImpl();
	@Override
	public List<Product> getProducts(){
		return productDao.getProducts();
	}
	
	@Override
	public boolean updateProduct(int id,int quantity) {
		return productDao.updateProduct(id,quantity);
	}
	
	@Override
	public Product getProduct(int id) {
		return productDao.getProduct(id);
	}
	@Override
	public List<Product> selectProductByName(String name){
		return productDao.selectProductByName(name);
	}

}
