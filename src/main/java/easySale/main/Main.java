package easySale.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import easySale.entities.Role;
import easySale.services.UserService;
import easySale.view.JOptionPaneView;

public class Main {

	private UserService userService;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public static void main(String[] args) {
		Main main = new Main();
	}

	public Main() {
		entityManagerFactory = Persistence.createEntityManagerFactory("easyDataBase");
		entityManager = entityManagerFactory.createEntityManager();

		boolean endOfProgram = true;
		userService = new UserService(entityManager);
		userService.initData();

		String password = JOptionPaneView.logInScreen();
		Role role = userService.findRoleByUserPassword(password);
		MenuCashierProcess menuCashierProcess = new MenuCashierProcess(userService,entityManager);
		MenuAdministratorProcess menuAdministratorProcess = new MenuAdministratorProcess(userService, entityManager);
		do {
			if (role == Role.ADMINISTRATOR) {
				int menuAdministrator = JOptionPaneView.menu(
						new String[] { "Start selling", "Employee managment","Export of receipts ", "Finish the sale", "Exit" },
						"Easy-Sale Administrator Panel");

				endOfProgram = menuAdministratorProcess.process(menuAdministrator);

			} else if (role == Role.CASHIER) {
				int menuCashier = JOptionPaneView.menu(new String[] { "Start selling", "Finish the sale", "Exit", },
						"Easy-Sale Cashier Module");
				endOfProgram = menuCashierProcess.process(menuCashier);
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect username or password");
				endOfProgram = false;
			}
		} while (endOfProgram);

		entityManager.close();
		entityManagerFactory.close();

	}
}
