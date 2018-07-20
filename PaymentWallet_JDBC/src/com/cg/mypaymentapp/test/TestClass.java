package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Greeshu", "7732049627",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Bhavya", "9123456789",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Manvi", "9292929292",new Wallet(new BigDecimal(7000)));
				
		 data.put("7732049627", cust1);
		 data.put("9123456789", cust2);	
		 data.put("9292929292", cust3);	
			service= new WalletServiceImpl(data);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9292929292", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("", "9292929292", new BigDecimal(1500));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("jerry", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	@Test
	public void testCreateAccount5() 
	{
		Customer actual=service.createAccount("Karim", "9692929292", new BigDecimal(5000));
		Customer expected=new Customer("Karim", "9692929292", new Wallet(new BigDecimal(5000)));
		
		assertEquals(expected, actual);
	}

	@Test(expected=InvalidInputException.class)
	public void testShowBalance8() 
	{
		service.showBalance("");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance9() 
	{
		service.showBalance("12345");		
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer12() 
	{
		service.fundTransfer("7732049627", "9292929292", new BigDecimal(12000));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer13() 
	{
		service.fundTransfer("9900112212", "9922950519", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer14() 
	{
		service.fundTransfer("9900112212", "", new BigDecimal(0));		
	}
	
	@Test
	public void testFundTransfer15() 
	{
		Customer customer=service.fundTransfer("7732049627", "9292929292", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransfer16() 
	{
		Customer customer=service.fundTransfer("7732049627", "9292929292", new BigDecimal(550.50));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8449.50);
		
		assertEquals(expected, actual);
	}
	
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer17() 
	{
		Customer customer=service.fundTransfer("7732049627", "9292929292", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer18() 
	{
		service.fundTransfer("", "", new BigDecimal(500));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer19() 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer20() 
	{
		service.fundTransfer("9922950519", null, new BigDecimal(50));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer21() 
	{
		service.fundTransfer("9292929292", "7732049627", new BigDecimal(0));		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount22() 
	{
		service.depositAmount(null, new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount23() 
	{
		service.depositAmount("9942221102", new BigDecimal(500));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount24() 
	{
		service.depositAmount("9292929292", new BigDecimal(-1000));
	}
//	@Test(expected=InsufficientBalanceException.class)
//	public void testWithdrawAmount25() 
//	{
//		service.withdrawAmount("7732049627", new BigDecimal(1000));	
//	}

}
