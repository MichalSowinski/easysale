package easySale.main;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import easy.sell.SellProccess;
import easySale.entities.CashDeclaration;
import easySale.entities.Receipt;
import easySale.services.CashDeclarationService;
import easySale.services.ReceiptService;
import easySale.services.UserService;

public class MenuCashierProcess {

	private UserService userService;
	private SellProccess sellProccess;
	private EntityManager entityManager;
	private CashDeclarationService cashDeclarationService = new CashDeclarationService(entityManager);
	Receipt receipt = new Receipt();
	ReceiptService receiptService;

	public boolean menuCashierProcess;

	public MenuCashierProcess(UserService userService, EntityManager entityManager) {
		this.userService = userService;
		sellProccess = new SellProccess();
		sellProccess = new SellProccess(cashDeclarationService, entityManager);
		receiptService = new ReceiptService(entityManager);
		this.entityManager = entityManager;
	}

	public boolean process(int menuCashier) {

		switch (menuCashier) {
		case 0:
			boolean czyKoniec = false;
			while (!czyKoniec) {
				int process = sellProccess.sellProccess();
				switch (process) {
				case 0:
					sellProccess.addProduct();
					break;
				case 1:
					sellProccess.checkCurrentListOfProducts();
					break;
				case 2:
					sellProccess.removeListOfProducts();
					break;
				case 3:
					sellProccess.payment();
					break;
				case 4:
					czyKoniec = true;

				}
			}
			break;
		case 1:
			Object[] options = { "Yes", "No" };
			int QuestionAboutFinishSale = JOptionPane.showOptionDialog(null, "Do you want finish your sale?",
					"Finish sale", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (QuestionAboutFinishSale == JOptionPane.YES_OPTION) {
				CashDeclaration cashDeclaration = cashDeclarationService.createCashDeclaration();
				JOptionPane.showMessageDialog(null,
						"Podsumowanie:" + "iloœæ paragonów" + receiptService.countReceipts() + "Karty:"
								+ cashDeclaration.getSumOfCreditCards() + "Gotówka:" + cashDeclaration.getSumOfCash()
								+ "transakcje mieszane:0");

			} else if (QuestionAboutFinishSale == JOptionPane.NO_OPTION) {

			}

		case 2:
			System.exit(0);

		}
		return true;
	}

}
