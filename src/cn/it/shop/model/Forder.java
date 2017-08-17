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
 * Forder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "forder", catalog = "shop")

public class Forder implements java.io.Serializable {

	// Fields

	private Integer id;
	private Status status;
	private User user;
	private String name;
	private String phone;
	private String remark;
	private Date date;
	private double total;
	private String post;
	private String address;
	private Set<Sorder> sorders = new HashSet<Sorder>(0);

	// Constructors

	/** default constructor */
	public Forder() {
	}

	/** minimal constructor */
	public Forder(Date date) {
		this.date = date;
	}

	/** full constructor */
	public Forder(Status status, User user, String name, String phone, String remark, Date date, double total,
			String post, String address, Set<Sorder> sorders) {
		this.status = status;
		this.user = user;
		this.name = name;
		this.phone = phone;
		this.remark = remark;
		this.date = date;
		this.total = total;
		this.post = post;
		this.address = address;
		this.sorders = sorders;
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
	@JoinColumn(name = "sid")

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", length = 20)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", length = 20)

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "remark", length = 20)

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "date", nullable = false, length = 19)

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "total", precision = 8)

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Column(name = "post", length = 20)

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "address", length = 200)

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "forder")

	public Set<Sorder> getSorders() {
		return this.sorders;
	}

	public void setSorders(Set<Sorder> sorders) {
		this.sorders = sorders;
	}

}