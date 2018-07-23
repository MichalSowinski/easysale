package easySale.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class CashDeclaration {
	@Id
	@GeneratedValue
	private long id;
	private BigDecimal sumOfCash;
	private BigDecimal sumOfCreditCards;
	private BigDecimal cashDeposit;
	@OneToMany
	@JoinColumn(name = "cashDeclaration_id")
	private List<Receipt> listOfReceipt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getSumOfCash() {
		return sumOfCash;
	}

	public void setSumOfCash(BigDecimal sumOfCash) {
		this.sumOfCash = sumOfCash;
	}

	public BigDecimal getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(BigDecimal cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public BigDecimal getSumOfCreditCards() {
		return sumOfCreditCards;
	}

	public void setSumOfCreditCards(BigDecimal sumOfCreditCards) {
		this.sumOfCreditCards = sumOfCreditCards;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CashDeclaration [id=").append(id).append(", sumOfCash=").append(sumOfCash)
				.append(", sumOfCreditCards=").append(sumOfCreditCards).append(", cashDeposit=").append(cashDeposit)
				.append(", role=").append("]");
		return sb.toString();

	}

	public List<Receipt> getListOfReceipt() {
		return listOfReceipt;
	}

	public void setListOfReceipt(List<Receipt> listOfReceipt) {
		this.listOfReceipt = listOfReceipt;
	}

}
