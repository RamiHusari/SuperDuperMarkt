package CSVReader;
	import java.io.FileReader;
	import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
	import java.util.List;
	import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import Products.Cheese;
import Products.Product;
import Products.Wine;
import ProductsListRepository.IProductsRepository;

public class CSVFileReader implements IProductsRepository {
	        private String csvFile = "products.csv";
	        
	        ArrayList<Product> productsList = new ArrayList<>();
	        
	        public ArrayList<Product> FileReader (String csvFile) throws CsvValidationException, NumberFormatException{
	        	
	        	try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
	        		String[] line;
	        		
	        		// Zeilenweise .csv-Datei lesen
	        		while ((line = reader.readNext()) != null) {
	        			// Produktinformationen aus den Spalten extrahieren
	        			String name = line[0];
	        			int quality = Integer.parseInt(line[1]);
	        			LocalDate expiryDate = LocalDate.parse(line[2]);
	        			double price = Double.parseDouble(line[3]);
	        			LocalDate deliveryDay = LocalDate.parse(line[4]);
	        			
	        			// Produktobjekt erstellen und zur Liste hinzufügen
	        			Product product = new Product(name, quality, expiryDate, price,deliveryDay);
	        			productsList.add(product);
	        		}
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
				return productsList;
	        	
	        }

			@Override
			public void PrintProducts() {
				try {
					productsList = FileReader(csvFile);
				} catch (CsvValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Product product : productsList) {
					System.out.println(product.getName() +"\t\t"+product.getPrice());
					}				
			}

			@Override
			public void PrintAllProducts() {
				try {
					productsList = FileReader(csvFile);
				} catch (CsvValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				try {
					productsList = FileReader(csvFile);
				} catch (CsvValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return productsList;
				
			}

			@Override
			public void PrintAllProducts(LocalDate day) {
				// TODO Auto-generated method stub
				
			}}
	 
	        		



