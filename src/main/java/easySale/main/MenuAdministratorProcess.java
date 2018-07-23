package easySale.main;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import easy.sell.SellProccess;
import easySale.entities.CashDeclaration;
import easySale.entities.Receipt;
import easySale.services.CashDeclarationService;
import easySale.services.ReceiptService;
import easySale.services.UserService;
import easySale.view.JOptionPaneView;

public class MenuAdministratorProcess {
	private EntityManager entityManager;
	private UserService userService;
	private SellProccess sellProccess;
	private CashDeclarationService cashDeclarationService = new CashDeclarationService(entityManager);
	Receipt receipt = new Receipt();
	ReceiptService receiptService;

	public MenuAdministratorProcess(UserService userService, EntityManager entityManager) {
		this.userService = userService;
		sellProccess = new SellProccess(cashDeclarationService, entityManager);
		receiptService = new ReceiptService(entityManager);
		this.entityManager = entityManager;
	}

	public boolean process(int menuAdministrator) {

		switch (menuAdministrator) {
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
			int optionsOfEmployeeManagment = JOptionPaneView.administratorPanel(
					new String[] { "Add User", "Search User", "Delete User", "Update User", "Return" },
					"Choose option:");

			switch (optionsOfEmployeeManagment) {

			case 0:
				userService.addUserToDataBase();
				break;

			case 1:
				userService.searchUser();
				break;

			case 2:

				userService.deleteUser();
				break;

			case 3:
				userService.updateUser();
				break;

			case 4:

			case 5:
				return false;

			}

			break;

		case 2:
			receiptService.exportReceiptsToFile(sellProccess.getCurrentListOfReceipts());
			break;

		case 3:

			Object[] options = { "Yes", "No" };
			int QuestionAboutFinishSale = JOptionPane.showOptionDialog(null, "Do you want finish your sale?",
					"Finish sale", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (QuestionAboutFinishSale == JOptionPane.YES_OPTION) {
				CashDeclaration cashDeclaration = cashDeclarationService.createCashDeclaration();
				JOptionPane.showMessageDialog(null,
						"Podsumowanie:" + "iloœæ paragonów" + sellProccess.getCurrentListOfReceipts().size() + "Karty:"
								+ cashDeclaration.getSumOfCreditCards() + "Gotówka:" + cashDeclaration.getSumOfCash()
								+ "transakcje mieszane:0");

			} else if (QuestionAboutFinishSale == JOptionPane.NO_OPTION) {

			}
			break;
		case 4:
			System.exit(0);
			break;

		}

		return true;
	}

}
