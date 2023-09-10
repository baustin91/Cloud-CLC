package com.gcu.models;

public class ProductModel {
	
	private int id;
	private String name;
	private float price;
	private String description;
	private String img_url;
	
	public ProductModel(int id, String name, float price, String description, String img_url) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.img_url = img_url;
	}

	public ProductModel() {
		super();
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
}
