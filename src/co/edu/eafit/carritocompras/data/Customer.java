package co.edu.eafit.carritocompras.data;

import java.math.BigDecimal;
import java.util.List;

public class Customer {

	private String code;
	private String name;
	private List<Purchase> purchases;
	//new variable for points logic
	private int userPoints;

	public int getUserPoint() {
		return userPoints;
	}

	public void setUserPoint(int userPoint) {
		this.userPoints = userPoint;
	}

	public Customer(String code, String name) {
		super();
		this.code = code;
		this.name = name;
		this.userPoints = 0;
	}
	
	public int computePoint(BigDecimal totalPrice){
		int umbralSumaPuntos = 1000;//por cada 1000 se suma un punto 
		BigDecimal umbralSumaPuntosBD = new BigDecimal(umbralSumaPuntos),receivedPoints = new BigDecimal(0);
	//se obtiene el numero de puntos recibidos
		receivedPoints = totalPrice.divide(umbralSumaPuntosBD);

		userPoints = receivedPoints.intValueExact();
		return receivedPoints.intValueExact();
	}

	public String getCode() {
		return code;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
