package com.ParallelDemo.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.ParallelDemo.bean.CustomerDemo;
import com.ParallelDemo.bean.Passbook;
import com.ParallelDemo.exception.InvalidInputException;
import com.ParallelDemo.service.ImpCustomerDemo;

public class CustomerDemomain {
	CustomerDemo cd = new CustomerDemo();
	Scanner sc = new Scanner(System.in);
	ImpCustomerDemo icd = new ImpCustomerDemo();
	Passbook trans = new Passbook();
	Random random=new Random();
	
		public void displaymenu() {
			System.out.println("---------------------");
			System.out.println("WELCOME TO THE BANK");
			System.out.println("---------------------");
			System.out.println("Select an option:");
			System.out.println("---------------------");
			System.out.println("1.Create Account, \n2.Deposit, \n3.WithDraw, \n4.Show Balance, \n5.Display Details, \n6.FundTransfer, \n7.PrintTransactions \n8.Exit");
			System.out.println("\nEnter Your Choice: ");
			int ch = sc.nextInt();
			
			switch (ch) {
			case 1:
				createAccount();
				break;
			case 2:
				deposit();
				break;
			case 3:
				withdraw();
				break;
			case 4:
				showBalance();
				break;
			case 5:
				viewDetails();
				break;
			case 6:
				fundTransfer();
				break;
			case 7:
				printTransactions();
				break;
			case 8:
				System.out.println("!----------Thank You Come Again-------------!");
				System.exit(0);
				break;
			default:
					System.out.println("Invalid Operation! Please try again.");
					break;

			}
	}
	
	public void createAccount() {
		CustomerDemo cd = new CustomerDemo();
		int accnum=random.nextInt(10000);
		int pin=random.nextInt(1000);
		cd.setAccountno(accnum);
		cd.setPin(pin);
		try {
		System.out.println("\n !Enter the Following Credentials to Create an Account! ");
		System.out.println("----------------------------------------------------------");
		do {
		System.out.println("Enter your Name (eg:Adam): ");
			cd.setName(sc.next());
		}while(!icd.isValidName(cd.getName()));
		do {
		System.out.println("Enter your MobileNo : ");
			cd.setMobno(sc.next());
		}while(!icd.isValidMobno(cd.getMobno()));
		do {
		System.out.println("Enter your Address with pincode ");
		System.out.println(" (eg:doorno,street,village,city,district-pincode): ");
			cd.setAddress(sc.next());
		}while(!icd.isValidAddress(cd.getAddress()));
		do{
			System.out.println("Enter an Intial Amount[500,1000]: ");
			cd.setBalance(sc.nextDouble());
		}while(!icd.isValidBalance(cd.getBalance()));
		icd.customerDetails(cd);
		accnum++;
		System.out.println("\nDetails:");
		System.out.println(cd);
		System.out.println(" !--------Account Created---------! ");
		}
		catch(InvalidInputException e) {
			System.err.println("Something Wrong! Reason"+e.getMessage()+"\n");
		}
			
	}
	
	public void deposit() {
		System.out.println("\nEnter the MobileNo linked to Account: ");
		String mobno = sc.next();
		System.out.println("\nEnter the Amount to Deposit: ");
		double amountdeposit = sc.nextDouble();
		System.out.println("\nEnter the PIN: ");
		int pin=sc.nextInt();
		try {
			System.out.println("\n");
			System.out.println(icd.deposit(mobno,amountdeposit,pin));
		}
		catch(InvalidInputException e) {
			System.err.println("Something Wrong! Reason"+e.getMessage()+"\n");
		}	
	}
	
	public void withdraw() {
		System.out.println("Enter the MobileNo linked to your Account: ");
		String mobno = sc.next();
		System.out.println("Enter the Amount to WithDraw: ");
		double amountwithdraw = sc.nextDouble();
		System.out.println("\nEnter the PIN: ");
		int pin=sc.nextInt();
		try {
			System.out.println("\n");
			System.out.println(icd.withdraw(mobno,amountwithdraw,pin));
		}
		catch(InvalidInputException e) {
			System.err.println("Something Wrong! Reason"+e.getMessage()+"\n");
		}
	}
	
	public void showBalance() {
		System.out.println("Enter Your MobileNo linked to your Account: ");
		String mobileno = sc.next();
		System.out.println("\nEnter the PIN: ");
		int pin = sc.nextInt();
		try {
			CustomerDemo customerBalance = icd.showBalance(mobileno,pin);
			if(mobileno.equals(customerBalance.getMobno())) {
				System.out.println("AccountNumber: "+customerBalance.getAccountno());
				System.out.println("Name: "+customerBalance.getName());
				System.out.println("MobileNo: XXXXXX"+customerBalance.getMobno().substring(6));
				System.out.println("Balance: "+customerBalance.getBalance());
			}
		}
		catch(InvalidInputException e) {
			System.err.println("Something Wrong! Reason"+e.getMessage()+"\n");
		}
	}
	
	public void viewDetails() {
		System.out.println("\nEnter Your MobileNo linked to your Account: ");
		String mobileno = sc.next();
		System.out.println("\nEnter the PIN: ");
		int pin = sc.nextInt();
		try {
			CustomerDemo customerDetails=icd.displayDetails(mobileno,pin);
			System.out.println(customerDetails);
		}
		catch(InvalidInputException e) {
			System.err.println("Something Wrong! Reason"+e.getMessage()+"\n");
		}
	}
	
	public void printTransactions() {
		System.out.println("Enter the MobileNo linked to your Account: ");
		String mobno = sc.next();
		System.out.println("Enter the PIN: ");
		int pin=sc.nextInt();
		try {
			List<Passbook> transactionlist=icd.printTransactions(mobno,pin);
			//transactionlist.stream().filter(s->s.).forEach(System.out::println);
			Iterator<Passbook> it=transactionlist.iterator();
			System.out.println("!-----Transactions------!");
			while(it.hasNext()){
				System.out.println(it.next().toString());
			}
		}
		catch(InvalidInputException e) {
			System.err.println("Something Wrong! Reason"+e.getMessage()+"\n");
		}
	}
	
	public void fundTransfer() {
		System.out.println("Enter Recipient's MobileNo: ");
		String recievermobno = sc.next();
		System.out.println("Enter Your MobileNo: ");
		String sendermobno = sc.next();
		System.out.println("Enter the Amount to be transfer: ");
		double amt = sc.nextDouble();
		System.out.println("Enter the PIN: ");
		int pin=sc.nextInt();
		try {
			if(amt>0) {
				icd.fundTransfer(recievermobno,sendermobno,amt,pin);
					System.out.println("Amount Transferred Successfully!");
				
			}
		}
		catch(InvalidInputException e) {
			System.err.println("Something Wrong! Reason"+e.getMessage()+"\n");
		}
	}
	public static void main(String[] args) {
		CustomerDemomain customermain = new CustomerDemomain();
		while (true) {
		customermain.displaymenu();
		}
}
	}