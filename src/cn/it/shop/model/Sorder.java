package cn.it.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Sorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sorder", catalog = "shop")

public class Sorder implements java.io.Serializable {

	// Fields

	private Integer id;
	private Product product;
	private Forder forder;
	private String name;
	private double price;
	private Integer number;

	// Constructors

	/** default constructor */
	public Sorder() {
	}

	/** minimal constructor */
	public Sorder(Integer number) {
		this.number = number;
	}

	/** full constructor */
	public Sorder(Product product, Forder forder, String name, double price, Integer number) {
		this.product = product;
		this.forder = forder;
		this.name = name;
		this.price = price;
		this.number = number;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fid")

	public Forder getForder() {
		return this.forder;
	}

	public void setForder(Forder forder) {
		this.forder = forder;
	}

	@Column(name = "name", length = 20)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", precision = 8)

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "number", nullable = false)

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}