package co.edu.eafit.carritocompras.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.data.Purchase;

public class IVAServiceTest {
	private Customer customer;
	private IVAService ivaService;
	
	@Before
	private void setUp() {
		customer = new Customer("xx1", "xxName");
		ivaService = new IVAService();
	}
	@Test
	public void calculateIVA(){
		//se asume IVA como 16% = 0.16
		Purchase p = BillingCalculator.calculateTotalPurchase(customer, "EL-001,FU-002");
		GenericIVAService genericIVAService = Mockito.mock(GenericIVAService.class);
		//Mocking external service behavior for IVA calculation
		Mockito.when(genericIVAService.calculateIVA(p.getTotalPrice())).thenReturn(p.getTotalPrice().multiply(new BigDecimal(0.16)));
		
		//Se compara el valor calculado localmente con el valor calculado por el servicio que se ha simulado (Mocking)
		Assert.assertEquals(p.getTotalPrice().multiply(new BigDecimal(0.16)).doubleValue(), ivaService.calculateIVA(p.getTotalPrice(), genericIVAService).doubleValue(), 0d);
	}

}
