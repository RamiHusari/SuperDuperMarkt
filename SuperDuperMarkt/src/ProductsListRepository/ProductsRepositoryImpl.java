package ProductsListRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Products.Cheese;
import Products.Product;
import Products.Wine;

public class ProductsRepositoryImpl implements IProductsRepository {
	//Repository ist ein Mechanismus zum Kapseln von Speicher
	@SuppressWarnings("deprecation")
	private  ArrayList<Product> productsList = new ArrayList<Product>() {
		{
			add(new Product("Product(1)", 20, LocalDate.now().plusDays(20), 1.99,LocalDate.now()));
			add(new Product("Product(2)", 40, LocalDate.now().plusDays(3), 4.99,LocalDate.now()));
			add(new Cheese("Cheese(1)", 32, LocalDate.now().plusDays(20), 4.99,LocalDate.now()));
			add(new Cheese("Cheese(2)", 50, LocalDate.now().plusDays(20), 4.99,LocalDate.now()));
			add(new Wine("Wine(1)  ", 20, 4.99,LocalDate.now().minusDays(20)));
			add(new Wine("Wine(2)  ", 49, 4.99,LocalDate.now().minusDays(7)));
			add(new Product("Product(2)", 40, LocalDate.now().plusDays(4), 4.99,LocalDate.now()));
			add(new Product("Product(2)", 40, LocalDate.now().plusDays(20), 4.99,LocalDate.now()));
			add(new Product("Product(2)", 40, LocalDate.now().plusDays(20), 4.99,LocalDate.now()));

		}
	};
	@Override
	public void PrintProducts() {
		System.out.println("Sortiment"+"\t\t"+"Preis");
		for(Product product : productsList) {
			System.out.println(product.getName() +"\t\t"+product.getPrice());
			}
	}

	@Override
	public void PrintAllProducts() {
		for(Product product : productsList) {
			System.out.println(product.getName() + "\t" + product.getQuality() +"\t"+
					((!(product instanceof Wine)) ?
					String.format("%,.2f",product.getDailyPrice()) : product.getPrice()) + "\t" + 
					(product instanceof Cheese ? 
						(product.getQuality() < 30 || product.getExpirationDay().isBefore(LocalDate.now())  ? "This Product should be removed" : "-") : 
							(!(product instanceof Wine)) && product.getExpirationDay().isBefore(LocalDate.now()) ? "This Product should be removed" : "-" ));
			}
	}
	
	public void PrintAllProducts(LocalDate day) {
		for(Product product : productsList) {
			System.out.println(product.getName() + "\t" + product.getQuality() +"\t"+
					((!(product instanceof Wine)) ?
					String.format("%,.2f",product.getDailyPrice()) : product.getPrice()) + "\t" + 
					(product instanceof Cheese ? 
						(product.getQuality() < 30 || product.getExpirationDay().isBefore(day)  ? "This Product should be removed" : "-") : 
							(!(product instanceof Wine)) && product.getExpirationDay().isBefore(day) ? "This Product should be removed" : "-" ));
			}
	}
	
	public ArrayList<Product> getAllProducts() {
		return productsList;
	}	
	
}
