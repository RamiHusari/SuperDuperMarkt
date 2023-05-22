package Products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Product {
	
	private String name;
	private int quality;
	private LocalDate expirationDay;
	private final double price;
	private double dailyPrice;
	private final LocalDate deliveryDay;
	private LocalDate updateDate;
	
	public Product(String name, int quality, LocalDate expirationDay, double price,LocalDate deliveryDay, LocalDate updateDate) {
		super();
		this.name = name;
		this.quality = quality;
		this.expirationDay = expirationDay;
		this.price = price;
		this.deliveryDay = deliveryDay;
		this.updateDate = updateDate;
	}
	
	public Product(String name, int quality, LocalDate expirationDay, double price,LocalDate deliveryDay) {
		super();
		this.name = name;
		this.quality = quality;
		this.expirationDay = expirationDay;
		this.price = price;
		this.deliveryDay = deliveryDay;
		updateDate = LocalDate.now().minusDays(1);
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public LocalDate getExpirationDay() {
		return expirationDay;
	}
	public void setExpirationDay(LocalDate expirationDay) {
		this.expirationDay = expirationDay;
	}
	public double getPrice() {
		return price;
	}
		
	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public LocalDate getDeliveryDay() {
		return deliveryDay;
	}

	
	
	public static void UpdatePrice(ArrayList<Product> list) {
		
		for(Product product : list) {
			if(!(product instanceof Wine)) {
				product.setDailyPrice(product.getPrice() + (product.getQuality() * 0.10));
			}
		}
	}

	
	
	public static void updateQuality(ArrayList<Product> list) {

	for(Product product : list) {
		if(! product.getUpdateDate().equals(LocalDate.now())) {
			if(!(product instanceof Wine)){
				if((product.getUpdateDate().isBefore(LocalDate.now()))) {
					product.setQuality(product.getQuality() -1);
					product.setUpdateDate(LocalDate.now());
				}
			}
			else if(product instanceof Wine) {
				((Wine)product).UpdateQualityWine((Wine)product);
			} 
		  }
		}
	}


	public static void SevenDaysResult(ArrayList<Product> productList, LocalDate day) {
		for(Product product : productList) {
			if(product.getUpdateDate() != day) {
				if(!(product instanceof Wine)){
					if((product.getUpdateDate().isBefore(day))) {
						product.setQuality(product.getQuality() -1);
						product.setUpdateDate(day);
					}
				}
				else if(product instanceof Wine  && (ChronoUnit.DAYS.between(((Wine)product).getDeliveryDay(),day)) %10  == 0) {
					if(product.getQuality() < 50)
					{
						product.setQuality(product.getQuality() + 1);
					}
				} 
			}UpdatePrice(productList);
		}
		
	}
}
