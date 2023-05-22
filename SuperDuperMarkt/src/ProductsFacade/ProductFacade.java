package ProductsFacade;

import java.time.LocalDate;
import java.util.ArrayList;

import Products.Product;
import ProductsListRepository.IProductsRepository;

public class ProductFacade implements IProductFacade{
	ArrayList<Product> productList = new ArrayList<Product>();
	// Die Facade Pattern vereinfachte zur Nutzung eines System
	@Override
	public void Start(IProductsRepository iProductsRepository) {
		productList = iProductsRepository.getAllProducts();
		iProductsRepository.PrintProducts();
		Product.updateQuality(productList);
		Product.UpdatePrice(productList);
		System.out.println("------------------------------- \n"+LocalDate.now());
		iProductsRepository.PrintAllProducts();		 
		System.out.println("-------------------------------");
		SevenDaysResult(iProductsRepository);
		
	}

	@Override
	public void SevenDaysResult(IProductsRepository iProductsRepository) {
		for(int i = 1; i <= 7; i++) {
			LocalDate day = LocalDate.now() ;
			day = day.plusDays(i);
			System.out.println(day);
			Product.SevenDaysResult(productList, day);
			iProductsRepository.PrintAllProducts(day);		 
			 System.out.println("-------------------------------");
		}
		
	}
	
	

}
