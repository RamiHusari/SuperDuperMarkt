package ProductsFacade;


import ProductsListRepository.IProductsRepository;

public interface IProductFacade {

	public void Start(IProductsRepository iProductsRepository);
	
	public void SevenDaysResult(IProductsRepository iProductsRepository);
}
