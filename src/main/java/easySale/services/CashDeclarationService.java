package easySale.services;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import easySale.entities.CashDeclaration;
import easySale.entities.Receipt;
import easySale.repositories.ProductRepository;

public class CashDeclarationService {
	private ProductRepository productRepository = new ProductRepository();
	private EntityManager entityManager;
	private BigDecimal sumOfCreditCards = BigDecimal.ZERO;
	private BigDecimal sumOfCash = BigDecimal.ZERO;

	public CashDeclarationService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void addAllPricesCreditCard(Receipt receipt) {

		BigDecimal prices = receipt.getValue();
		sumOfCreditCards = prices.add(sumOfCreditCards);

	}

	public void sumAllPricesCash(Receipt receipt) {

		BigDecimal prices = receipt.getValue();
		sumOfCash = prices.add(sumOfCash);
	}

	public BigDecimal CashDeposit() {
		String askAboutCashDeposit = JOptionPane.showInputDialog("Enter cash deposit:");
		BigDecimal askAboutCashDepositConvert = new BigDecimal(askAboutCashDeposit);

		return askAboutCashDepositConvert;
	}

	public CashDeclaration createCashDeclaration() {

		CashDeclaration cashDeclaration = new CashDeclaration();

		cashDeclaration.setSumOfCash(sumOfCash);
		cashDeclaration.setSumOfCreditCards(sumOfCreditCards);

		return cashDeclaration;
	}
}
