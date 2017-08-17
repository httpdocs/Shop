package cn.it.shop.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", catalog = "shop")

public class Product implements java.io.Serializable {

	// Fields

	private Integer id;
	private Category category;
	private String name;
	private double price;
	private String pic;
	private String remark;
	private String xremark;
	private Date date;
	private boolean commend;
	private boolean open;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(Date date) {
		this.date = date;
	}

	/** full constructor */
	public Product(Category category, String name, double price, String pic, String remark, String xremark, Date date,
			boolean commend, boolean open) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.remark = remark;
		this.xremark = xremark;
		this.date = date;
		this.commend = commend;
		this.open = open;
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
	@JoinColumn(name = "cid")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name", length = 50)

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

	@Column(name = "pic", length = 300)

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "remark")

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "xremark")

	public String getXremark() {
		return this.xremark;
	}

	public void setXremark(String xremark) {
		this.xremark = xremark;
	}

	@Column(name = "date", nullable = false, length = 19)

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "commend")

	public boolean getCommend() {
		return this.commend;
	}

	public void setCommend(boolean commend) {
		this.commend = commend;
	}

	@Column(name = "open")

	public boolean getOpen() {
		return this.open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}