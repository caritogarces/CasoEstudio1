package co.edu.eafit.carritocompras.service;

import java.math.BigDecimal;

import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.data.Purchase;
import co.edu.eafit.carritocompras.data.PurchaseStatus;
import co.edu.eafit.carritocompras.data.util.ChangeStatusException;

public class PaymentService {

	public void pay(Customer customer, Purchase purchase,
			String creditCardNumnber, GenericCreditCardService creditCardService) {
		try {
			if (creditCardService.pay(creditCardNumnber,
					purchase.getTotalPrice())) {
				purchase.setStatus(PurchaseStatus.APPROVED);
			} else {
				purchase.setStatus(PurchaseStatus.REJECTED);
			}
			if (customer.getUserPoint() >= 1000) {//se determina que  debe aplicarse descuento porque son 1000 puntos o mas
				BigDecimal bd = new BigDecimal(0.98);//el descuento es del 2%, entonces el nuevo valor seria del 98% de lo que era antes
				bd.multiply(purchase.getTotalPrice());//Se calcula el nuevo valor con el descuento
				purchase.setTotalPrice(bd);//se establece el resultado como un nuevo total

			}
		} catch (ChangeStatusException e) {
			purchase.markAsReview();
		}
	}

}
