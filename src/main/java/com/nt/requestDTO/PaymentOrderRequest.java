package com.nt.requestDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PaymentOrderRequest {
	@NotNull(message="Amount is required")
	@Positive(message="Amount must be positive")
	@DecimalMin(value="1.00",message="Amount must be at least 1.00")
    private BigDecimal amount;
	
	@NotNull(message="Currency is required")
	@Pattern(regexp="INR|USD|EUR|GBP",message="Invalid currency")
	private String currency;
	
	@NotNull(message="Receipt is required")
	@Size(min = 3, max = 50, message = "Receipt must be between 3 and 50 characters")
	private String receipt;
	
	@NotNull(message = "Customer details are required")
    private CustomerDetails customer;
	
	 public static class CustomerDetails {
	        @NotBlank(message = "Customer name is required")
	        private String name;
	        
	        @Email(message = "Invalid email format")
	        private String email;
	        
	        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
	        private String phone;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			@Override
			public String toString() {
				return "CustomerDetails [name=" + name + ", email=" + email + ", phone=" + phone + "]";
			}

			public CustomerDetails(@NotBlank(message = "Customer name is required") String name,
					@Email(message = "Invalid email format") String email,
					@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format") String phone) {
				super();
				this.name = name;
				this.email = email;
				this.phone = phone;
			}

			public CustomerDetails() {
				super();
				// TODO Auto-generated constructor stub
			}
			
	        
	    }
	    
	    private String notes;

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getReceipt() {
			return receipt;
		}

		public void setReceipt(String receipt) {
			this.receipt = receipt;
		}

		public CustomerDetails getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerDetails customer) {
			this.customer = customer;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		@Override
		public String toString() {
			return "PaymentOrderRequest [amount=" + amount + ", currency=" + currency + ", receipt=" + receipt
					+ ", customer=" + customer + ", notes=" + notes + "]";
		}

		public PaymentOrderRequest(
				@NotNull(message = "Amount is required") @Positive(message = "Amount must be positive") @DecimalMin(value = "1.00", message = "Amount must be at least 1.00") BigDecimal amount,
				@NotNull(message = "Currency is required") @Pattern(regexp = "INR|USD|EUR|GBP", message = "Invalid currency") String currency,
				@NotNull(message = "Receipt is required") @Size(min = 3, max = 50, message = "Receipt must be between 3 and 50 characters") String receipt,
				@NotNull(message = "Customer details are required") CustomerDetails customer, String notes) {
			super();
			this.amount = amount;
			this.currency = currency;
			this.receipt = receipt;
			this.customer = customer;
			this.notes = notes;
		}

		public PaymentOrderRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	

}
