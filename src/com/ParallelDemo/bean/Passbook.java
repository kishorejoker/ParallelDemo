package com.ParallelDemo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Passbook {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;
	private String log;
	
	@ManyToOne
	@JoinColumn(name="mobno")
	private CustomerDemo customer;
	
	public Passbook(){
		
	}
	
	public Passbook(String log, CustomerDemo customer) {
		super();
		this.log = log;
		this.customer = customer;
	}


	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public CustomerDemo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDemo customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "TransactionId=" + transactionId + ", Log=" + log
				+ ", MobileNo=" + customer.getMobno();
	}

}
