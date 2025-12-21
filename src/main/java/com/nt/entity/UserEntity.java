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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
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

}
