package SuperDuperMarktApp;

import ProductsFacade.ProductFacade;
import ProductsListRepository.IProductsRepository;
import ProductsListRepository.ProductsRepositoryImpl;

public class SuperDuperMarktApp {

	public static void main(String[] args) {
		IProductsRepository p = new ProductsRepositoryImpl();
		ProductFacade pFacade = new ProductFacade();
		pFacade.Start(p);
	}
}
