package com.nt.entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "users")

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long uid;
    
    @Column(name="USER_NAME",nullable=false,unique=true)
    private String userName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "GENDER",nullable=false)
    private String gender;

    @Column(name = "ADDRESS", columnDefinition = "TEXT", nullable = false)
    private String address;

    
   
    
    @Pattern(
    	    regexp = "^\\+?[0-9]{10,15}$",
    	    message = "Mobile number must be 10-15 digits, optionally starting with +"
    	)
    	@Column(name = "MOBILE_NUMBER", length = 15, unique = true, nullable = false)
    	private String mobileNumber;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="SECURITY_ROLES",joinColumns = @JoinColumn(referencedColumnName = "USER_ID",name="USER_ID"))
    private Set<String> roles;
    
   
    @OneToMany(
    	targetEntity = OrdersEntity.class,
        mappedBy = "user",
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Set<OrdersEntity> orders;


	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}


	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}


	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	/**
	 * @return the roles
	 */
	public Set<String> getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}


	/**
	 * @return the orders
	 */
	public Set<OrdersEntity> getOrders() {
		return orders;
	}


	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Set<OrdersEntity> orders) {
		this.orders = orders;
	}


	@Override
	public String toString() {
		return "UserEntity [uid=" + uid + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", address=" + address + ", mobileNumber=" + mobileNumber + ", roles=" + roles
				+ ", orders=" + orders + "]";
	}


	public UserEntity(Long uid, String userName, String email, String password, String gender, String address,
			@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Mobile number must be 10-15 digits, optionally starting with +") String mobileNumber,
			Set<String> roles, Set<OrdersEntity> orders) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.roles = roles;
		this.orders = orders;
	}


	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
    
    

}
