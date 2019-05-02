package model;

public class OrderDetail {
	Product product;
	Order order;
	int amountProduct;
	int unitPrice;
	String color;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getAmountProduct() {
		return amountProduct;
	}

	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getMoney() {
		String str = Integer.toString(this.unitPrice);
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
