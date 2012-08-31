package co.edu.eafit.carritocompras.service;

import java.math.BigDecimal;

public class IVAService {
	
	public BigDecimal calculateIVA(BigDecimal totalPrice, GenericIVAService genericIvaService){
		
		return genericIvaService.calculateIVA(totalPrice);
	}

}
