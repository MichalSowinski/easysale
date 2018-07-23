package easySale.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import easySale.entities.Product;

public class ReceiptNameGenerator {

	Product product = new Product();
	Calendar calendar = Calendar.getInstance();
	Date date = new Date();

	public String createNameOfReceipt() {
		long idOfProduct = product.getId();
		String idOfProductAsString = String.valueOf(idOfProduct);

		date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY/HH:MM:ss");
		String newDateFormat = simpleDateFormat.format(date);

		String receiptName = idOfProductAsString + newDateFormat;
		return receiptName;

	}

}
