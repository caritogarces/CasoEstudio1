package co.edu.eafit.carritocompras.service;

import org.junit.Assert;
import org.junit.Test;

import co.edu.eafit.carritocompras.data.Product;
import co.edu.eafit.carritocompras.data.products.ElectronicProduct;

public class BillingCalculatorTest {

	@Test
	public void testFornitureDiscount() {

		final String fornitureString = "FU-006";
		double fornitureDiscount = 0.1;// porcentaje valido de descuento para
										// muebles

		Product forniture = Product.buildProduct(fornitureString);
		// Prueba de lo que se espera contra lo que se calcula en
		// Product.calculateDiscount();

		Assert.assertEquals(forniture.getPrice().doubleValue()
				* fornitureDiscount, forniture.calculateDiscount()
				.doubleValue(), 0d);
	}

	@Test
	public void testElectronicDiscount() {
		final String electricString = "EL-001";
		double electronicDiscount = 0.2;// Porcentaje valido de descuento para
										// electronicos

		ElectronicProduct electric = (ElectronicProduct) Product
				.buildProduct(electricString);
		Assert.assertEquals(electric.getPrice().doubleValue()
				* electronicDiscount, electric.calculateDiscount()
				.doubleValue(), 0d);

	}

}
