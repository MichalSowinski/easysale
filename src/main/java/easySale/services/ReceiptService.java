package easySale.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import easySale.entities.Product;
import easySale.entities.Receipt;
import easySale.util.ReceiptNameGenerator;

public class ReceiptService {
	Receipt receipt = new Receipt();
	ReceiptNameGenerator receiptNameGenerator = new ReceiptNameGenerator();
	Date date = new Date();
	java.sql.Timestamp timestamp = new java.sql.Timestamp(new Date().getTime());
	ProductService productService;
	private EntityManager entityManager;
	

	public ReceiptService(EntityManager entityManager) {
		this.entityManager = entityManager;
		productService = new ProductService(entityManager);
	}

	public Receipt createReceipt(List<Product> listOfProducts) {
		receipt.setListOfProducts(listOfProducts);
		receipt.setName(receiptNameGenerator.createNameOfReceipt());
		receipt.setUtilTimeStamp(timestamp);
		receipt.setValue(productService.getPricesFromProduct(listOfProducts));

		entityManager.getTransaction().begin();
		entityManager.persist(receipt);
		entityManager.getTransaction().commit();

		return receipt;

	}

	public void exportReceiptsToFile(List<Receipt> listOfReceipts) {

		File file = new File("C:\\Users\\Micha³\\Desktop\\receipts.txt");
		BufferedWriter bufferedWriter = null;
		try {
//			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < listOfReceipts.size(); i++) {
				bufferedWriter.write(listOfReceipts.get(i).toString());
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			System.err.println("Cos poszlo nie tak"); // komunikat w konsoli na
														// czerwono
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public long countReceipts() {
		long receiptNumber = 0;

		entityManager.getTransaction().begin();
		TypedQuery<Long> query = entityManager.createQuery("Select count(*) from Receipt", Long.class);

		try {
			receiptNumber = query.getSingleResult();

			entityManager.getTransaction().commit();

		} catch (NoResultException e) {
			JOptionPane.showMessageDialog(null, "Wyst¹pi³ b³¹d  ");
		}
		return receiptNumber;

	}
}
