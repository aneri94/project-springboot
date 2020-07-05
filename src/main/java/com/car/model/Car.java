package com.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "manufacture_name")
	private String manufactureName;
	@Column(name = "model")
	private String model;
	@Column(name = "manufacturing_year")
	private String manufacturingYear;
	@Column(name = "color")
	private String color;

	public Car() {
		super();
	}

	public Car(Long id, String name, String manufactureName, String model, String manufacturingYear, String color) {
		super();
		this.id = id;
		this.name = name;
		this.manufactureName = manufactureName;
		this.model = model;
		this.manufacturingYear = manufacturingYear;
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getmanufactureName() {
		return manufactureName;
	}

	public void setmanufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getmanufacturingYear() {
		return manufacturingYear;
	}

	public void setmanufacturingYear(String manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
