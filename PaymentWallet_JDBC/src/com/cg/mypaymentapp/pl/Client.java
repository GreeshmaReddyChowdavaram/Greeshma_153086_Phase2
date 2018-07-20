package com.cg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class Client
{
	private Scanner console = new Scanner(System.in);
	private Customer customer;
	
	//private Map<String, Customer> data;
	
	private WalletService service;
	private String name = null;
	private String mobileNo = null;
	private String mobileNo1 = null;
	private BigDecimal amount = null;

	public Client() 
	{
		System.out.println("Welcome to Payment Wallet Application");
		customer = new Customer();
		service = new WalletServiceImpl();	
	}
	public void operations()
	{		
		System.out.println("1) Create New Paytm Account");
		System.out.println("2) Check Your Balance");
		System.out.println("3) Deposit Amount");
		System.out.println("4) Withdraw Amount");
		System.out.println("5) Transfer Funds");
		System.out.println("6) Show All Transactions");
		System.out.println("0) Exit Application");
		System.out.println();
		System.out.println("Enter Your Choice");
	
		
		switch (console.nextInt()) {
		case 1:
			//data = new HashMap<String, Customer>();
			
			System.out.println("Enter the Details: ");
			System.out.println();
			System.out.print("Enter your Name: ");
			name = console.next();
			System.out.print("Enter your Mobile Number: ");
			mobileNo = console.next();
			System.out.print("Add balance: ");
			amount = console.nextBigDecimal();
			customer = service.createAccount(name, mobileNo, amount);
			if(customer!=null)
			{
			System.out.println("Your account is created.");	
			System.out.println("With Account Details: ");
			
			
			System.out.println(customer);
			
			}else
				System.out.println("Your account is not created.");
			break;
		
		case 2:
			mobileNo = null;
			System.out.println();
			System.out.print("Enter your registered mobile Number: ");
			mobileNo = console.next();
			customer = service.showBalance(mobileNo);
			System.out.println("Name: "+ customer.getName());
			System.out.println("Your account balance: "+ customer.getWallet().getBalance());
			break;
		
		case 3:
			mobileNo = null;
			System.out.print("Enter your registered mobile Number: ");
			mobileNo = console.next();
			System.out.print("Enter the Amount to Deposit: ");
			amount = console.nextBigDecimal();
			customer = service.depositAmount(mobileNo, amount);
			
			
			
			System.out.println("Amount " + amount + " deposited in the Account with customer name : " + customer.getName());
			System.out.println("Updated balance is " + customer.getWallet().getBalance());
			
			break;
		
		case 4:
			mobileNo = null;
			System.out.print("Enter your registered mobile Number: ");
			mobileNo = console.next();
			System.out.print("Enter the Amount to Withdraw: ");
			amount = console.nextBigDecimal();
			customer = service.withdrawAmount(mobileNo, amount);
			
			
			
			System.out.println("Amount " + amount + " withdrawn from the Account with customer name : " + customer.getName());
			System.out.println("Updated balance is " + customer.getWallet().getBalance());
			break;
		
		case 5:
			System.out.print("Enter the mobile number from which fund need to be transfer: ");
			mobileNo = console.next();
			System.out.print("Enter the mobile number to which fund need to be transfer: ");
			mobileNo1 = console.next();
			System.out.print("Enter the Amount to Transfer: ");
			amount = console.nextBigDecimal();
			customer = new Customer();
			customer = service.fundTransfer(mobileNo, mobileNo1, amount);
			
			
			
			System.out.println("Amount " + amount + " Transferred from the Account with customer name : " + customer.getName());
			System.out.println("Updated balance is " + customer.getWallet().getBalance());
			break;
		case 6:
			Transactions transactions = new Transactions();
			List<String> transactionDetails1 = new ArrayList<String>();
			System.out.print("Enter the Mobile number: ");
			mobileNo = console.next();
			
			transactions = service.getTransaction(mobileNo);
			transactionDetails1 = transactions.getTransactionDetails();
			if(transactionDetails1!=null)
			{
			Iterator<String> it = transactionDetails1.iterator();
			while(it.hasNext())
				System.out.println(it.next());
			}else
				System.out.println("Unable to find any transactions.");
			break;
		default:
		       System.out.println("Kindly enter right choice.Invalid choice");
			break;
		}
		
	}
    public static void main(String[] args)
    {
    	Scanner console = new Scanner(System.in);
		Client client = new Client();
		
		String choice = "yes";
		do
		{
			if(choice.equalsIgnoreCase("yes"))
			{
			client.operations();
			System.out.print("Do you want to continue(yes/no): ");
			choice = console.next();
			}
				
			
		}while(choice.equalsIgnoreCase("yes"));
		System.out.println("Thank you for using it.");
		System.exit(0);
		
		console.close();
	}
}
