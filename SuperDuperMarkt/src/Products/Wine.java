package Products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Wine extends Product {
	

	public Wine(String name, int quality , double price, LocalDate deliveryDay) {
		super(name, quality, null, price, deliveryDay);
		// TODO Auto-generated constructor stub
	}

	public void UpdateQualityWine(Wine wine) {
		if(wine.getQuality() < 50)
		{
			int firstQualityPoint = (int) (ChronoUnit.DAYS.between(((Wine)wine).getDeliveryDay(),LocalDate.now())) / 10 ;
			wine.setQuality(wine.getQuality() + firstQualityPoint);
			wine.setUpdateDate(LocalDate.now());
			if(wine.getQuality() > 50) {
				wine.setQuality(50);
			}
		}
	}
}