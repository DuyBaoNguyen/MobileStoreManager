package model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
	int id;
	Timestamp timeOrder;
	String personName;
	boolean personSex;
	String personPhone;
	String personAddress;
	int value;
	List<OrderDetail> orderDetails;
	boolean paid;
	
	public Order() {
		super();
	}
	
	public Order(int id) {
		super();
		this.id = id;
	}

	public boolean getPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTimeOrder() {
		return timeOrder;
	}

	public void setTimeOrder(Timestamp timeOrder) {
		this.timeOrder = timeOrder;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public boolean getPersonSex() {
		return personSex;
	}

	public void setPersonSex(boolean personSex) {
		this.personSex = personSex;
	}

	public String getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getMoney() {
		String str = Integer.toString(this.value);
		int count = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			count++;
			if (count == 3 && i != 0) {
				str = str.substring(0, i) + "." + str.substring(i, str.length());
				count = 0;
			}
		}
		return str + "đ";
	}
}
