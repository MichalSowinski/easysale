package easySale.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import easySale.entities.Product;

public class ProductService {
	
	private EntityManager entityManager;
	
	public ProductService(EntityManager entityManager){
		this.entityManager = entityManager;
		
	}

	public BigDecimal getPricesFromProduct(List<Product> listOfProducts) {
		BigDecimal toPay = new BigDecimal("0");
		for (int i = 0; i < listOfProducts.size(); i++) {
			Product product = listOfProducts.get(i);
			BigDecimal priceOfProduct = product.getPrice();
			toPay = priceOfProduct.add(toPay);

		}
		return toPay;

	}

	public Product doesProductExistInBase(String code) {
	

		entityManager.getTransaction().begin();
		Product product = null;
		TypedQuery<Product> query = entityManager.createQuery("select a from Product a where a.barCode = :code",
				Product.class);
		query.setParameter("code", code);

		try {
			product = query.getSingleResult();
			String barCode = product.getBarCode();
			String name = product.getName();
			BigDecimal price = product.getPrice();

			entityManager.getTransaction().commit();
			

		} catch (NoResultException e) {
			JOptionPane.showMessageDialog(null, "nie znaleziono produktu o podanym kodzie ");
		}
		return product;
	}

}
