package com.ParallelDemo.service;

import java.util.List;

import com.ParallelDemo.bean.CustomerDemo;
import com.ParallelDemo.bean.Passbook;

public interface ICustomerDemo {
	public boolean customerDetails(CustomerDemo cd);
	public String deposit(String mobno,double amt,int pin);
	public String withdraw(String mobno,double amt,int pin);
	public CustomerDemo showBalance(String mobno,int pin);
	public void fundTransfer(String rmobno, String smobno, double amt,int pin);
	public List<Passbook> printTransactions(String mobno,int pin);
	public CustomerDemo displayDetails(String mobno,int pin);

}
