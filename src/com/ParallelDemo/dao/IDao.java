package com.ParallelDemo.dao;

import java.util.List; 

import com.ParallelDemo.bean.CustomerDemo;
import com.ParallelDemo.bean.Passbook;

public interface IDao {
	public boolean customerDetails(CustomerDemo cd);
	public CustomerDemo deposit(String mobno);
	public CustomerDemo withdraw(String mobno);
	public CustomerDemo showBalance(String mobno);
	public CustomerDemo fundTransfer(String mobno);
	public CustomerDemo displayDetails(String mobno);
	public List<Passbook> printTransactions(String mobno,int pin);
	public void beginTransaction();
	public void commitTransaction();
	public void update(CustomerDemo customer);
	public void transactions(String log,CustomerDemo cd);
}
