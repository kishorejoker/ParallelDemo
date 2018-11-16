package com.ParallelDemo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.ParallelDemo.bean.CustomerDemo;
import com.ParallelDemo.bean.Passbook;

public class ImpDaoTest {
	CustomerDemo customer = new CustomerDemo();
	CustomerDemo customer2 = new CustomerDemo();
	ImpDao dao = new ImpDao();

	/*@Test
	public void testCustomerDetails() {
		customer.setAccountno(4799);
		customer.setAddress("12,VijayanStreet,Chennai-600001");
		customer.setBalance(500.0);
		customer.setMobno("7537537537");
		customer.setName("Arjun");
		customer2.setAccountno(2979);
		customer2.setAddress("12,KamalanStreet,Chennai-600001");
		customer2.setBalance(1000.0);
		customer2.setMobno("9519519511");
		customer2.setName("Aju");
		dao.customerDetails(customer2);
		assertTrue("true",(dao.customerDetails(customer)));
	}*/

	@Test
	public void testDeposit() {
		customer.setMobno("7537537537");
		CustomerDemo customertest=dao.deposit(customer.getMobno());
		assertNotNull(customertest);
	}

	@Test
	public void testWithdraw() {
		customer.setMobno("7537537537");
		CustomerDemo customertest=dao.deposit(customer.getMobno());
		assertNotNull(customertest);
	}

	@Test
	public void testShowBalance() {
		customer.setMobno("7537537537");
		CustomerDemo customertest=dao.withdraw(customer.getMobno());
		assertNotNull(customertest);
	}

	@Test
	public void testFundTransfer() {
		customer2.setMobno("9519519511");
		customer.setMobno("7537537537");
		CustomerDemo test=dao.fundTransfer(customer2.getMobno());
		assertNotNull("true",test);
	}

	@Test
	public void testPrintTransactions() {
		customer.setMobno("7537537537");
		customer.setPin(111);
		List<Passbook> listTest=dao.printTransactions(customer.getMobno(),customer.getPin());
		assertNotNull(listTest);
	}

}
