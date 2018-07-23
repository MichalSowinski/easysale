package easy.sell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import easySale.entities.Product;
import easySale.entities.Receipt;
import easySale.entities.Role;
import easySale.services.CashDeclarationService;
import easySale.services.ProductService;
import easySale.services.ReceiptService;

public class SellProccess {
	ProductService productService;
	Receipt receipt = new Receipt();
	private EntityManager entityManager;
	private boolean isStarted = false;
	private JTextField textField;
	private int result;
	private List<Product> listOfProducts = new ArrayList<>();
	String[] buttons = { "add product", "check current list of products", "remove list of product", "payment",
			"return", };
	ReceiptService receiptService;
	CashDeclarationService cashDeclarationService;
	private List<Receipt> currentListOfReceipts = new ArrayList<>();

	public SellProccess(CashDeclarationService cashDeclarationService, EntityManager entityManager) {
		this.cashDeclarationService = cashDeclarationService;
		this.entityManager = entityManager;
		productService = new ProductService(entityManager);
		receiptService = new ReceiptService(entityManager);
	}

	public SellProccess() {

	}

	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public List<Receipt> getCurrentListOfReceipts() {
		return currentListOfReceipts;
	}

	public int sellProccess() {

		if (!isStarted) {
			cashDeclarationService.CashDeposit();

		}
		isStarted = true;

		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter bar code:"));
		panel.add(new JLabel("\n"));
		textField = new JTextField(30);
		panel.add(textField);

		result = JOptionPane.showOptionDialog(null, panel, "EasySale", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, buttons, null);
		return result;

	}

	public void addProduct() {

		String barCode = textField.getText();
		Product product = productService.doesProductExistInBase(barCode);
		if (product != null)
			listOfProducts.add(product);

		// Date date = new Date();

	}

	public void checkCurrentListOfProducts() {
		if (listOfProducts.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lista produktów jest pusta!");
		} else {
			JOptionPane.showMessageDialog(null, "Aktualna lista produktów" + listOfProducts);
		}

	}

	public void removeListOfProducts() {
		listOfProducts.clear();
		JOptionPane.showMessageDialog(null, "Lista produktów usuniêta!");
	}

	public void giveDiscount() {
		JOptionPane.showMessageDialog(null, "zni¿ka");
	}

	public void payment() {
		String[] chooseMethodsOfPayment = new String[3];
		chooseMethodsOfPayment[0] = "Credit Card";
		chooseMethodsOfPayment[1] = "Cash";
		chooseMethodsOfPayment[2] = "Mixed Transaction";

		Object questionAboutMethodOfPayment = JOptionPane.showInputDialog(null, "Choose Payment Method:",
				"Payment Methods", JOptionPane.QUESTION_MESSAGE, null, chooseMethodsOfPayment, null);

		if (questionAboutMethodOfPayment == chooseMethodsOfPayment[0]) {

			Receipt receipt = receiptService.createReceipt(listOfProducts);
			currentListOfReceipts.add(receipt);
			cashDeclarationService.addAllPricesCreditCard(receipt);
			JOptionPane.showMessageDialog(null, receipt);

		} else if (questionAboutMethodOfPayment == chooseMethodsOfPayment[1]) {
			Receipt receipt = receiptService.createReceipt(listOfProducts);
			currentListOfReceipts.add(receipt);
			cashDeclarationService.sumAllPricesCash(receipt);
			JOptionPane.showMessageDialog(null, receipt);

		} else if (questionAboutMethodOfPayment == chooseMethodsOfPayment[2]) {
			JOptionPane.showMessageDialog(null, "Mixed Transaction");
		}

	}
}
