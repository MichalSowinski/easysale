package easySale.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import easySale.entities.Role;
import easySale.entities.User;
import easySale.repositories.UserRepository;

public class UserService {

	private EntityManager entityManager;

	public UserService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void initData() {

		User mainAdmin = new User();
		mainAdmin.setPassword("abcd1234");
		mainAdmin.setFirstName("Michal");
		mainAdmin.setLastName("Sowinski");
		mainAdmin.setRole(Role.ADMINISTRATOR);
	

		User firstCashier = new User();
		firstCashier.setFirstName("Anna");
		firstCashier.setLastName("Nowak");
		firstCashier.setPassword("cashier");
		firstCashier.setRole(Role.CASHIER);

		

		

		entityManager.getTransaction().begin();
		entityManager.persist(mainAdmin);
		entityManager.persist(firstCashier);
		
		entityManager.getTransaction().commit();
	}

	public Role findRoleByUserPassword(String password) {

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select a.role from User a where a.password = :password");
		query.setParameter("password", password);
		Role role = null;
		if (!query.getResultList().isEmpty()) {
			role = (Role) query.getResultList().get(0);
		}

		entityManager.getTransaction().commit();

		return role;
	}

	public void addUserToDataBase() {

		String getFirstNameFromAdministrator = JOptionPane.showInputDialog("Enter Firstname:");
		String getLastNameFromAdministrator = JOptionPane.showInputDialog("Enter Lastname:");
		String getPasswordFromAdministrator = JOptionPane.showInputDialog("Enter password:");

		// String getShopsFromAdministrator =JOptionPane.showInputDialog("Enter
		// Shops:");

		User user = new User();
		user.setFirstName(getFirstNameFromAdministrator);
		user.setLastName(getLastNameFromAdministrator);
		user.setPassword(getPasswordFromAdministrator);

		Role[] roleOfUser = new Role[2];
		roleOfUser[0] = Role.ADMINISTRATOR;
		roleOfUser[1] = Role.CASHIER;
		Object questionAboutRole = JOptionPane.showInputDialog(null, "Enter Role:", "Roles",
				JOptionPane.QUESTION_MESSAGE, null, roleOfUser, null);

		if (questionAboutRole == Role.ADMINISTRATOR) {
			user.setRole(Role.ADMINISTRATOR);

		} else if (questionAboutRole == Role.CASHIER) {
			user.setRole(Role.CASHIER);
		}

		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();

		// 1) zrobic relacje manytomany miedzy shop a user i zapisac te obiekty
		// do bazy
	}

	public void searchUser() {

		String[] sortingSelection = new String[3];
		sortingSelection[0] = "By first name";
		sortingSelection[1] = "By last name";
		sortingSelection[2] = "By shop";
		Object questionAboutHowToSearchUser = JOptionPane.showInputDialog(null, "How would you like to search user?",
				"Search", JOptionPane.QUESTION_MESSAGE, null, sortingSelection, null);

		if (questionAboutHowToSearchUser == sortingSelection[0]) {
			List<User> users;
			String name = JOptionPane.showInputDialog("Enter name:");
			TypedQuery<User> query = entityManager.createQuery("Select e from User e where e.firstName = :name",
					User.class);
			query.setParameter("name", name);
			users = query.getResultList();

			try {

				JOptionPane.showMessageDialog(null, users);
			} catch (IllegalStateException e) {
				JOptionPane.showMessageDialog(null, "User was not found");
			}

		} else if (questionAboutHowToSearchUser == sortingSelection[1]) {
			String lastName = JOptionPane.showInputDialog("Enter last name:");
			TypedQuery<User> query = entityManager.createQuery("Select e from User e where e.lastName = :lastName",
					User.class);
			query.setParameter("lastName", lastName);
			List<User> users = query.getResultList();
			JOptionPane.showInputDialog(users);

		} else if (questionAboutHowToSearchUser == sortingSelection[2]) {
			JOptionPane.showMessageDialog(null, "It does not work yet");

		}

	}

	public void deleteUser() {

		entityManager.getTransaction().begin();
		String passwordOfUser = JOptionPane.showInputDialog("Enter password of user:");
		TypedQuery<User> query = entityManager.createQuery("select a from User a where a.password = :password",
				User.class);
		query.setParameter("password", passwordOfUser);
		try {
			User user = query.getSingleResult();
			entityManager.remove(user);
		} catch (NoResultException e) {
			JOptionPane.showMessageDialog(null, "User was not found");
		}
		entityManager.getTransaction().commit();

	}

	public void updateUser() {

		entityManager.getTransaction().begin();
		String passwordOfUser = JOptionPane.showInputDialog("Enter password of user which you want to edit :");
		TypedQuery<User> query = entityManager.createQuery("select a from User a where a.password = :password",
				User.class);
		query.setParameter("password", passwordOfUser);
		User user = query.getSingleResult();

		String[] updateSelection = new String[3];
		updateSelection[0] = "first name";
		updateSelection[1] = "last name";
		Object questionAboutHowToUpdateUser = JOptionPane.showInputDialog(null, "What would you like to edit??",
				"Update", JOptionPane.QUESTION_MESSAGE, null, updateSelection, null);
		if (questionAboutHowToUpdateUser == updateSelection[0]) {
			String newName = JOptionPane.showInputDialog("Enter new name:");
			Query query2 = entityManager
					.createQuery("update User set firstName = :firstName" + " where password = :passwordOfUser");

			query2.setParameter("firstName", newName);
			query2.setParameter("passwordOfUser", passwordOfUser);
			query2.executeUpdate();

		} else if (questionAboutHowToUpdateUser == updateSelection[1]) {

		}

		entityManager.getTransaction().commit();

	}

	public boolean exist(String input) {

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select count(a.id) from Administrator a where a.password = :password");
		query.setParameter("password", input);

		long count = (long) query.getSingleResult();
		entityManager.getTransaction().commit();

		if (count > 0) {
			return true;
		}
		return false;
	}

}
