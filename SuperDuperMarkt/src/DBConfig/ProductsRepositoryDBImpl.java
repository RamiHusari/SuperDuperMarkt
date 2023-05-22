package DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Products.Cheese;
import Products.Product;
import Products.Wine;
import ProductsListRepository.IProductsRepository;

public class ProductsRepositoryDBImpl implements IProductsRepository{
	
	private String DB_URL ="jdbc:sqlserver://localhost:1433;databaseName=database_name;authentication=ActiveDirectoryIntegrated";
	private  ArrayList<Product> productsList = new ArrayList<Product>();


	@Override
	public void PrintProducts() {
		productsList = getAllProducts();
		System.out.println("Sortiment"+"\t\t"+"Preis");
		for(Product product : productsList) {
			System.out.println(product.getName() +"\t\t"+product.getPrice());
			}
		
	}

	@Override
	public void PrintAllProducts() {
		productsList = getAllProducts();
		for(Product product : productsList) {
			System.out.println(product.getName() + "\t" + product.getQuality() +"\t"+
					((!(product instanceof Wine)) ?
					String.format("%,.2f",product.getDailyPrice()) : product.getPrice()) + "\t" + 
					(product instanceof Cheese ? 
						(product.getQuality() < 30 || product.getExpirationDay().isBefore(LocalDate.now())  ? "This Product should be removed" : "-") : 
							(!(product instanceof Wine)) && product.getExpirationDay().isBefore(LocalDate.now()) ? "This Product should be removed" : "-" ));
			}
		
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
	        
	        try (Connection conn = DriverManager.getConnection(DB_URL)) {
	            String query = "SELECT name, quality, expirationDay, price, deliveryDay FROM Product";
	            PreparedStatement pstmt = conn.prepareStatement(query);

	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                String name = rs.getString("name");
	                int quality = rs.getInt("quality");
	                LocalDate expirationDay = rs.getObject("expirationDay", LocalDate.class);
	                double price = rs.getDouble("price");
	                LocalDate deliveryDay = rs.getObject("deliveryDay", LocalDate.class);
	                LocalDate updateDate = rs.getObject("deliveryDay", LocalDate.class);
	                Product product = new Product(name, quality, expirationDay, price, deliveryDay,updateDate);
	                products.add(product);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return products;
	}

	@Override
	public void PrintAllProducts(LocalDate day) {
		// TODO Auto-generated method stub
		
	}

}
