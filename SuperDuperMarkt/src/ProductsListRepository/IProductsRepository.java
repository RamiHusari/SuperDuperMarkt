package ProductsListRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import Products.Product;

public interface IProductsRepository {
	
	public void PrintProducts();

	public void PrintAllProducts();
	
	public ArrayList<Product> getAllProducts();

	public void PrintAllProducts(LocalDate day);

}
