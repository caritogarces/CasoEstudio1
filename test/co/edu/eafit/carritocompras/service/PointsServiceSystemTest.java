package co.edu.eafit.carritocompras.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.data.Purchase;

public class PointsServiceSystemTest {

	private Customer customer;
	private PaymentService paymentService;

	@Before
	public void setUp() {
		customer = new Customer("xx1", "xxName");
		paymentService = new PaymentService();
	}

	@Test
	public void pointsSystemTest() {
		GenericCreditCardService creditCardService = Mockito
				.mock(GenericCreditCardService.class);
		Purchase purchase = Mockito.mock(Purchase.class);
		// para una venta cuyo valor es 2500000 el numero de puntos seria 2500 y
		// deberia aplicarse descuento
		Mockito.stub(purchase.getTotalPrice())
				.toReturn(new BigDecimal(2500000));
		// lo que se que debe dar el numero de puntos es lo que debe arrojar el
		// servicio
		Assert.assertEquals(2500d, Double.valueOf(String.valueOf(customer
				.computePoint(purchase.getTotalPrice()))), 0d);
		// el numeor de puntos es mas de mil se aplica descuento del 2% que son
		// 50000
		// Mocking external service behavior
		Mockito.when(creditCardService.pay("xxxx111xxxx", purchase.getTotalPrice()))
				.thenReturn(true);
		//se crea el purchaseNoMock porque el paymentservice necesita modificar la propiedad del nuevm  valor total
		Purchase purchaseNoMock = new Purchase(customer);
		purchaseNoMock.setTotalPrice(purchase.getTotalPrice());
		//en el momento de pago se aplica el descuento y se refresca el valor total
		paymentService.pay(customer, purchaseNoMock, "xxxx111xxxx", creditCardService);

		//el nuevo valor total aplicado el descuento debe ser igual al calculado en paymentService
		
		Assert.assertEquals(2500000d*0.98d, purchaseNoMock.getTotalPrice().doubleValue(),0d);
	}
}
