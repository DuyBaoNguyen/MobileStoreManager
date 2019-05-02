package model;

import java.util.List;

public class Manufacturer {
	int id;
	String name;
	List<Product> products;
	
	public Manufacturer() {
		super();
	}

	public Manufacturer(int id) {
		super();
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
